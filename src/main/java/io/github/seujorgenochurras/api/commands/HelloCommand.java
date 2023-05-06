package io.github.seujorgenochurras.api.commands;

import io.github.seujorgenochurras.command.ICommand;

public class HelloCommand implements ICommand {

   @Override
   public String[] getNames() {
      return new String[]{"Hello"};
   }

   @Override
   public void invoke(String[] params) {
      System.out.println("Hello");
   }


   public static final class HelloWorldCommand implements ICommand{
      public HelloWorldCommand(){}

      @Override
      public String[] getNames() {
         return new String[]{"World"};
      }

      @Override
      public void invoke(String[] params) {
         System.out.println("Hello World");
      }
   }
}
