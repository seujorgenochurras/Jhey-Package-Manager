package io.github.seujorgenochurras.command.reflections;


import io.github.seujorgenochurras.command.ICommand;
import io.github.seujorgenochurras.command.reflections.common.IllegalCommandStructureException;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class CommandReflector {
   private CommandReflector() {
   }

   public static Set<ICommand> getAllCommandInstances() {
      Set<ICommand> result = new HashSet<>();

      getAllCommandClasses().forEach(commandClass ->
              result.add((ICommand) tryToCreateAnInstanceOf(commandClass)));

      return result;
   }

   private static Set<Class<? extends ICommand>> getAllCommandClasses() {
      Set<Class<?>> rawCommandClasses = ClassFetcher
              .startFetch()
              .getAllSubClassesOf(ICommand.class)
              .finishFetch();

      return parseClassSetToCommandSet(rawCommandClasses);
   }

   @SuppressWarnings("unchecked")
   private static Set<Class<? extends ICommand>> parseClassSetToCommandSet(Set<Class<?>> classSet) {
      return classSet.stream().map(clazz -> (Class<? extends ICommand>) clazz).collect(Collectors.toSet());
   }

   @SuppressWarnings("ConfusingArgumentToVarargsMethod")
   private static Object tryToCreateAnInstanceOf(Class<?> classDefinition) {
      try {
         return classDefinition.getConstructor(null).newInstance();
      } catch (IllegalAccessException e) {
         throw new IllegalCommandStructureException("Cannot access Command method");
      } catch (InvocationTargetException e) {
         throw new IllegalCommandStructureException("Command method cannot throw something");
      } catch (IllegalArgumentException e) {
         throw new IllegalCommandStructureException("Command has unknown parameters");
      } catch (InstantiationException e) {
         throw new IllegalCommandStructureException("Command class cannot be an interface/abstract class");
      } catch (NoSuchMethodException e) {
         throw new IllegalCommandStructureException("Command" + classDefinition.getName() + " cannot have a private constructor");
      }
   }
}