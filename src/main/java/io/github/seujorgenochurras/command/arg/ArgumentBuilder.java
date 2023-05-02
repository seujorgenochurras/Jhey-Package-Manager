package io.github.seujorgenochurras.command.arg;

import static com.sun.jdi.connect.Connector.Argument;

public class ArgumentBuilder {
   Argument argumentFormat;
   private ArgumentBuilder() {
   }
   public static ArgumentBuilder startBuild(){
      return new ArgumentBuilder();
   }

}
