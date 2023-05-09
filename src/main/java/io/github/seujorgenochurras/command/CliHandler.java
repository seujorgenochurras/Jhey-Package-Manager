package io.github.seujorgenochurras.command;

import io.github.seujorgenochurras.command.reflections.register.CommandRegister;
import java.util.Arrays;

public class CliHandler {

   public static void handleCliArguments(String[] rawCliArgs){
      String cliArgsAsString = Arrays.toString(rawCliArgs);
      String[] cliCommandSeparatedFromFlags = cliArgsAsString.split("-", 1);
      int commandNameIndex = 0;
      int commandFlagsIndex = 0;

      String commandName = cliCommandSeparatedFromFlags[commandNameIndex];
      String commandFlags = cliCommandSeparatedFromFlags[commandFlagsIndex];
      ICommand command = CommandRegister.COMMANDS.get(commandName);

      command.invoke(command.flagFormatter().formatString(commandFlags));

   }

}
