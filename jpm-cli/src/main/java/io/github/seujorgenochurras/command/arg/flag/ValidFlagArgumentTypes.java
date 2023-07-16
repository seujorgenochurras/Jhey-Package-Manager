package io.github.seujorgenochurras.command.arg.flag;

public enum ValidFlagArgumentTypes {
   INTEGER(Integer.class),
   BOOLEAN(Boolean.class),
   STRING(String.class),
   DOUBLE(Double.class);
   public final Class<?> getTypeAsClass;

   ValidFlagArgumentTypes(Class<?> getTypeAsClass) {
      this.getTypeAsClass = getTypeAsClass;
   }
}
