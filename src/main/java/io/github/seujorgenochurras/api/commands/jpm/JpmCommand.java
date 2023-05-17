package io.github.seujorgenochurras.api.commands.jpm;


import io.github.seujorgenochurras.api.domain.AbstractDependency;
import io.github.seujorgenochurras.api.domain.Dependency;
import io.github.seujorgenochurras.api.service.DependencyService;
import io.github.seujorgenochurras.api.service.MavenService;
import io.github.seujorgenochurras.command.toolbox.CommandConsole;
import static io.github.seujorgenochurras.command.toolbox.CommandConsole.*;
import io.github.seujorgenochurras.command.toolbox.CommandToolBox;
import io.github.seujorgenochurras.command.ICommand;
import io.github.seujorgenochurras.command.arg.CommandArgumentBuilder;
import io.github.seujorgenochurras.command.arg.flag.pattern.FlagPatternCollection;
import io.github.seujorgenochurras.command.reflections.common.ValidFlagArgumentTypes;
import io.github.seujorgenochurras.command.toolbox.builders.ConsoleListBuilder;

import java.util.ArrayList;

public class JpmCommand implements ICommand {

   private final MavenService mavenService = new MavenService();

   private CommandToolBox toolBox;
   private DependencyService dependencyService;
   @Override
   public void invoke(CommandToolBox toolBox) {
      this.toolBox = toolBox;

      ArrayList<Dependency> dependenciesFound = mavenService.searchForDependency("libName");

      AbstractDependency DependencyChosen = DependencyPrompter
              .startPrompt()
              .promptDependencies(dependenciesFound)
              .getDependencyChosen()
              .promptVersion()
              .finishPromptAndGetChosenDependency();
//
//      DependencyManagerFile dependencyManagerFile = toolBox.getDependencyManagerFile();
//      dependencyManagerFile.addDependency(DependencyChosen);

      System.out.println(DependencyChosen);
   }

   private Dependency getDependencyChosen(){
      String flagDependencyName = toolBox.commandArgs.getFlagByName("i").getValueAsString();

      ArrayList<Dependency> dependenciesFound = mavenService.searchForDependency(flagDependencyName);
      setDependencyService(dependenciesFound);

      String dependencyChosenName = promptDependency(dependenciesFound).getResult();

      return dependencyService.getDependencyByFullName(dependencyChosenName);
   }

   private ConsolePromptAnswer promptDependency(ArrayList<Dependency> dependenciesToPrompt){
      CommandConsole commandConsole = toolBox.commandConsole;

      ConsoleListBuilder listBuilder =  commandConsole
              .addNewListPrompt()
              .message("Found " + dependenciesToPrompt.size() + " libs")
              .pageSize(5);

      dependenciesToPrompt.forEach(dependency ->
              listBuilder.newItem()
                      .name(dependency.getFullName())
                      .add());

      return commandConsole.prompt(listBuilder);
   }
   private void setDependencyService(ArrayList<Dependency> dependencies){
      this.dependencyService = new DependencyService(dependencies);
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
