package io.github.seujorgenochurras.api.command.jpm.prompt;

import io.github.seujorgenochurras.api.domain.IDependency;
import io.github.seujorgenochurras.api.service.DependencyService;
import io.github.seujorgenochurras.command.toolbox.CommandConsole;
import io.github.seujorgenochurras.command.toolbox.builders.ConsoleListBuilder;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import static io.github.seujorgenochurras.command.toolbox.CommandConsole.ConsolePromptAnswer;
import static io.github.seujorgenochurras.command.toolbox.CommandConsole.PreConsoleListBuilder;

public class DependencyCollectionPrompter {
   private static final CommandConsole console = new CommandConsole();
   private static final Logger logger = Logger.getLogger(DependencyCollectionPrompter.class.getName());
   private SingleDependencyPrompter dependencyChosen;


   private DependencyCollectionPrompter() {
   }

   public static DependencyCollectionPrompter startPrompt() {
      return new DependencyCollectionPrompter();
   }

   public <T extends Collection<? extends IDependency>> DependencyCollectionPrompter promptDependenciesAsync(
           CompletableFuture<T> completableDependencies) {

      PreConsoleListBuilder preListBuilder = console
              .addNewListPrompt();

      Collection<? extends IDependency> dependencies = tryGetCompletableFuture(completableDependencies);

      ConsoleListBuilder listBuilder = preListBuilder
              .message("Found " + dependencies.size() + " dependencies")
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

   public <T> T tryGetCompletableFuture(CompletableFuture<T> completableFuture) {
      try {
         return completableFuture.get();
      } catch (InterruptedException | ExecutionException e) {
         logger.severe("Something went terrible wrong");
         e.printStackTrace();
         return null;
      }
   }
}
