package io.github.seujorgenochurras.mapper.gradlew;

import io.github.seujorgenochurras.domain.plugin.Plugin;
import io.github.seujorgenochurras.domain.dependency.Dependency;
import io.github.seujorgenochurras.domain.dependency.util.StringToDependencyParser;
import io.github.seujorgenochurras.domain.manager.gradlew.GradleBuildFileBuilder;
import io.github.seujorgenochurras.domain.plugin.util.StringToPluginParser;
import io.github.seujorgenochurras.mapper.DependencyManagerFile;
import io.github.seujorgenochurras.mapper.DependencyMapper;
import io.github.seujorgenochurras.mapper.gradlew.tree.GradleForest;
import io.github.seujorgenochurras.mapper.gradlew.tree.mapper.GradleForestFileMapper;
import io.github.seujorgenochurras.mapper.gradlew.validator.*;
import io.github.seujorgenochurras.mapper.gradlew.validator.chain.GradleValidatorChain;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GradleMapper extends DependencyMapper {

   private final GradleForest gradleForest;
   private List<Dependency> dependencyDeclarations = new ArrayList<>();
   private List<Plugin> pluginDeclarations = new ArrayList<>();

   public GradleMapper(File rootFile) {
      super(rootFile);
      this.gradleForest = GradleForestFileMapper.mapFile(rootFile);
   }

   @Override
   protected DependencyManagerFile map() {
      mapDependencies();
      mapPlugins();

      return GradleBuildFileBuilder.startBuild()
              .dependencies(this.dependencyDeclarations)
              .plugins(this.pluginDeclarations)
              .originFile(this.rootFile)
              .gradleForest(gradleForest)
              .getBuild();
   }

   @Override
   protected void mapDependencies() {
      this.dependencyDeclarations = getDependencyDeclarations();
   }

   protected void mapPlugins() {
      this.pluginDeclarations = getAllPluginDeclarations();
   }

   protected List<Plugin> getAllPluginDeclarations() {
      GradleValidatorChain<String> pluginDeclarationValidator = GradleValidatorChain
              .initialValidator(new StringValidator())
              .addValidator(new StringHasSizeGreaterThan(5))
              .addValidator(new StringStartsWithRegex("id"));

      List<Plugin> plugins = new ArrayList<>();
      gradleForest.getTreeByName("plugins").getNodes()
              .stream()
              .filter(possiblePluginDeclarationNode -> pluginDeclarationValidator.validate(possiblePluginDeclarationNode.getTextContents()))
              .forEach(pluginDeclarationNode ->
                      plugins.add(StringToPluginParser.stringToPlugin(pluginDeclarationNode.getTextContents())));
      return plugins;
   }

   protected List<Dependency> getDependencyDeclarations() {
      String implementationDeclarationRegex = "(testImplementation|implementation|runtime_only|testRuntimeOnly|testCompileOnly|runtimeOnly|api|compileOnly|compileOnlyApi).*";

      GradleValidatorChain<String> dependencyDeclarationValidator = GradleValidatorChain
              .initialValidator(new StringValidator())
              .addValidator(new StringHasSizeGreaterThan(16))
              .addValidator(new StringStartsWithRegex(implementationDeclarationRegex))
              .addValidator(new WhenStringSplittedWith(":").arraySizeGreaterThen(1));

      List<Dependency> dependenciesDeclarations = new ArrayList<>();

      gradleForest.getTreeByName("dependencies").getNodes()
              .stream()
              .filter(probableDependencyDeclarationNode ->
                      dependencyDeclarationValidator.validate(probableDependencyDeclarationNode.getTextContents()))
              .forEach(dependencyDeclarationNode -> {
                 String nodeContents = dependencyDeclarationNode.getTextContents();
                 dependenciesDeclarations.add(StringToDependencyParser.stringToDependency(nodeContents));
              });
      return dependenciesDeclarations;
   }

}
