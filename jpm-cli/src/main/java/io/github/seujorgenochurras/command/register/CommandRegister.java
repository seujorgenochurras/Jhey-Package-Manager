package io.github.seujorgenochurras.command.register;

import io.github.seujorgenochurras.api.command.jpm.JpmCommand;
import io.github.seujorgenochurras.command.ICommand;

import java.util.*;

public class CommandRegister {
   private CommandRegister(){}

   public static final HashMap<String, ICommand> COMMANDS = new HashMap<>();

   static {
      COMMANDS.put( "jpm", new JpmCommand());
   }

}
