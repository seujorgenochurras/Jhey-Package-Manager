package io.github.seujorgenochurras.command.reflections.register;

import io.github.seujorgenochurras.command.AbstractCommand;
import io.github.seujorgenochurras.command.annotation.SubCommand;
import io.github.seujorgenochurras.command.reflections.CommandFetcher;
import io.github.seujorgenochurras.command.reflections.CommandTypes;
import org.reflections.Configuration;
import org.reflections.util.ConfigurationBuilder;

import java.util.*;

public class CommandRegister {
   private CommandRegister(){}

   public static final HashMap<String, AbstractCommand> commands;

   static {
      commands =
              CommandFetcher
                      .startFetchingFor(CommandTypes.COMMAND)
                      .getAllClassesAnnotatedWithCommand()
                      .warnIfCommandIsNotExtendingAbstractCommand()
                      .getCommandsAndFinishFetching();

      commands.forEach((commandName, commandInstance )-> addAllSubCommandsOf(commandInstance));
   }

   private static void addAllSubCommandsOf(AbstractCommand command){
      command.subCommands.putAll(getSubCommandsOf(command));
      //TODO check if a subcommand has subcommands, and add them to the subcommand list
   }
   private static Map<String, AbstractCommand> getSubCommandsOf(AbstractCommand abstractCommand) {
      if(commandHasSubclasses(abstractCommand)) return Collections.emptyMap();
      Class<?>[] abstractCommandInnerClasses = abstractCommand.getClass().getDeclaredClasses();
      Map<String, AbstractCommand> abstractCommands = new HashMap<>();

      for (Class<?> ignored : abstractCommandInnerClasses) {
         //TODO NOT WORKING, need to verify for only one class
         abstractCommands.putAll(
         CommandFetcher
                 .startFetchingFor(abstractCommand.getClass().getPackageName(), CommandTypes.SUB_COMMAND)
                 .getAllClassesAnnotatedWithCommand()
                 .warnIfCommandIsNotExtendingAbstractCommand()
                 .getSubCommandsAndFinishFetching());
      }
      return abstractCommands;
   }
   private static boolean commandHasSubclasses(AbstractCommand command){
      return command.getClass().getDeclaredClasses().length != 0;
   }


}
