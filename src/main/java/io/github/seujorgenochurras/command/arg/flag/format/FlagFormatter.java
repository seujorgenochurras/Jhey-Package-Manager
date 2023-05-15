package io.github.seujorgenochurras.command.arg.flag.format;

import static io.github.seujorgenochurras.command.reflections.common.ValidFlagArgumentTypes.*;

import io.github.seujorgenochurras.command.arg.CommandArgs;
import io.github.seujorgenochurras.command.arg.flag.Flag;
import io.github.seujorgenochurras.command.arg.flag.pattern.FlagPattern;
import io.github.seujorgenochurras.command.arg.flag.pattern.FlagPatternCollection;
import io.github.seujorgenochurras.command.reflections.common.ValidFlagArgumentTypes;

public class FlagFormatter {
   private final FlagPatternCollection flagPatterns;

   public FlagFormatter(FlagPatternCollection flagPatterns) {
      this.flagPatterns = flagPatterns;
   }

   public CommandArgs formatString(String rawFlags){
      String commandArgSeparatorRegex = "(-\\w=)|--\\w+=";
      String[] commandArgs = rawFlags.split(commandArgSeparatorRegex);
      CommandArgs resultedFlags = new CommandArgs();

      for (String commandArg : commandArgs) {
         String flagNameAndValueSeparator = "=";
         String[] flagNameAndValue = commandArg.split(flagNameAndValueSeparator);
         String flagName = flagNameAndValue[0];
         String flagValue = tryGetFlagValue(flagNameAndValue);
         Flag flag = new FlagValidator(flagName, flagValue).validateAndGetFlag();
         resultedFlags.put(flagName, flag);
      }
      return resultedFlags;
   }
   private String tryGetFlagValue(String[] flagNameAndValue){
      try {
         return flagNameAndValue[1];
      }catch (IndexOutOfBoundsException e){
         return null;
      }
   }

   private final class FlagValidator{
      private final String flagName;
      private final String flagValue;

      public FlagValidator(String flagName, String flagValue) {
         this.flagName = flagName;
         this.flagValue = flagValue;
      }
      public Flag validateAndGetFlag() throws FlagNotFoundException, IllegalFlagType {
         if(!flagExists()) throw new FlagNotFoundException("Flag " + flagName + " not found");
         if(!isFlagValueValid())
            throw new IllegalFlagType("Type of value (" + getFlagValueType() + ") is illegal in flag -" + flagName);

         return new Flag(flagValue, flagName);
      }

      private boolean isFlagValueValid(){
         return getFlagValueType().equals(getFlagPatternReturnType());
      }

      public ValidFlagArgumentTypes getFlagValueType(){
         ValidFlagArgumentTypes flagArgReturnType;
         if(flagValue == null) flagArgReturnType = BOOLEAN;
         else if(flagValue.matches("\\d")){
            if(flagValue.split("\\.").length != 0){
               flagArgReturnType = DOUBLE;
            }else {
               flagArgReturnType = INTEGER;
            }
         }
         else flagArgReturnType = STRING;
         return flagArgReturnType;
      }

      private boolean flagExists(){
         return flagPatterns.containsKey(flagName);
      }

      private ValidFlagArgumentTypes getFlagPatternReturnType(){
         return getFlagPattern().getFlagArgumentType();
      }
      private FlagPattern getFlagPattern(){
         return flagPatterns.getFlagByName(flagName);
      }
   }
}
