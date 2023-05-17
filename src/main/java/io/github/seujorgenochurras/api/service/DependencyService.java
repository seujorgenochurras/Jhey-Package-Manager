package io.github.seujorgenochurras.api.service;

import io.github.seujorgenochurras.api.domain.Dependency;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class DependencyService {
   private final ArrayList<Dependency> dependencies;

   public DependencyService(ArrayList<Dependency> dependencies) {
      this.dependencies = dependencies;
   }
   public Dependency getDependencyByFullName(String dependencyFullName){
      for (Dependency dependency : dependencies){
         if(dependency.getFullName().equals(dependencyFullName))
            return dependency;
      }
      throw new NoSuchElementException("Dependency not found");
   }
}
