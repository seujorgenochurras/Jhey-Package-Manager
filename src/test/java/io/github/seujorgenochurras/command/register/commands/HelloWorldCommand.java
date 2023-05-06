package io.github.seujorgenochurras.command.register.commands;

import io.github.seujorgenochurras.command.ICommand;

public class HelloWorldCommand implements ICommand {

   @Override
   public String[] getNames() {
      return new String[]{"Hello World"};
   }

   @Override
   public void invoke(String[] params) {
      System.out.println("yeah");
   }
}
