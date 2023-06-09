package io.github.seujorgenochurras.file;

public class DependencyFileNotFoundException extends RuntimeException{
   public DependencyFileNotFoundException() {
   }

   public DependencyFileNotFoundException(String message) {
      super(message);
   }

   public DependencyFileNotFoundException(String message, Throwable cause) {
      super(message, cause);
   }

   public DependencyFileNotFoundException(Throwable cause) {
      super(cause);
   }

   public DependencyFileNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
   }
}
