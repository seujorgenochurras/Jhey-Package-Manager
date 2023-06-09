package io.github.seujorgenochurras.command.reflections.register;

import io.github.seujorgenochurras.api.command.jpm.JpmCommand;
import io.github.seujorgenochurras.command.ICommand;
import io.github.seujorgenochurras.command.reflections.CommandReflector;

import java.util.*;

public class CommandRegister {
   private CommandRegister(){}

   public static final HashMap<String, ICommand> COMMANDS = new HashMap<>();

   static {
      COMMANDS.put( "jpm", new JpmCommand());
   }

   private static void registerCommands(){
      Set<ICommand> abstractCommands = getCommandInstances();
      abstractCommands.forEach(command ->
              Arrays.stream(
                      command.getNames())
                      .forEach(commandName -> COMMANDS.put(commandName, command)));
   }
   private static Set<ICommand> getCommandInstances(){
      return CommandReflector.getAllCommandInstances();
   }

}
