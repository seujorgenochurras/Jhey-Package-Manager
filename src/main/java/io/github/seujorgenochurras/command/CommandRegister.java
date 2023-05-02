package io.github.seujorgenochurras.command;

import io.github.seujorgenochurras.api.commands.HelloWorldCommand;

import java.util.HashMap;

public class CommandRegister {
   public static final HashMap<String, Command> COMMANDS = new HashMap<>();

   private CommandRegister() {
   }

   static {
      registerCommands();
   }

   private static void registerCommands() {
      COMMANDS.put("HelloW", new HelloWorldCommand());
   }

}
