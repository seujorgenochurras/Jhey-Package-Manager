package io.github.seujorgenochurras.command;

import io.github.seujorgenochurras.command.reflections.register.CommandRegister;

public class CliHandler {

   public static void handleCliArguments(String[] rawCliArgs){
      String cliArgsAsString = rawCliArgs.toString();
      String[] cliArgSeparatedFromFlags = cliArgsAsString.split("-", 1);
      int commandNameIndex = 0;
      int commandFlagsIndex = 0;

      String commandName = cliArgSeparatedFromFlags[commandNameIndex];
      String commandFlags = cliArgSeparatedFromFlags[commandFlagsIndex];
      ICommand command = CommandRegister.COMMANDS.get(commandName);

      command.invoke(command.flagFormatter().formatString(commandFlags));

   }

}
