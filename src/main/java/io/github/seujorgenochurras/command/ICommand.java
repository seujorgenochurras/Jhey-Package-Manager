package io.github.seujorgenochurras.command;

import io.github.seujorgenochurras.command.arg.flag.FlagFormatter;
import io.github.seujorgenochurras.command.arg.flag.FlagPatternCollection;

public interface ICommand {

   String[] getNames();

   void invoke(CommandToolBox toolBox);

   FlagPatternCollection commandArgsPattern();

  default FlagFormatter flagFormatter(){
      return new FlagFormatter(this.commandArgsPattern());
   }

}
