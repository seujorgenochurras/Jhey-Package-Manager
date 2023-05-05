package io.github.seujorgenochurras.command.reflections.register;

import io.github.seujorgenochurras.command.AbstractCommand;
import io.github.seujorgenochurras.command.reflections.CommandFetcher;

import java.util.*;

public class CommandRegister {
   private CommandRegister(){}

   public static final HashMap<String, AbstractCommand> COMMANDS = new HashMap<>();

   static {
      registerCommands();
   }

   private static void registerCommands(){
      Set<AbstractCommand> abstractCommands = getCommandInstances();
      abstractCommands.forEach(command ->
              Arrays.stream(
                      command.getCommandNames())
                      .forEach(commandName -> COMMANDS.put(commandName, command)));
   }
   private static Set<AbstractCommand> getCommandInstances(){
      return CommandFetcher
              .startFetching()
              .getAllSubClassesOfAbstractCommand()
              .getCommandInstances();
   }

}
