package io.github.seujorgenochurras.api.commands;

import io.github.seujorgenochurras.command.AbstractCommand;

public class HelloCliCommand extends AbstractCommand {

   public HelloCliCommand(){
      super("Cli");
   }
   @Override
   public void invoke(String[] params) {
      System.out.println("Hello CLI");
   }
}
