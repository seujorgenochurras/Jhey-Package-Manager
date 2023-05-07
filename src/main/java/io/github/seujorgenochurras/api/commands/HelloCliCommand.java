package io.github.seujorgenochurras.api.commands;

import io.github.seujorgenochurras.command.ICommand;
import io.github.seujorgenochurras.command.arg.CliArgumentBuilder;
import io.github.seujorgenochurras.command.arg.CliArgument;

public class HelloCliCommand implements ICommand {


   @Override
   public void invoke(String[] params) {
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
              .argumentType(String.class)
              .addFlag()

              .newFlag()
              .aliases("-m", "--message")
              .argumentType(String.class)
              .addFlag()

              .newFlag()
              .aliases("-j", "--joke")
              .addFlag()
              .build();
   }
}