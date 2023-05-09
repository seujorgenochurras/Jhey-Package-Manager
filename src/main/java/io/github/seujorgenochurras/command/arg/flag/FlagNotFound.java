package io.github.seujorgenochurras.command.arg.flag;

public class FlagNotFound extends RuntimeException {

   public FlagNotFound(String msg) {
      super(msg);
   }

   public FlagNotFound(String msg, Exception e) {
      super(msg, e);
   }
}
