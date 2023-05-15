package io.github.seujorgenochurras.command.arg.flag.pattern;

import io.github.seujorgenochurras.command.reflections.common.ValidFlagArgumentTypes;

import java.util.Set;

public class FlagPattern {
   private Set<String> aliases;
   private ValidFlagArgumentTypes flagArgumentType = ValidFlagArgumentTypes.BOOLEAN;

   private boolean isRequired = false;

   public Set<String> getAliases() {
      return aliases;
   }

   public FlagPattern setAliases(Set<String> aliases) {
      this.aliases = aliases;
      return this;
   }

   public boolean isRequired() {
      return isRequired;
   }

   public FlagPattern setRequired(boolean required) {
      isRequired = required;
      return this;
   }

   public ValidFlagArgumentTypes getFlagArgumentType() {
      return flagArgumentType;
   }

   public FlagPattern setFlagArgumentType(ValidFlagArgumentTypes flagArgumentType) {
      this.flagArgumentType = flagArgumentType;
      return this;
   }
}
