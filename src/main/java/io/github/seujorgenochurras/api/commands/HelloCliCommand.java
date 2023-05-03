package io.github.seujorgenochurras.api.commands;

import io.github.seujorgenochurras.command.AbstractCommand;
import io.github.seujorgenochurras.command.annotation.SubCommand;

@SubCommand(of = HelloCommand.class, name = "Cli")
public class HelloCliCommand extends AbstractCommand {

   @Override
   public void invoke(String[] params) {
      System.out.println("Hello CLI");
   }
}
