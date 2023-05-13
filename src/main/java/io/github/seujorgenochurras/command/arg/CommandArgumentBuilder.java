package io.github.seujorgenochurras.command.arg;

import io.github.seujorgenochurras.command.arg.flag.FlagPattern;
import io.github.seujorgenochurras.command.arg.flag.FlagPatternCollection;
import io.github.seujorgenochurras.command.reflections.common.ValidFlagArgumentTypes;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;


public class CommandArgumentBuilder {
   private final FlagPatternCollection flags = new FlagPatternCollection();

   private CommandArgumentBuilder() {
   }

   public static CommandArgumentBuilder startBuild() {
      return new CommandArgumentBuilder();
   }

   public FlagPatternBuilder newFlag() {
      return new FlagPatternBuilder(this);
   }

   private CommandArgumentBuilder addFlag(FlagPattern flagPattern) {
      flags.addFlag(flagPattern);
      return this;
   }

   public FlagPatternCollection build() {
      return flags;
   }

   public static class FlagPatternBuilder {
      private final CommandArgumentBuilder commandArgumentBuilder;

      private final FlagPattern resultedFlagPattern = new FlagPattern();

      public FlagPatternBuilder(CommandArgumentBuilder commandArgumentBuilder) {
         this.commandArgumentBuilder = commandArgumentBuilder;
      }

      public FlagPatternBuilder aliases(String... aliases) {
         Set<String> aliasesSet = removeDashesFromAliases(aliases);

         resultedFlagPattern.setAliases(aliasesSet);
         return this;
      }
      private Set<String> removeDashesFromAliases(String... aliases){
         return Arrays.stream(aliases)
                 .map(alias -> alias.replace("-", ""))
                  .collect(Collectors.toSet());
      }

      /**
       * The default value is Boolean
       */
      public FlagPatternBuilder argType(ValidFlagArgumentTypes argumentType){
         resultedFlagPattern.setFlagArgumentType(argumentType);
         return this;
      }

      public CommandArgumentBuilder addFlag() {
         return commandArgumentBuilder.addFlag(this.resultedFlagPattern);
      }

   }
}
