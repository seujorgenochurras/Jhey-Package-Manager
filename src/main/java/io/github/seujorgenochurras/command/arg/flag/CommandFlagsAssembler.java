package io.github.seujorgenochurras.command.arg.flag;

import java.util.HashMap;
import java.util.Set;

public class CommandFlagsAssembler {
   private final String rawCliArgs;

   public CommandFlagsAssembler(String rawCliArgs) {
      this.rawCliArgs = rawCliArgs;
   }

   public CommandFlags fulfillFlagsPattern(FlagPatternCollection flagPatternCollection){
      CommandFlags rawArgsAsCollection = parseRawCliArgsToFlagPattern();

      return CommandFlagsValidator.validate(rawArgsAsCollection);


   }
   private CommandFlags parseRawCliArgsToFlagPattern(){
      return new CommandFlags();
   }
   private static class CliArgsParser{
      private final String rawCliArgs;

      public CliArgsParser(String rawCliArgs) {
         this.rawCliArgs = rawCliArgs;
      }
      public CommandFlags parseCliArgs(){
         HashMap<String, String> flagNameAndValue;

      }
      private Set<String> rawCliArgs

   }

   private static class CommandFlagsValidator{


   }
}
