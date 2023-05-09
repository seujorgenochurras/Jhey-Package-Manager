package io.github.seujorgenochurras.command.arg;

import io.github.seujorgenochurras.command.arg.flag.FlagPattern;
import io.github.seujorgenochurras.command.arg.flag.FlagPatternCollection;
import io.github.seujorgenochurras.command.reflections.ValidFlagArgumentTypes;

import java.util.Set;


public class CliArgumentBuilder {
   private final FlagPatternCollection flags = new FlagPatternCollection();

   private CliArgumentBuilder() {
   }

   public static CliArgumentBuilder startBuild() {
      return new CliArgumentBuilder();
   }

   public FlagPatternBuilder newFlag() {
      return new FlagPatternBuilder(this);
   }

   private CliArgumentBuilder addFlag(FlagPattern flagPattern) {
      flags.addFlag(flagPattern);
      return this;
   }

   public CliArgument build() {
      return new CliArgument(flags);
   }

   public static class FlagPatternBuilder {
      private final CliArgumentBuilder cliArgumentBuilder;

      private final FlagPattern resultedFlagPattern = new FlagPattern();

      public FlagPatternBuilder(CliArgumentBuilder cliArgumentBuilder) {
         this.cliArgumentBuilder = cliArgumentBuilder;
      }

      public FlagPatternBuilder aliases(String... aliases) {
         resultedFlagPattern.setAliases(Set.of(aliases));
         return this;
      }

      /***
       * The default value is Boolean
       */
      public FlagPatternBuilder returnType(ValidFlagArgumentTypes argumentType){
         resultedFlagPattern.setArgumentType(argumentType);
         return this;
      }

      public CliArgumentBuilder addFlag() {
         return cliArgumentBuilder.addFlag(this.resultedFlagPattern);
      }

   }
}
