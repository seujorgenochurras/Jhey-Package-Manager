package io.github.seujorgenochurras.mapper.gradlew;

import io.github.seujorgenochurras.domain.PluginDeclaration;
import io.github.seujorgenochurras.domain.dependency.DependencyDeclaration;
import io.github.seujorgenochurras.domain.manager.gradlew.GradleBuildFileBuilder;
import io.github.seujorgenochurras.mapper.DependencyManagerFile;
import io.github.seujorgenochurras.mapper.DependencyMapper;
import io.github.seujorgenochurras.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;

import static io.github.seujorgenochurras.utils.StringUtils.*;

public class GradleMapper extends DependencyMapper {

   private List<DependencyDeclaration> dependencyDeclarations = new ArrayList<>();
   private List<PluginDeclaration> pluginDeclarations = new ArrayList<>();
   protected String gradleBuildFileAsString;

   public GradleMapper(File rootFile) {
      super(rootFile);
      this.gradleBuildFileAsString = FileUtils.getFileAsString(rootFile);
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
      List<PluginDeclaration> pluginsAsString = new ArrayList<>();

      getLinesOfPluginsBlock().forEach((lineNumber, line) -> {

         String pluginDeclarationRegex = "(?<=id).*['\"]";
         Matcher matcher = generateStringMatcherFromRegex(line, pluginDeclarationRegex);
         getAllMatchesOfMatcher(matcher).forEach((pluginDeclarationLine, pluginDeclaration) ->
                 pluginsAsString.add(new PluginDeclaration(pluginDeclaration, pluginDeclarationLine)));
      });
      return pluginsAsString;
   }

   protected List<DependencyDeclaration> getDependencyDeclarations() {

      List<DependencyDeclaration> dependenciesAsString = new ArrayList<>();

      getLinesOfDependenciesBlock().forEach((lineNumber, line) -> {
         String dependencyDeclarationRegex = "(testImplementation|implementation|runtime_only|testRuntimeOnly).*".trim();
         Matcher dependencyDeclarationMatcher = generateStringMatcherFromRegex(line, dependencyDeclarationRegex);

         getAllMatchesOfMatcher(dependencyDeclarationMatcher)
                 .forEach((dependencyDeclarationLineNumber, dependencyDeclarationLine) ->
                         dependenciesAsString.add(new DependencyDeclaration(dependencyDeclarationLine, dependencyDeclarationLineNumber)));
      });
      return dependenciesAsString;
   }

   protected HashMap<Integer, String> getBlockLinesFromGradleFile(String blockName) {

      String blockRegex = generateRegexForCodeBlock(blockName);
      Matcher matcher = generateGradleMatcherFromRegex(blockRegex);

      return getAllMatchesOfMatcher(matcher);
   }

   private Matcher generateGradleMatcherFromRegex(String regex) {
      return generateStringMatcherFromRegex(gradleBuildFileAsString, regex);
   }

   private HashMap<Integer, String> getLinesOfDependenciesBlock() {
      return getBlockLinesFromGradleFile("dependencies");
   }

   private HashMap<Integer, String> getLinesOfPluginsBlock() {
      return getBlockLinesFromGradleFile("plugins");
   }

}
