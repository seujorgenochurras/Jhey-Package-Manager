package io.github.seujorgenochurras.command;

import io.github.seujorgenochurras.api.commands.HelloCommand;

import java.util.HashMap;

public class CommandRegister {
   public static final HashMap<String, AbstractCommand> COMMANDS = new HashMap<>();

   private CommandRegister() {
   }

   static {
      registerCommands();
   }

   private static void registerCommands() {
      COMMANDS.put("HelloW", new HelloCommand.HelloWorldCommand());
      //TODO make this automatic, maybe with annotations and reflection.
   }

}
