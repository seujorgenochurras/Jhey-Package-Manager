package io.github.seujorgenochurras.api.commands;

import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.ListPromptBuilder;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import io.github.seujorgenochurras.api.domain.Dependency;
import io.github.seujorgenochurras.api.service.DependencyService;
import io.github.seujorgenochurras.command.CommandToolBox;
import io.github.seujorgenochurras.command.ICommand;
import io.github.seujorgenochurras.command.arg.CommandArgumentBuilder;
import io.github.seujorgenochurras.command.arg.flag.FlagPatternCollection;
import io.github.seujorgenochurras.command.reflections.common.ValidFlagArgumentTypes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class JpmCommand implements ICommand {

   private final DependencyService dependencyService = new DependencyService();
   @Override
   public void invoke(CommandToolBox toolBox) {
      String libName = toolBox.commandArgs().getFlagByName("i").getValueAsString();

      ArrayList<Dependency> dependenciesFound = dependencyService.searchForDependency(libName);

      ConsolePrompt prompt = new ConsolePrompt();
      PromptBuilder promptBuilder = prompt.getPromptBuilder();
      ListPromptBuilder listPrompt = promptBuilder
              .createListPrompt()
              .name("dependence")
              .message("Found 10 libs").pageSize(10);

      dependenciesFound.forEach(dependency -> {
         String dependencyName = dependency.getArtifact();
         String dependencyGroupName = dependency.getGroupName();
         String dependencyVersion = dependency.getLatestVersion();

         String dependencyIdentifier = dependencyGroupName + ":" + dependencyName + ":" + dependencyVersion;
         listPrompt
                 .newItem()
                 .text(dependencyIdentifier)
                 .name(dependencyIdentifier)
                 .add();
      });
      listPrompt.addPrompt();

      try {
      HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
      }catch (IOException e){
      }
   }

   @Override
   public FlagPatternCollection commandArgsPattern() {
      return CommandArgumentBuilder
              .startBuild()

              .newFlag()
              .aliases("-i", "--install")
              .argType(ValidFlagArgumentTypes.STRING)
              .addFlag()
              .newFlag()
              .aliases("-f", "--force")
              .argType(ValidFlagArgumentTypes.BOOLEAN)
              .addFlag()

              .build();
   }

   @Override
   public String[] getNames() {
      return new String[]{"jpm"};
   }

}
