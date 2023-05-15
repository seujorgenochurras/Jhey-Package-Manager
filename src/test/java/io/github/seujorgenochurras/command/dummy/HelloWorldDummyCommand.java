//package io.github.seujorgenochurras.command.dummy;
//
//import io.github.seujorgenochurras.command.toolbox.CommandToolBox;
//import io.github.seujorgenochurras.command.ICommand;
//import io.github.seujorgenochurras.command.arg.CommandArgumentBuilder;
//import io.github.seujorgenochurras.command.arg.flag.pattern.FlagPatternCollection;
//import io.github.seujorgenochurras.command.reflections.common.ValidFlagArgumentTypes;
//
//public class HelloWorldDummyCommand implements ICommand {
//
//   @Override
//   public String[] getNames() {
//      return new String[]{"Hello World"};
//   }
//
//   @Override
//   public void invoke(CommandToolBox toolBox) {
//      String message = toolBox.commandArgs().getFlagByName("-m").getValueAsString();
//      if(message == null) {
//         System.out.println("Hello World");
//      } else {
//         System.out.println("Hello World " + message);
//      }
//   }
//
//   @Override
//   public FlagPatternCollection commandArgsPattern() {
//     return CommandArgumentBuilder
//              .startBuild()
//
//              .newFlag()
//              .aliases("-m", "--message")
//              .argType(ValidFlagArgumentTypes.STRING)
//              .addFlag()
//              .build();
//   }
//}
