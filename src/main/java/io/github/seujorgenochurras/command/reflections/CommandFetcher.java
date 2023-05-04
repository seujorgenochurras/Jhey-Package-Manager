package io.github.seujorgenochurras.command.reflections;

import io.github.seujorgenochurras.command.AbstractCommand;
import io.github.seujorgenochurras.command.annotation.Command;
import io.github.seujorgenochurras.command.annotation.SubCommand;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public final class CommandFetcher {
   private static final Logger logger = Logger.getLogger(CommandFetcher.class.getName());
   private final Set<Class<?>> possibleCommands;

   private CommandsClassSeparator commandsClassSeparator;

   private CommandFetcher(Set<Class<?>> possibleCommands) {
      this.possibleCommands = possibleCommands;
   }

   public static CommandFetcher startFetchingFor(CommandTypes commandType) {
      return startFetchingFor("io", commandType);
   }
   public static CommandFetcher startFetchingFor(String packageName, CommandTypes commandType) {
      Reflections reflections = new Reflections(packageName);
      Set<Class<?>> possibleCommands = reflections.getTypesAnnotatedWith(commandType.commandTypeClass);
      return new CommandFetcher(possibleCommands);
   }


   public CommandsClassSeparator getAllClassesAnnotatedWithCommand() {
      commandsClassSeparator = new CommandsClassSeparator(possibleCommands, this)
              .separateCommands();
      return commandsClassSeparator;
   }

   private HashMap<String, AbstractCommand> getCommands() {
      List<Class<? extends AbstractCommand>> commandClasses = commandsClassSeparator.getCommandsClasses();
      HashMap<String, AbstractCommand> commands = new HashMap<>();
      commandClasses.forEach(commandClass -> {
         String commandName = commandClass.getAnnotation(Command.class).name();
         AbstractCommand abstractCommand = (AbstractCommand) tryToCreateAnInstanceOf(commandClass);
         commands.put(commandName, abstractCommand);
      });
      return commands;
   }
   private HashMap<String, AbstractCommand> getSubCommands() {
      List<Class<? extends AbstractCommand>> commandClasses = commandsClassSeparator.getCommandsClasses();
      HashMap<String, AbstractCommand> commands = new HashMap<>();
      commandClasses.forEach(commandClass -> {
         String commandName = commandClass.getAnnotation(SubCommand.class).name();
         AbstractCommand abstractCommand = (AbstractCommand) tryToCreateAnInstanceOf(commandClass);
         commands.put(commandName, abstractCommand);
      });
      return commands;
   }
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

   public static final class CommandsClassSeparator {
      private final Map<Boolean, List<Class<?>>> separatedCommandsClass = new HashMap<>();
      private final Set<Class<?>> commandListToSeparate;

      private final CommandFetcher fetcher;

      public CommandsClassSeparator(Set<Class<?>> commandListToSeparate, CommandFetcher fetcher) {
         this.commandListToSeparate = commandListToSeparate;
         this.fetcher = fetcher;
      }

      private CommandsClassSeparator separateCommands() {
         separatedCommandsClass.putAll(commandListToSeparate
                 .stream()
                 .collect(Collectors
                         .partitioningBy(commandClass ->
                                 commandClass.getSuperclass().equals(AbstractCommand.class))));
         return this;
      }

      private List<Class<? extends AbstractCommand>> getCommandsClasses() {
         return separatedCommandsClass.get(true).stream().map(command -> (Class<? extends AbstractCommand>) command).collect(Collectors.toList());
      }

      public CommandsClassSeparator warnIfCommandIsNotExtendingAbstractCommand() {
         separatedCommandsClass.get(false).forEach(notCommand ->
                 logger.logrb(Level.WARNING,
                         ResourceBundle.getBundle(notCommand.getName()),
                         "Command " + notCommand.getName() + " is not defined as an AbstractCommand",
                         ""));
         return this;
      }

      public HashMap<String, AbstractCommand> getCommandsAndFinishFetching() {
         return fetcher.getCommands();
      }
      public HashMap<String, AbstractCommand> getSubCommandsAndFinishFetching() {
         return fetcher.getSubCommands();
      }
   }
}
