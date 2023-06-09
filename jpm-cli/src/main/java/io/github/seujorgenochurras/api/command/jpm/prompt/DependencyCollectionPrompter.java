package io.github.seujorgenochurras.api.command.jpm.prompt;

import io.github.seujorgenochurras.api.domain.IDependency;
import io.github.seujorgenochurras.api.service.DependencyService;
import io.github.seujorgenochurras.command.toolbox.CommandConsole;
import static io.github.seujorgenochurras.command.toolbox.CommandConsole.*;
import io.github.seujorgenochurras.command.toolbox.builders.ConsoleListBuilder;

import java.util.Collection;

public class DependencyCollectionPrompter {
   private SingleDependencyPrompter dependencyChosen;
   private static final CommandConsole console = new CommandConsole();

   private DependencyCollectionPrompter() {
   }

   public static DependencyCollectionPrompter startPrompt() {
      return new DependencyCollectionPrompter();
   }

   public DependencyCollectionPrompter promptDependencies(Collection<? extends IDependency> dependencies) {
      ConsoleListBuilder listBuilder = console
              .addNewListPrompt()
              .message("Found " + dependencies.size() + " libs")
              .pageSize(5);

      dependencies.forEach(dependency ->
              listBuilder.newItem()
                      .name(dependency.getFullName())
                      .add());


      DependencyService dependencyService = new DependencyService(dependencies);

      ConsolePromptAnswer answer = console.prompt(listBuilder);

      IDependency promptResult = dependencyService.getDependencyByFullName(answer.getResult());

      this.dependencyChosen = SingleDependencyPrompter.dependencyToPrompt(promptResult);

      return this;
   }

   public SingleDependencyPrompter getDependencyChosen() {
      return dependencyChosen;
   }
}
