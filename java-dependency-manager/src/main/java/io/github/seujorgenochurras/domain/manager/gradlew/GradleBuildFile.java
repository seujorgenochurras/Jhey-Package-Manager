package io.github.seujorgenochurras.domain.manager.gradlew;

import io.github.seujorgenochurras.domain.plugin.Plugin;
import io.github.seujorgenochurras.domain.dependency.Dependency;
import io.github.seujorgenochurras.mapper.DependencyManagerFile;
import io.github.seujorgenochurras.mapper.gradlew.tree.GradleForest;
import io.github.seujorgenochurras.mapper.gradlew.tree.GradleForestTransformer;
import io.github.seujorgenochurras.mapper.gradlew.tree.node.GradleNode;
import io.github.seujorgenochurras.mapper.gradlew.tree.node.GradleTree;
import io.github.seujorgenochurras.utils.NotFoundException;

import java.io.File;
import java.util.List;

import static io.github.seujorgenochurras.utils.StringUtils.weakEquals;

public class GradleBuildFile implements DependencyManagerFile {
   private GradleForest gradleForest;
   private List<Dependency> dependencies;
   private List<Plugin> plugins;
   private File originFile;

   private GradleTree dependenciesTree;
   private GradleTree pluginsTree;

   public GradleBuildFile setDependencies(List<Dependency> dependencies) {
      this.dependencies = dependencies;
      return this;
   }

   public void setOriginFile(File originFile) {
      this.originFile = originFile;
   }

   @Override
   public List<? extends Plugin> getPlugins() {
      return plugins;
   }

   @Override
   public List<Dependency> getDependencies() {
      return dependencies;
   }

   public GradleBuildFile setPlugins(List<Plugin> plugins) {
      this.plugins = plugins;
      return this;
   }

   @Override
   public void addDependency(Dependency dependency) {
      String declaration = dependency.getDependencyType().typeName + " (\""
              + dependency.getGroupName().trim()
              + ":"
              + dependency.getArtifact().trim()
              + ":"
              + dependency.getVersion().trim()
              + "\")";

      dependenciesTree.addNode(new GradleNode(declaration));
      GradleForestTransformer.transform(gradleForest, originFile);
      this.dependencies.add(dependency);
   }


   @Override
   public <T extends Plugin> void addPlugin(T plugin) {
      String declaration = "id '" + plugin.getId().trim() + "'\n";

      pluginsTree.addNode(new GradleNode(declaration));

      GradleForestTransformer.transform(gradleForest, originFile);
      this.plugins.add(plugin);
   }

   @Override
   public void removeDependency(Dependency dependency) {
      List<GradleNode> dependenciesNode = dependenciesTree.getNodes();
      String dependencyDeclaration = dependency.getDeclaration();
      dependenciesNode.remove(dependenciesNode.stream()
              .filter(node -> weakEquals(node.getTextContents(), dependencyDeclaration))
              .findFirst()
              .orElseThrow(() -> new NotFoundException("Dependency :'" + dependency.getDeclaration() + "' not found")));
      dependencies.remove(dependency);

      GradleForestTransformer.transform(gradleForest, originFile);
   }


   @Override
   public <T extends Plugin> void removePlugin(T plugin) {
      //Fuck you
   }

   @Override
   public void commentDependency(Dependency dependency) {
      List<GradleNode> dependenciesNode = dependenciesTree.getNodes();
      String dependencyDeclaration = dependency.getDeclaration();
      dependenciesNode.remove(dependenciesNode.stream()
              .filter(node -> weakEquals(node.getTextContents(), dependencyDeclaration))
              .findFirst()
              .orElseThrow(() -> new NotFoundException("Dependency not found")));

   }

   @Override
   public String toString() {
      return "GradleBuildFile{" +
              "plugins=" + plugins +
              ", dependencies=" + dependencies +
              '}';
   }

   public GradleBuildFile setGradleForest(GradleForest gradleForest) {
      this.gradleForest = gradleForest;
      this.pluginsTree = gradleForest.getTreeByName("plugins");
      this.dependenciesTree = gradleForest.getTreeByName("dependencies");
      return this;
   }
}
