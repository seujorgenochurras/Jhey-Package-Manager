package io.github.seujorgenochurras.command;

public class Flag {
   private String[] aliases;
   private Class<?> argumentType = Boolean.class;

   public String[] getAliases() {
      return aliases;
   }

   public Flag setAliases(String[] aliases) {
      this.aliases = aliases;
      return this;
   }

   public Class<?> getArgumentType() {
      return argumentType;
   }

   public Flag setArgumentType(Class<?> argumentType) {
      this.argumentType = argumentType;
      return this;
   }
}
