package io.github.seujorgenochurras.api.commands;

import io.github.seujorgenochurras.command.ICommand;

public class HelloCliCommand implements ICommand {


   @Override
   public void invoke(String[] params) {
      System.out.println("Hello CLI");
   }

   @Override
   public String[] getNames() {
      return new String[]{"Cli"};
   }
}
