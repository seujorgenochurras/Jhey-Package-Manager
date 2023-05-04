package io.github.seujorgenochurras;

import io.github.seujorgenochurras.command.reflections.register.CommandRegister;

public class Main {
   public static void main(String[] args) {
      System.out.println(CommandRegister.commands);
      System.out.println("\n\n");
      CommandRegister.commands.forEach((s, command) -> System.out.println(command.subCommands));
   }
}