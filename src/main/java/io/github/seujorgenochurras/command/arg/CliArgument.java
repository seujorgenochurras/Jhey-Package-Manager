package io.github.seujorgenochurras.command.arg;

import io.github.seujorgenochurras.command.arg.flag.FlagPatternCollection;

public class CliArgument {
   private final FlagPatternCollection flags;

   public CliArgument(FlagPatternCollection flags) {
      this.flags = flags;
   }
}
