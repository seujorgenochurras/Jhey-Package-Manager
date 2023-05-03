package io.github.seujorgenochurras.command;

import javax.naming.NameNotFoundException;
import java.util.Objects;

import static io.github.seujorgenochurras.command.CommandRegister.COMMANDS;

public class CommandHandler {

   public static void handleCliText(String rawCliArgument) {
      AbstractCommand cliAbstractCommand = getCommandFromCliText(rawCliArgument);
      cliAbstractCommand.invoke(rawCliArgument.split(" "));
   }

   private static AbstractCommand getCommandFromCliText(String rawCliArgument) {
      CliArgument cliArgument = new CliArgument(rawCliArgument);
      return cliArgument.getCorrespondentCommand();
   }

   private record CliArgument(String rootText) {
      public String getFirstArgument() {
         return getCliArgumentByIndex(0);
      }
      public String getCliArgumentByIndex(int index){
         return rootText.split(" ")[index].replaceAll("\\[|]", "");
      }

      public AbstractCommand getCorrespondentCommand() throws NameNotFoundException {
         AbstractCommand firstAbstractCommand = COMMANDS.get(this.getFirstArgument());
         if(Objects.isNull(firstAbstractCommand)) throw new NameNotFoundException("Cli command not found");

         AbstractCommand subAbstractCommand;
         return null;
      }
   }
}




