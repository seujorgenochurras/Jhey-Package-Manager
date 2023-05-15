package io.github.seujorgenochurras;

import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.ListPromptBuilder;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import io.github.seujorgenochurras.api.domain.Dependency;
import io.github.seujorgenochurras.api.service.DependencyService;
import io.github.seujorgenochurras.command.cli.CliHandler;
import jline.TerminalFactory;
import org.fusesource.jansi.AnsiConsole;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
   public static void main(String[] args) {
      AnsiConsole.systemInstall();
      CliHandler.handleCliArguments(args);
   }
}
