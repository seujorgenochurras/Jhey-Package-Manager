package io.github.seujorgenochurras.domain.manager.gradlew;

import io.github.seujorgenochurras.domain.PluginDeclaration;
import io.github.seujorgenochurras.domain.dependency.DependencyDeclaration;

import java.io.File;
import java.util.List;

public class GradleBuildFileBuilder {
   private GradleBuildFileBuilder() {
   }

   private final GradleBuildFile gradleBuildFile = new GradleBuildFile();

   public static GradleBuildFileBuilder startBuild() {
      return new GradleBuildFileBuilder();
   }

   public GradleBuildFileBuilder dependenciesDeclaration(List<DependencyDeclaration> dependencyList) {
      this.gradleBuildFile.setDependencies(dependencyList);
      return this;
   }

   public GradleBuildFileBuilder plugins(List<PluginDeclaration> plugins) {
      this.gradleBuildFile.setPlugins(plugins);
      return this;
   }

   public GradleBuildFileBuilder originFile(File file) {
      this.gradleBuildFile.setOriginFile(file);
      return this;
   }

   public GradleBuildFile getBuild() {
      return this.gradleBuildFile;
   }

}
