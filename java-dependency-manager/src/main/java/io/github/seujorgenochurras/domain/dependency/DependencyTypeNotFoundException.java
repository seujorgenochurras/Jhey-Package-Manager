package io.github.seujorgenochurras.domain.dependency;

public class DependencyTypeNotFoundException extends RuntimeException{
   public DependencyTypeNotFoundException() {
   }

   public DependencyTypeNotFoundException(String message) {
      super(message);
   }

   public DependencyTypeNotFoundException(String message, Throwable cause) {
      super(message, cause);
   }

   public DependencyTypeNotFoundException(Throwable cause) {
      super(cause);
   }

   public DependencyTypeNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
   }
}
