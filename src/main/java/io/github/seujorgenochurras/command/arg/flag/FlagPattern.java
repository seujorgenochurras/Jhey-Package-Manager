package io.github.seujorgenochurras.command.arg.flag;

import io.github.seujorgenochurras.command.reflections.ValidFlagArgumentTypes;

import java.util.Set;

public class FlagPattern {
   private Set<String> aliases;
   private ValidFlagArgumentTypes argumentType = ValidFlagArgumentTypes.BOOLEAN;

   public Set<String> getAliases() {
      return aliases;
   }

   public FlagPattern setAliases(Set<String> aliases) {
      this.aliases = aliases;
      return this;
   }

   public ValidFlagArgumentTypes getArgumentType() {
      return argumentType;
   }

   public FlagPattern setArgumentType(ValidFlagArgumentTypes argumentType) {
      this.argumentType = argumentType;
      return this;
   }
}
