package io.github.seujorgenochurras.api.command.jpm;

import io.github.seujorgenochurras.api.command.jpm.prompt.MavenPrompter;
import io.github.seujorgenochurras.api.domain.IDependency;
import io.github.seujorgenochurras.api.domain.Dependency;
import io.github.seujorgenochurras.api.service.MavenService;
import io.github.seujorgenochurras.command.toolbox.CommandToolBox;
import io.github.seujorgenochurras.command.ICommand;
import io.github.seujorgenochurras.command.arg.CommandArgumentBuilder;
import io.github.seujorgenochurras.command.arg.flag.pattern.FlagPatternCollection;
import io.github.seujorgenochurras.command.reflections.common.ValidFlagArgumentTypes;
import io.github.seujorgenochurras.domain.GradleDependency;
import io.github.seujorgenochurras.domain.GradlewBuildFile;

import java.util.ArrayList;

public class JpmCommand implements ICommand {
   private final MavenService mavenService = new MavenService();

   @Override
   public void invoke(CommandToolBox toolBox) {
      String libName = toolBox.commandArgs.getFlagByName("i").getValueAsString();

      ArrayList<Dependency> dependenciesFound = mavenService.searchForDependency(libName);

      IDependency dependencyChosen = MavenPrompter
         .startPrompt()
         .promptDependencyCollection(dependenciesFound)
         .promptVersion()
         .getResultedDependency();



      GradlewBuildFile dependencyManagerFile = toolBox.gradlewBuildFile;
      dependencyManagerFile.addDependency(new GradleDependency()
              .setGroupName(dependencyChosen.getGroupName())
              .setArtifact(dependencyChosen.getArtifactName())
              .setVersion(dependencyChosen.getVersion()));

      System.out.println(dependencyChosen.getFullName());
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