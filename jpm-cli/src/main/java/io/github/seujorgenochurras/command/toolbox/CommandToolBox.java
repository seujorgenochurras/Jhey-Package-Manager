package io.github.seujorgenochurras.command.toolbox;

import io.github.seujorgenochurras.command.arg.CommandArgs;
import io.github.seujorgenochurras.manager.DependencyManager;
import io.github.seujorgenochurras.mapper.DependencyManagerFile;

public class CommandToolBox {


   public final CommandArgs commandArgs;
   public final CommandConsole commandConsole = new CommandConsole();
   public final String currentDirPath = System.getProperty("user.dir");
   public final DependencyManagerFile dependencyManager = DependencyManager.getDependencyManagerFile(currentDirPath);

   public CommandToolBox(CommandArgs commandArgs) {
      this.commandArgs = commandArgs;
   }
}
