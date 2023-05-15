package io.github.seujorgenochurras.command.toolbox;

import de.codeshelf.consoleui.elements.PromptableElementIF;
import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import io.github.seujorgenochurras.command.toolbox.builders.CommandConsoleBuilder;
import io.github.seujorgenochurras.command.toolbox.builders.ConsoleListBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class CommandConsole extends ConsolePrompt {

   private static final Logger logger = Logger.getLogger(CommandConsole.class.getName());


   public ConsolePromptAnswer prompt(CommandConsoleBuilder builder){
      return new ConsolePromptAnswer(prompt(builder.build()));
   }

   @Override
   public HashMap<String, ? extends PromtResultItemIF> prompt(List<PromptableElementIF> promptableElementList)  {
      try {
         System.out.println(promptableElementList.size());
        return super.prompt(promptableElementList);
      }catch (IOException e){
         logger.severe("Something went terrible wrong when prompting");
         e.printStackTrace();
         Thread.currentThread().interrupt();
         return new HashMap<>();
      }
   }
   public PreConsoleListBuilder addNewListPrompt(){
      return new PreConsoleListBuilder();
   }

   public static final class PreConsoleListBuilder{
      public ConsoleListBuilder message(String message){
         return new ConsoleListBuilder(message);
      }
   }

   public static final class ConsolePromptAnswer{
      private final HashMap<String, ? extends PromtResultItemIF> rawResult;
      public ConsolePromptAnswer(HashMap<String, ? extends PromtResultItemIF> rawResult) {
         this.rawResult = rawResult;
      }

      public String getResult(){
         return rawResult.toString();
      }

   }
}
