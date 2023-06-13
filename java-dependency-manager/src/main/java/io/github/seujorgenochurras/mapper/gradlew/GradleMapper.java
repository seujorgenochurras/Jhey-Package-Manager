package io.github.seujorgenochurras.mapper.gradlew;

import io.github.seujorgenochurras.domain.PluginDeclaration;
import io.github.seujorgenochurras.domain.dependency.DependencyDeclaration;
import io.github.seujorgenochurras.domain.manager.gradlew.GradleBuildFileBuilder;
import io.github.seujorgenochurras.mapper.DependencyManagerFile;
import io.github.seujorgenochurras.mapper.DependencyMapper;
import io.github.seujorgenochurras.mapper.gradlew.tree.GradleForest;
import io.github.seujorgenochurras.mapper.gradlew.tree.mapper.GradleForestFileMapper;
import io.github.seujorgenochurras.mapper.gradlew.validator.StringHasSizeGreaterThan;
import io.github.seujorgenochurras.mapper.gradlew.validator.StringStartsWithRegex;
import io.github.seujorgenochurras.mapper.gradlew.validator.StringValidator;
import io.github.seujorgenochurras.mapper.gradlew.validator.WhenStringSplittedWith;
import io.github.seujorgenochurras.mapper.gradlew.validator.chain.GradleValidatorChain;
import io.github.seujorgenochurras.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GradleMapper extends DependencyMapper {

   private final GradleForest gradleForest;
   protected String gradleBuildFileAsString;
   private List<DependencyDeclaration> dependencyDeclarations = new ArrayList<>();
   private List<PluginDeclaration> pluginDeclarations = new ArrayList<>();

   public GradleMapper(File rootFile) {
      super(rootFile);
      this.gradleBuildFileAsString = FileUtils.getFileAsString(rootFile);
      this.gradleForest = GradleForestFileMapper.mapFile(rootFile);
   }

   @Override
   protected DependencyManagerFile map() {
      mapDependencies();
      mapPlugins();

      return GradleBuildFileBuilder.startBuild()
              .dependenciesDeclaration(this.dependencyDeclarations)
              .plugins(this.pluginDeclarations)
              .originFile(this.rootFile)
              .getBuild();
   }

   @Override
   protected void mapDependencies() {
      this.dependencyDeclarations = getDependencyDeclarations();
   }

   protected void mapPlugins() {
      this.pluginDeclarations = getAllPluginDeclarations();
   }

   protected List<PluginDeclaration> getAllPluginDeclarations() {
      GradleValidatorChain<String> pluginDeclarationValidator = GradleValidatorChain
              .initialValidator(new StringValidator())
              .addValidator(new StringHasSizeGreaterThan(16))
              .addValidator(new StringStartsWithRegex("(?<=id).*['\"]"));

      List<PluginDeclaration> plugins = new ArrayList<>();
      gradleForest.getTreeByName("plugins").getNodes()
              .stream()
              .filter(possiblePluginDeclarationNode -> pluginDeclarationValidator.validate(possiblePluginDeclarationNode.getTextContents()))
              .forEach(pluginDeclarationNode ->
                      plugins.add(new PluginDeclaration(pluginDeclarationNode.getTextContents(), 0)));

      return plugins;
   }

   protected List<DependencyDeclaration> getDependencyDeclarations() {
      String implementationDeclarationRegex = "(testImplementation|implementation|runtime_only|testRuntimeOnly|testCompileOnly|runtimeOnly|api|compileOnly|compileOnlyApi).*";

      GradleValidatorChain<String> dependencyDeclarationValidator = GradleValidatorChain
              .initialValidator(new StringValidator())
              .addValidator(new StringHasSizeGreaterThan(16))
              .addValidator(new StringStartsWithRegex(implementationDeclarationRegex))
              .addValidator(new WhenStringSplittedWith(":").sizeGreaterThen(1));

      List<DependencyDeclaration> dependenciesAsString = new ArrayList<>();

      gradleForest.getTreeByName("dependencies").getNodes()
              .stream()
              .filter(probableDependencyDeclarationNode ->
                      dependencyDeclarationValidator.validate(probableDependencyDeclarationNode.getTextContents()))
              .forEach(dependencyDeclarationNode -> {
                 String nodeContents = dependencyDeclarationNode.getTextContents();
                 dependenciesAsString.add(new DependencyDeclaration(nodeContents));
              });
      return dependenciesAsString;
   }

}
