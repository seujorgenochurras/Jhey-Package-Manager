package io.github.seujorgenochurras.command.arg;

import io.github.seujorgenochurras.command.arg.flag.Flag;

import java.util.HashMap;

public class CommandArgs extends HashMap<String, Flag> {
   public Flag getFlagByName(String flagName){
      return this.get(flagName);
   }

}
