package io.github.seujorgenochurras.api.service;

import io.github.seujorgenochurras.api.domain.IDependency;

import java.util.List;
import java.util.NoSuchElementException;

public class DependencyService {
   private final List<? extends IDependency> dependencies;

   public DependencyService(List<? extends IDependency> dependencies) {
      this.dependencies = dependencies;
   }

   public IDependency getDependencyByFullName(String dependencyFullName){
      for (IDependency Dependency : dependencies){
         if(Dependency.getFullName().equals(dependencyFullName))
            return Dependency;
      }
      throw new NoSuchElementException("Dependency not found " + dependencyFullName);
   }
}
