package io.github.seujorgenochurras.command;

import jline.internal.Nullable;

import java.util.HashMap;

public abstract class AbstractCommand {
   @Nullable
   public HashMap<String, AbstractCommand> subCommands = new HashMap<>();

   public abstract void invoke(String[] params);

   public boolean hasSubCommands(){
      return !subCommands.isEmpty();
   }
}
