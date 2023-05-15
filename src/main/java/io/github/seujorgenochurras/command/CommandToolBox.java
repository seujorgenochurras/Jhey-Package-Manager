package io.github.seujorgenochurras.command;

import de.codeshelf.consoleui.prompt.ConsolePrompt;
import io.github.seujorgenochurras.command.arg.flag.CommandArgs;

public record CommandToolBox(CommandArgs commandArgs) {
   public static final ConsolePrompt consolePrompt = new ConsolePrompt();
}
