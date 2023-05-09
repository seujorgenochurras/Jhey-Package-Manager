package io.github.seujorgenochurras.command.arg.flag;

import static io.github.seujorgenochurras.command.reflections.ValidFlagArgumentTypes.*;

import io.github.seujorgenochurras.command.reflections.ValidFlagArgumentTypes;

public class FlagFormatter {
   private FlagPatternCollection flagPatterns;

   public CommandFlags formatString(String rawFlags){
      String commandArgSeparatorRegex = "-|--(?=\\w)";
      String[] commandArgs = rawFlags.split(commandArgSeparatorRegex);
      CommandFlags resultedFlags = new CommandFlags();

      for (String commandArg : commandArgs) {
         String flagNameAndValueSeparator = "=";
         String[] flagNameAndValue = commandArg.split(flagNameAndValueSeparator);
         String flagName = flagNameAndValue[0];
         String flagValue = flagNameAndValue[1];

         Flag flag = new FlagValidator(flagName, flagValue).validateAndGetFlag();
         resultedFlags.put(flagName, flag);
      }
      return resultedFlags;
   }
   private final class FlagValidator{
      private final String flagName;
      private final String flagValue;

      public FlagValidator(String flagName, String flagValue) {
         this.flagName = flagName;
         this.flagValue = flagValue;
      }
      public Flag validateAndGetFlag() throws FlagNotFound, IllegalFlagType {
         if(!flagExists()) throw new FlagNotFound("Flag " + flagName + " not found in");
         if(!isFlagValueValid()) throw new IllegalFlagType("Flag value " + flagValue + " is illegal in" + flagName);

         return new Flag(flagValue, flagName);
      }
      private boolean isFlagValueValid(){
         return getFlagValueType().equals(getFlagPatternReturnType());
      }

      public ValidFlagArgumentTypes getFlagValueType(){
         ValidFlagArgumentTypes flagArgReturnType = null;
         if(flagValue == null) flagArgReturnType = BOOLEAN;
         else if(flagValue.startsWith("\"")) flagArgReturnType = STRING;
         else if(flagValue.contains(".") && !Double.isNaN(Double.parseDouble(flagValue))) flagArgReturnType = DOUBLE;
         else if(flagValue.split("\\d").length != 0 && !Double.isNaN(Double.parseDouble(flagValue))) flagArgReturnType = INTEGER;

         return flagArgReturnType;
      }

      private boolean flagExists(){
         return flagPatterns.containsKey(flagName);
      }

      private ValidFlagArgumentTypes getFlagPatternReturnType(){
         return getFlagPattern().getArgumentType();
      }
      private FlagPattern getFlagPattern(){
         return flagPatterns.getFlagByName(flagName);
      }
   }
}
