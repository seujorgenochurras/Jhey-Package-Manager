package io.github.seujorgenochurras.command.arg.flag.format;

public class IllegalFlagType extends RuntimeException {
   public IllegalFlagType() {
   }

   public IllegalFlagType(String message) {
      super(message);
   }

   public IllegalFlagType(String message, Throwable cause) {
      super(message, cause);
   }

   public IllegalFlagType(Throwable cause) {
      super(cause);
   }

   public IllegalFlagType(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
   }
}
