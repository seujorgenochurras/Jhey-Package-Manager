package io.github.seujorgenochurras.api.service;

import io.github.seujorgenochurras.api.domain.AbstractDependency;

import java.util.List;
import java.util.NoSuchElementException;

public class DependencyService {
   private final List<? extends AbstractDependency> dependencies;

   public DependencyService(List<? extends AbstractDependency> dependencies) {
      this.dependencies = dependencies;
   }

   public AbstractDependency getDependencyByFullName(String dependencyFullName){
      for (AbstractDependency Dependency : dependencies){
         if(Dependency.getFullName().equals(dependencyFullName))
            return Dependency;
      }
      throw new NoSuchElementException("Dependency not found");
   }
}
