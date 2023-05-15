package io.github.seujorgenochurras.api.commands;


import io.github.seujorgenochurras.api.domain.Dependency;
import io.github.seujorgenochurras.api.service.MavenService;
import io.github.seujorgenochurras.command.toolbox.CommandConsole;
import io.github.seujorgenochurras.command.toolbox.CommandToolBox;
import io.github.seujorgenochurras.command.ICommand;
import io.github.seujorgenochurras.command.arg.CommandArgumentBuilder;
import io.github.seujorgenochurras.command.arg.flag.pattern.FlagPatternCollection;
import io.github.seujorgenochurras.command.reflections.common.ValidFlagArgumentTypes;
import io.github.seujorgenochurras.command.toolbox.builders.ConsoleListBuilder;

import java.util.ArrayList;

public class JpmCommand implements ICommand {

   private final MavenService mavenService = new MavenService();
   @Override
   public void invoke(CommandToolBox toolBox) {
      String libName = toolBox.commandArgs.getFlagByName("i").getValueAsString();

      ArrayList<Dependency> dependenciesFound = mavenService.searchForDependency(libName);

     CommandConsole commandConsole = toolBox.commandConsole;

     ConsoleListBuilder listBuilder =  commandConsole
             .addNewListPrompt()
              .message("Found " + dependenciesFound.size() + " libs")
              .pageSize(5);

     dependenciesFound.forEach(dependency ->
             listBuilder.newItem()
             .name(dependency.getIdentifier())
             .add());

      String dependencyChosen = commandConsole.prompt(listBuilder).getResult();

      System.out.println(dependencyChosen);
   }

   @Override
   public FlagPatternCollection commandArgsPattern() {
      return CommandArgumentBuilder
              .startBuild()

              .newFlag()
              .aliases("-i", "--install")
              .argType(ValidFlagArgumentTypes.STRING)
              .addFlag()

              .build();
   }

   @Override
   public String[] getNames() {
      return new String[]{"jpm"};
   }

}
