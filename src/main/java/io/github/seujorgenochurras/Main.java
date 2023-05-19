package io.github.seujorgenochurras;

import de.codeshelf.consoleui.elements.ConfirmChoice;
import de.codeshelf.consoleui.prompt.ConfirmPrompt;
import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import io.github.seujorgenochurras.command.cli.CliHandler;
import io.github.seujorgenochurras.command.toolbox.builders.ConsoleConfirmBuilder;
import org.fusesource.jansi.AnsiConsole;

public class Main {
   public static void main(String[] args) {
      AnsiConsole.systemInstall(); //needed because windows has no AnsiConsole by default

      CliHandler.handleCliArguments(args);

      ConsolePrompt prompt = new ConsolePrompt();
      PromptBuilder promptBuilder = prompt.getPromptBuilder();


      promptBuilder.createConfirmPromp()
              .name("delivery")
              .message("Is this pizza for delivery?")
              .defaultValue(ConfirmChoice.ConfirmationValue.YES)
              .addPrompt();
   }
}
