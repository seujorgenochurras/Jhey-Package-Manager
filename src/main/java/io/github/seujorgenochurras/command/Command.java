package io.github.seujorgenochurras.command;

import jline.internal.Nullable;

import java.util.HashMap;

public abstract class Command {
   @Nullable
   public static HashMap<String, ? extends Command> subCommands;

   public abstract void invoke(String[] params);
   public abstract String getName();

}
