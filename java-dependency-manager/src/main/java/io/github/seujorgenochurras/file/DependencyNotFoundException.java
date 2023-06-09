package io.github.seujorgenochurras.file;

public class DependencyNotFoundException extends RuntimeException{
   public DependencyNotFoundException() {
   }

   public DependencyNotFoundException(String message) {
      super(message);
   }

   public DependencyNotFoundException(String message, Throwable cause) {
      super(message, cause);
   }

   public DependencyNotFoundException(Throwable cause) {
      super(cause);
   }

   public DependencyNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
   }
}
