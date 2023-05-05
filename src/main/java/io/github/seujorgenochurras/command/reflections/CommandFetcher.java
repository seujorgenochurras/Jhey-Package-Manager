package io.github.seujorgenochurras.command.reflections;

import io.github.seujorgenochurras.command.AbstractCommand;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class CommandFetcher {
   public Set<Class<? extends AbstractCommand>> commandClasses = new HashSet<>();
   private CommandFetcher(){}

   public static CommandFetcher startFetching(){
      return new CommandFetcher();
   }
   public CommandClassesInstantiator getAllSubClassesOfAbstractCommand(){
      Reflections reflections = new Reflections("io");
      commandClasses.addAll(reflections.getSubTypesOf(AbstractCommand.class));
      return new CommandClassesInstantiator(commandClasses);
   }

   public record CommandClassesInstantiator(Set<Class<? extends AbstractCommand>> commandClasses){
      public Set<AbstractCommand> getCommandInstances(){
        return commandClasses.stream()
                .map(commandClass -> (AbstractCommand) tryToCreateAnInstanceOf(commandClass))
                .collect(Collectors.toSet());
      }
      private Object tryToCreateAnInstanceOf(Class<?> classDefinition) {
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
}