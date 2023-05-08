package io.github.seujorgenochurras.api.commands;

import io.github.seujorgenochurras.command.ICommand;
import io.github.seujorgenochurras.command.arg.CliArgumentBuilder;
import io.github.seujorgenochurras.command.arg.CliArgument;
import io.github.seujorgenochurras.command.arg.flag.FlagPatternCollection;

public class HelloCliCommand implements ICommand {


   @Override
   public void invoke(FlagPatternCollection flags) {
      String messageFlag = flags.getFlagByName("force");
      System.out.println("Hello CLI");
   }

   @Override
   public String[] getNames() {
      return new String[]{"Cli"};
   }

   public CliArgument getArgs(){
     return CliArgumentBuilder
              .startBuild()

              .newFlag()
              .aliases("-f", "--force")
              .addFlag()

              .newFlag()
              .aliases("-m", "--message")
              .addFlag()

              .newFlag()
              .aliases("-j", "--joke")
              .addFlag()
              .build();
   }
}