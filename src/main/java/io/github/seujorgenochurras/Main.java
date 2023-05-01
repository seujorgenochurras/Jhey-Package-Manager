package io.github.seujorgenochurras;


import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import org.fusesource.jansi.AnsiConsole;

import java.io.IOException;
import java.util.HashMap;

public class Main {
   public static void main(String[] args) throws IOException {
      AnsiConsole.systemInstall();

      ConsolePrompt prompt = new ConsolePrompt();
      PromptBuilder promptBuilder = prompt.getPromptBuilder();

      promptBuilder.createCheckboxPrompt()
              .name("mathTeest")
              .message("What's the result of 1+1?")
              .newItem().text("2").add()
              .newItem().text("two").add()
              .newItem().text("dva").add()
              .newItem().text("Quattro").add()
              .addPrompt();

      promptBuilder.createChoicePrompt()
              .name("mathTeset")
              .message("What's the result of 1+1?")
              .newItem().name("2").key('a').add()
              .newItem().message("two").key('b').add()
              .newItem().name("dva").key('c').add()
              .newItem().name("Quattro").key('d').add()
              .addPrompt();
      promptBuilder.createListPrompt()
              .name("mathTese2")
              .message("What's the result of 1+1?")
              .newItem().name("2").text("2").add()
              .newItem().name("bas").text("bas").add()
              .newItem().name("dva").text("dva").add()
              .newItem().name("Quattro").text("Quattro").add()
              .addPrompt();

              promptBuilder.createConfirmPromp()
              .name("mathTe2st")
              .message("What's the result of 1+1?")
              .addPrompt();

      HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
      System.out.println(result);
   }
}