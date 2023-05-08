package io.github.seujorgenochurras.command.arg.flag;

public class Flag<T> {
   private T value;

   public T getValue() {
      return value;
   }

   public Flag<T> setValue(T value) {
      this.value = value;
      return this;
   }

}
