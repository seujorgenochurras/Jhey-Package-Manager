package io.github.seujorgenochurras.command;

import io.github.seujorgenochurras.command.arg.flag.CommandArgs;
import io.github.seujorgenochurras.command.arg.flag.FlagFormatter;
import io.github.seujorgenochurras.command.arg.flag.FlagPatternCollection;

public interface ICommand {

   String[] getNames();

   void invoke(CommandArgs args);

   FlagPatternCollection commandArgsPattern();

  default FlagFormatter flagFormatter(){
      return new FlagFormatter(this.commandArgsPattern());
   }

}
