package io.github.seujorgenochurras.api.command.jpm.prompt;

import io.github.seujorgenochurras.api.domain.IDependency;

import java.util.Collection;

public class MavenPrompter {
   private MavenPrompter(){}

   public static MavenPrompter startPrompt(){
      return new MavenPrompter();
   }
   public <T extends Collection<? extends IDependency>> SingleDependencyPrompter promptDependencyCollection(T dependencyCollection){
      return DependencyCollectionPrompter
              .startPrompt()
              .promptDependencies(dependencyCollection)
              .getDependencyChosen();
   }
}
