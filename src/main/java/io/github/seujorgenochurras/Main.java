package io.github.seujorgenochurras;


import static io.github.seujorgenochurras.command.reflections.register.CommandRegister.COMMANDS;

public class Main {
   public static void main(String[] args) {
      try {
         COMMANDS.get("Cli").invoke(args);
         System.out.println(COMMANDS);
      } catch (NullPointerException e) {
         System.out.println("Command not found!");
      }catch (ArrayIndexOutOfBoundsException e){
         System.out.println("No args provided");
      }
   }
}