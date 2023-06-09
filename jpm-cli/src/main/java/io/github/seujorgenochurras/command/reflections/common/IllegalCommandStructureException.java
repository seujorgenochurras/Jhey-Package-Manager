package io.github.seujorgenochurras.command.reflections.common;

public class IllegalCommandStructureException extends RuntimeException{
   public IllegalCommandStructureException() {
   }

   public IllegalCommandStructureException(String message) {
      super(message);
   }
}
