package io.github.seujorgenochurras.command;

import io.github.seujorgenochurras.command.arg.flag.format.FlagFormatter;
import io.github.seujorgenochurras.command.arg.flag.pattern.FlagPatternCollection;
import io.github.seujorgenochurras.command.toolbox.CommandToolBox;

public interface ICommand {

   String[] getNames();

   void invoke(CommandToolBox toolBox);

   FlagPatternCollection commandArgsPattern();

   default FlagFormatter flagFormatter() {
      return new FlagFormatter(this.commandArgsPattern());
   }

}
