package io.github.seujorgenochurras.command.reflections.common;

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
