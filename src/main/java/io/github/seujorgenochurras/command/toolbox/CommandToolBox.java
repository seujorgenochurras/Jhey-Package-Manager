package io.github.seujorgenochurras.command.toolbox;

import io.github.seujorgenochurras.command.arg.CommandArgs;
import io.github.seujorgenochurras.domain.GradlewBuildFile;

public class CommandToolBox {


   public final CommandArgs commandArgs;
   public final CommandConsole commandConsole = new CommandConsole();

   public final GradlewBuildFile gradlewBuildFile;

   public CommandToolBox(CommandArgs commandArgs, GradlewBuildFile gradlewBuildFile) {
      this.commandArgs = commandArgs;
      this.gradlewBuildFile = gradlewBuildFile;
   }
}
