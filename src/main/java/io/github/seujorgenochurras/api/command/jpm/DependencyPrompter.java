package io.github.seujorgenochurras.api.command.jpm;

import io.github.seujorgenochurras.api.domain.IDependency;
import io.github.seujorgenochurras.api.domain.Dependency;
import io.github.seujorgenochurras.api.service.DependencyService;
import io.github.seujorgenochurras.api.service.MavenService;
import io.github.seujorgenochurras.command.toolbox.CommandConsole;
import io.github.seujorgenochurras.command.toolbox.builders.ConsoleListBuilder;

import java.util.ArrayList;
import java.util.List;

public class DependencyPrompter {
   private DependencyChosen dependencyChosen;

   private static final CommandConsole console = new CommandConsole();

   private DependencyPrompter() {
   }

   public static DependencyPrompter startPrompt() {
      return new DependencyPrompter();
   }

   public DependencyPrompter promptDependencies(List<Dependency> dependencies) {
      ConsoleListBuilder listBuilder = console
              .addNewListPrompt()
              .message("Found " + dependencies.size() + " libs")
              .pageSize(5);

      dependencies.forEach(dependency ->
              listBuilder.newItem()
                      .name(dependency.getFullName())
                      .add());


      DependencyService dependencyService = new DependencyService(dependencies);

      IDependency dependencyChosenAsDependency = dependencyService.getDependencyByFullName(console.prompt(listBuilder).getResult());
      dependencyChosen = new DependencyChosen(dependencyChosenAsDependency);
      return this;
   }

   public DependencyChosen getDependencyChosen() {
      return dependencyChosen;
   }

   public static final class DependencyChosen {
      private final IDependency dependency;

      private IDependency dependencyVersionChosen;

      public DependencyChosen(IDependency dependency) {
         this.dependency = dependency;
      }

      public DependencyChosen promptVersion() {
         ConsoleListBuilder consoleListBuilder = console.addNewListPrompt()
                 .message("Select a version")
                 .pageSize(5);

         List<MavenService.SimpleDependency> versions = getDependencyVersions();

         versions.forEach(dependencyVersion ->
                 consoleListBuilder.newItem()
                         .name(dependencyVersion.getVersion())
                         .value(dependencyVersion.getFullName())
                         .add());

         DependencyService versionsService = new DependencyService(versions);
         this.dependencyVersionChosen = versionsService.getDependencyByFullName(console.prompt(consoleListBuilder).getResult());

         return this;
      }

      public IDependency finishPromptAndGetChosenDependency() {
         return this.dependencyVersionChosen;
      }

      private ArrayList<MavenService.SimpleDependency> getDependencyVersions() {
         return new MavenService().searchVersionsOf(dependency);
      }
   }
}
