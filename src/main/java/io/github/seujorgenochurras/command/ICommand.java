package io.github.seujorgenochurras.command;

import io.github.seujorgenochurras.command.arg.flag.FlagPatternCollection;

public interface ICommand {

   String[] getNames();

   void invoke(FlagPatternCollection args);

   ArgFormatter argFormatter();

}
