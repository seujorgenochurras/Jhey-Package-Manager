package io.github.seujorgenochurras.mapper.gradlew;

import io.github.seujorgenochurras.domain.PluginDeclaration;
import io.github.seujorgenochurras.domain.dependency.DependencyDeclaration;
import io.github.seujorgenochurras.domain.manager.gradlew.GradleBuildFileBuilder;
import io.github.seujorgenochurras.mapper.DependencyManagerFile;
import io.github.seujorgenochurras.mapper.DependencyMapper;
import io.github.seujorgenochurras.mapper.gradlew.tree.GradleTree;
import io.github.seujorgenochurras.mapper.gradlew.tree.mapper.GradleTreeFileMapper;
import io.github.seujorgenochurras.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.github.seujorgenochurras.utils.StringUtils.stringContainsAnyMatchesOf;

public class GradleMapper extends DependencyMapper {

   private final GradleTree gradleTree;
   protected String gradleBuildFileAsString;
   private List<DependencyDeclaration> dependencyDeclarations = new ArrayList<>();
   private List<PluginDeclaration> pluginDeclarations = new ArrayList<>();

   public GradleMapper(File rootFile) {
      super(rootFile);
      this.gradleBuildFileAsString = FileUtils.getFileAsString(rootFile);
      this.gradleTree = GradleTreeFileMapper.mapFile(rootFile);
   }

   @Override
   protected DependencyManagerFile map() {
      this.removeComments();
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

   private void removeComments() {
      String multiLineAndInLineCommentsRegex = "/\\*((.|\\n)*?)\\*/|//.*";
      this.gradleBuildFileAsString = this.gradleBuildFileAsString.replaceAll(multiLineAndInLineCommentsRegex, "");
   }

   protected List<PluginDeclaration> getAllPluginDeclarations() {
      List<PluginDeclaration> plugins = new ArrayList<>();
      gradleTree.getNodeGroupByName("plugins").getNodes()
              .stream()
              .filter(possiblePluginDeclarationNode ->
                      stringContainsAnyMatchesOf("(?<=id).*['\"]", possiblePluginDeclarationNode.getTextContents()))

              .forEach(pluginDeclarationNode ->
                      plugins.add(new PluginDeclaration(pluginDeclarationNode.getTextContents(), 0)));

      return plugins;
   }

   protected List<DependencyDeclaration> getDependencyDeclarations() {

      List<DependencyDeclaration> dependenciesAsString = new ArrayList<>();

      gradleTree.getNodeGroupByName("dependencies").getNodes()
              .stream()
              .filter(probableDependencyDeclarationNode ->
                      stringContainsAnyMatchesOf("(testImplementation|implementation|runtime_only|testRuntimeOnly|testCompileOnly|runtimeOnly|api|compileOnly|compileOnlyApi).*",
                              probableDependencyDeclarationNode.getTextContents()))
              .forEach(dependencyDeclarationNode -> {
                 int nodeLinePosition = dependencyDeclarationNode.getLinePosition();
                 String nodeContents = dependencyDeclarationNode.getTextContents();
                 dependenciesAsString.add(new DependencyDeclaration(nodeContents, nodeLinePosition));
              });
      return dependenciesAsString;
   }

}
