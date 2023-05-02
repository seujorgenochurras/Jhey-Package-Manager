package io.github.seujorgenochurras.command;

import static io.github.seujorgenochurras.command.CommandRegister.COMMANDS;

public class CommandHandler {

   public static void handleCliText(String rawCliArgument) {
      Command cliCommand = getCommandFromCliText(rawCliArgument);
      cliCommand.invoke(rawCliArgument.split(" "));
   }

   private static Command getCommandFromCliText(String rawCliArgument) {
      CliArgument cliArgument = new CliArgument(rawCliArgument);
      return cliArgument.getCorrespondentCommand();
   }

   private record CliArgument(String rootText) {
      public String getFirstWord() {
         return rootText.split(" ")[0].replaceAll("\\[|]", "");
      }

      public Command getCorrespondentCommand() {
         return COMMANDS.get(this.getFirstWord());
      }

   }

}




