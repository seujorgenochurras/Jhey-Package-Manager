package io.github.seujorgenochurras.command;

import io.github.seujorgenochurras.command.reflections.register.CommandRegister;
import java.util.Arrays;

import static io.github.seujorgenochurras.command.reflections.register.CommandRegister.COMMANDS;

public class CliHandler {

   public static void handleCliArguments(String[] rawCliArgs){

      String cliArgsAsString = Arrays.toString(rawCliArgs);
      cliArgsAsString = cliArgsAsString.replace("[", "").replace("]", "").replace(",", "");
      String[] cliCommandSeparatedFromFlags = cliArgsAsString.split("-", 2);
      int commandNameIndex = 0;
      int commandArgsIndex = 1;

      String commandName = cliCommandSeparatedFromFlags[commandNameIndex].trim();
      String commandFlags = cliCommandSeparatedFromFlags[commandArgsIndex].trim();

      ICommand command = COMMANDS.get(commandName);
      command.invoke(command.flagFormatter().formatString(commandFlags));

   }

}
