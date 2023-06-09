package io.github.seujorgenochurras.mapper;

import io.github.seujorgenochurras.domain.AbstractPlugin;
import io.github.seujorgenochurras.domain.dependency.Dependency;

import java.util.List;

public interface DependencyManagerFile {
   void addDependency(Dependency dependency);

   <T extends AbstractPlugin> void addPlugin(T plugin);

   List<? extends AbstractPlugin> getPlugins();

   List<Dependency> getDependencies();

   void removeDependency(Dependency dependency);

   void commentDependency(Dependency dependency);

   <T extends AbstractPlugin> void removePlugin(T plugin);

}
