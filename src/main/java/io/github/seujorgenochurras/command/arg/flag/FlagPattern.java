package io.github.seujorgenochurras.command.arg.flag;

import io.github.seujorgenochurras.command.reflections.ValidArgumentTypes;

import java.util.Set;

public class FlagPattern {
   private Set<String> aliases;
   private ValidArgumentTypes argumentType = ValidArgumentTypes.BOOLEAN;

   public Set<String> getAliases() {
      return aliases;
   }

   public FlagPattern setAliases(Set<String> aliases) {
      this.aliases = aliases;
      return this;
   }

   public ValidArgumentTypes getArgumentType() {
      return argumentType;
   }

   public FlagPattern setArgumentType(ValidArgumentTypes argumentType) {
      this.argumentType = argumentType;
      return this;
   }
}
