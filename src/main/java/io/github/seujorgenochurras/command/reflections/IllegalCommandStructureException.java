package io.github.seujorgenochurras.command.reflections;

public class IllegalCommandStructureException extends RuntimeException{
   public IllegalCommandStructureException() {
   }

   public IllegalCommandStructureException(String message) {
      super(message);
   }
}
