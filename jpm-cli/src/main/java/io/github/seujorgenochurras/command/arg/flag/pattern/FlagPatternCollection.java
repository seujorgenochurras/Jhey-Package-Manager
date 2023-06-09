package io.github.seujorgenochurras.command.arg.flag.pattern;

import java.util.HashMap;

public class FlagPatternCollection extends HashMap<String, FlagPattern> {
   public FlagPattern getFlagByName(String name) {
      return this.get(name);
   }

   public void addFlag(FlagPattern flagPattern) {
      flagPattern.getAliases().forEach(flagName -> this.put(flagName, flagPattern));
   }

}
