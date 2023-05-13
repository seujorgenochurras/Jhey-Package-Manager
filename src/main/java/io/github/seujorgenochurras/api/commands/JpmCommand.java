package io.github.seujorgenochurras.api.commands;

import io.github.seujorgenochurras.command.ICommand;
import io.github.seujorgenochurras.command.arg.CommandArgumentBuilder;
import io.github.seujorgenochurras.command.arg.flag.CommandArgs;
import io.github.seujorgenochurras.command.arg.flag.FlagPatternCollection;
import io.github.seujorgenochurras.command.reflections.common.ValidFlagArgumentTypes;

public class JpmCommand implements ICommand {


   @Override
   public void invoke(CommandArgs args) {
      String libName = args.getFlagByName("i").getValueAsString();
      System.out.println(libName);
   }

   @Override
   public FlagPatternCollection commandArgsPattern() {
      return CommandArgumentBuilder
              .startBuild()

              .newFlag()
              .aliases("-i", "--install")
              .argType(ValidFlagArgumentTypes.STRING)
              .addFlag()

              .build();
   }

   @Override
   public String[] getNames() {
      return new String[]{"jpm"};
   }

}
