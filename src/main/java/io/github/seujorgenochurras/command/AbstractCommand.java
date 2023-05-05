package io.github.seujorgenochurras.command;

import jline.internal.Nullable;

import java.util.HashMap;

public abstract class AbstractCommand {

   private final String[] commandNames;

   protected AbstractCommand(String ...commandNames) {
      this.commandNames = commandNames;
   }
   @Nullable
   public HashMap<String, AbstractCommand> subCommands = new HashMap<>();

   public abstract void invoke(String[] params);

   public String[] getCommandNames() {
      return commandNames;
   }
}
