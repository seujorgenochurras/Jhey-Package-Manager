package io.github.seujorgenochurras.command.cli;

import io.github.seujorgenochurras.command.toolbox.CommandToolBox;
import io.github.seujorgenochurras.command.ICommand;
import io.github.seujorgenochurras.command.cli.utils.StringUtils;

import static io.github.seujorgenochurras.command.reflections.register.CommandRegister.COMMANDS;

public class CliHandler {
   private CliHandler() {
   }

   public static void handleCliArguments(String... rawCliArgs) {
      String cliArgsAsString = StringUtils.removeArraySyntaxFromRawStringArr(rawCliArgs);
      String[] cliCommandSeparatedFromFlags = cliArgsAsString.split("-", 2);

      int commandNameIndex = 0;
      int commandArgsIndex = 1;

      String commandName = cliCommandSeparatedFromFlags[commandNameIndex].trim();
      String commandFlags = cliCommandSeparatedFromFlags[commandArgsIndex].trim();

      ICommand command = COMMANDS.get(commandName);
      CommandToolBox toolBox = new CommandToolBox(command.flagFormatter().formatString(commandFlags));
      command.invoke(toolBox);
   }
}
