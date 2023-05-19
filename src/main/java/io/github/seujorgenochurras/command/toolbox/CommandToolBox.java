package io.github.seujorgenochurras.command.toolbox;

import io.github.seujorgenochurras.command.arg.CommandArgs;

public class CommandToolBox {

   public CommandToolBox(CommandArgs commandArgs) {
      this.commandArgs = commandArgs;
   }

   public final CommandArgs commandArgs;
   public final CommandConsole commandConsole = new CommandConsole();
}
