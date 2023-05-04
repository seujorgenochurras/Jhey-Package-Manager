package io.github.seujorgenochurras.command.reflections;

public class IllegalCommandStructureException extends RuntimeException{
   public IllegalCommandStructureException() {
   }

   public IllegalCommandStructureException(String message) {
      super(message);
   }

   public IllegalCommandStructureException(String message, Throwable cause) {
      super(message, cause);
   }

   public IllegalCommandStructureException(Throwable cause) {
      super(cause);
   }

   public IllegalCommandStructureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
   }
}
