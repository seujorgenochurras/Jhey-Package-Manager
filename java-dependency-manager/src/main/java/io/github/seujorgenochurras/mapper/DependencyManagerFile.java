package io.github.seujorgenochurras.mapper;

import io.github.seujorgenochurras.domain.plugin.Plugin;
import io.github.seujorgenochurras.domain.dependency.Dependency;

import java.util.List;

public interface DependencyManagerFile {
   void addDependency(Dependency dependency);

   <T extends Plugin> void addPlugin(T plugin);

   List<? extends Plugin> getPlugins();

   List<Dependency> getDependencies();

   void removeDependency(Dependency dependency);

   void commentDependency(Dependency dependency);

   <T extends Plugin> void removePlugin(T plugin);

}
