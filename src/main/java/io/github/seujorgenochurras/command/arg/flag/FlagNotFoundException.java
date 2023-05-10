package io.github.seujorgenochurras.command.arg.flag;

public class FlagNotFoundException extends RuntimeException {

   public FlagNotFoundException(String msg) {
      super(msg);
   }

   public FlagNotFoundException(String msg, Exception e) {
      super(msg, e);
   }
}
