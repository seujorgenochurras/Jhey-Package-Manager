package io.github.seujorgenochurras.command.arg.flag;

import java.util.HashMap;

public class CommandArgs extends HashMap<String, Flag> {

   public Flag getFlagByName(String flagName){
      return this.get(flagName);
   }

}
