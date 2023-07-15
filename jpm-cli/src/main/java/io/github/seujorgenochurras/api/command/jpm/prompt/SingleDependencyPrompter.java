package io.github.seujorgenochurras.api.command.jpm.prompt;

import de.codeshelf.consoleui.elements.ConfirmChoice;
import io.github.seujorgenochurras.api.domain.Dependency;
import io.github.seujorgenochurras.api.domain.IDependency;
import io.github.seujorgenochurras.api.service.DependencyService;
import io.github.seujorgenochurras.api.service.MavenService;
import io.github.seujorgenochurras.command.toolbox.CommandConsole;
import io.github.seujorgenochurras.command.toolbox.builders.ConsoleListBuilder;

import java.util.List;

public class SingleDependencyPrompter {
   private IDependency resultedDependency;

   private static final CommandConsole console = new CommandConsole();
   private static final MavenService mavenService = new MavenService();

   private SingleDependencyPrompter(IDependency dependencyToPrompt) {
      this.resultedDependency = dependencyToPrompt;
   }

   public static SingleDependencyPrompter dependencyToPrompt(IDependency dependencyToPrompt) {
      return new SingleDependencyPrompter(dependencyToPrompt);
   }


   public SingleDependencyPrompter promptVersion() {
      if(!isCurrentVersionRight()) {
         List<Dependency> dependencyVersionsToPrompt = mavenService.searchVersionsOf(resultedDependency);

         DependencyService dependencyService = new DependencyService(dependencyVersionsToPrompt);

         ConsoleListBuilder listBuilder = getListBuilderOfDependencies(dependencyVersionsToPrompt);
         String dependencyName = console.prompt(listBuilder).getResult();
         resultedDependency = dependencyService.getDependencyByFullName(dependencyName);
      }
      return this;
   }

   private boolean isCurrentVersionRight(){
      return promptCurrentVersionIsRight().getResultAsBoolean();
   }


   private CommandConsole.ConsolePromptAnswer promptCurrentVersionIsRight(){
       return console.prompt(console.addNewConfirmBuilder()
                 .name("isVersionRight")
                 .message("You're about to install version " + resultedDependency.getVersion() + " is it right?")
                 .defaultValue(ConfirmChoice.ConfirmationValue.YES));
   }

   private ConsoleListBuilder getListBuilderOfDependencies(List<? extends IDependency> dependencies){
      ConsoleListBuilder listBuilder = console
              .addNewListPrompt()
              .message("Chose version")
              .pageSize(5);

      dependencies.forEach(dependency ->
              listBuilder.newItem()
                      .name(dependency.getVersion())
                      .value(dependency.getFullName())
                      .add());
      return listBuilder;
   }

   public IDependency getResultedDependency(){
      return resultedDependency;
   }
}
