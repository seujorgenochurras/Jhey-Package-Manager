package io.github.seujorgenochurras.command.arg;

import io.github.seujorgenochurras.command.Flag;

import java.util.ArrayList;
import java.util.List;


public class CliArgumentBuilder {
  private final List<Flag> flags = new ArrayList<>();

   private CliArgumentBuilder() {
   }
   public static CliArgumentBuilder startBuild(){
      return new CliArgumentBuilder();
   }
   public FlagBuilder newFlag(){
      return new FlagBuilder(this);
   }
   private CliArgumentBuilder addFlag(Flag flag){
      flags.add(flag);
      return this;
   }
   public CliArgument build(){
      return new CliArgument(flags);
   }

   

   public static class FlagBuilder{
      private final CliArgumentBuilder cliArgumentBuilder;

      private final Flag resultedFlag = new Flag();

      public FlagBuilder(CliArgumentBuilder cliArgumentBuilder) {
         this.cliArgumentBuilder = cliArgumentBuilder;
      }
      public FlagBuilder aliases(String ...aliases){
         resultedFlag.setAliases(aliases);
         return this;
      }
      public FlagBuilder argumentType(Class<?> argumentTypeClass){
         resultedFlag.setArgumentType(argumentTypeClass);
         return this;
      }

      public CliArgumentBuilder addFlag(){
         return cliArgumentBuilder.addFlag(this.resultedFlag);
      }

   }
}
