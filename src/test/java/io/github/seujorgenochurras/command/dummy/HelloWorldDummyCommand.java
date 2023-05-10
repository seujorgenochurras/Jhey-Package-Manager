package io.github.seujorgenochurras.command.dummy;

import io.github.seujorgenochurras.command.ICommand;
import io.github.seujorgenochurras.command.arg.CommandArgumentBuilder;
import io.github.seujorgenochurras.command.arg.flag.CommandArgs;
import io.github.seujorgenochurras.command.arg.flag.FlagPatternCollection;
import io.github.seujorgenochurras.command.reflections.ValidFlagArgumentTypes;

public class HelloWorldDummyCommand implements ICommand {

   @Override
   public String[] getNames() {
      return new String[]{"Hello World"};
   }

   @Override
   public void invoke(CommandArgs args) {
      String message = args.getFlagByName("-m").getValueAsString();
      if(message == null) {
         System.out.println("Hello World");
      } else {
         System.out.println("Hello World " + message);
      }
   }

   @Override
   public FlagPatternCollection commandArgsPattern() {
     return CommandArgumentBuilder
              .startBuild()

              .newFlag()
              .aliases("-m", "--message")
              .returnType(ValidFlagArgumentTypes.STRING)
              .addFlag()
              .build();
   }
}
