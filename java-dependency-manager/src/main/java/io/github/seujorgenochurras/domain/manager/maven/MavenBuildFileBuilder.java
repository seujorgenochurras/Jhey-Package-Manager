package io.github.seujorgenochurras.domain.manager.maven;

import io.github.seujorgenochurras.domain.AbstractPlugin;
import io.github.seujorgenochurras.domain.dependency.Dependency;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MavenBuildFileBuilder {
   private File rootFile;
   private List<Dependency> dependencies = new ArrayList<>();
   private List<AbstractPlugin> plugins = new ArrayList<>();
   private MavenBuildFileBuilder() {
   }

   public static MavenBuildFileBuilder startBuild() {
      return new MavenBuildFileBuilder();
   }

   public MavenBuildFileBuilder rootFile(File rootFile) {
      this.rootFile = rootFile;
      return this;
   }

   public MavenBuildFile getBuildResult() {
      return new MavenBuildFile(rootFile)
              .setDependencies(dependencies)
              .setPlugins(plugins);
   }

   public MavenBuildFileBuilder dependencies(List<Dependency> dependencies) {
      this.dependencies = dependencies;
      return this;
   }

   public MavenBuildFileBuilder plugins(List<AbstractPlugin> plugins) {
      this.plugins = plugins;
      return this;
   }
}
