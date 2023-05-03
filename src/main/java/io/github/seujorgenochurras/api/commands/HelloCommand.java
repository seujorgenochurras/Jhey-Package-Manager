package io.github.seujorgenochurras.api.commands;

import io.github.seujorgenochurras.command.AbstractCommand;
import io.github.seujorgenochurras.command.annotation.Command;
import io.github.seujorgenochurras.command.annotation.SubCommand;

@Command(name = "Hello")
public class HelloCommand extends AbstractCommand {

   @Override
   public void invoke(String[] params) {
      System.out.println("Hello");
   }

   @SubCommand(name = "World")
   public static final class HelloWorldCommand extends AbstractCommand{

      @Override
      public void invoke(String[] params) {
         System.out.println("Hello World");
      }
   }
}
