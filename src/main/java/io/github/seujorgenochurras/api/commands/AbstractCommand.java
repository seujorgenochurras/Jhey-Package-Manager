package io.github.seujorgenochurras.api.commands;

public abstract class AbstractCommand {
   private final Args args;

   protected AbstractCommand(Args args) {
      this.args = args;
   }
   public abstract void callback();
}
