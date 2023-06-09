package io.github.seujorgenochurras.domain.manager.gradlew;

import io.github.seujorgenochurras.domain.AbstractPlugin;
import io.github.seujorgenochurras.domain.PluginDeclaration;
import io.github.seujorgenochurras.domain.dependency.Dependency;
import io.github.seujorgenochurras.domain.dependency.DependencyDeclaration;
import io.github.seujorgenochurras.file.DependencyNotFoundException;
import io.github.seujorgenochurras.mapper.DependencyManagerFile;
import io.github.seujorgenochurras.utils.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static io.github.seujorgenochurras.utils.StringUtils.getIndexOfStringWithRegex;

public class GradleBuildFile implements DependencyManagerFile {

   //TODO learn about trees and change this to a tree
   private List<DependencyDeclaration> dependencies;
   private List<PluginDeclaration> plugins;

   //TODO learn more about transformers and build a fucking Bumblebee
   //Just kidding, transformers are used to transform file contents
   private File originFile;
   private String originFileAsString;

   @Override
   public List<Dependency> getDependencies() {
      return dependencies.stream()
              .map(DependencyDeclaration::toDependencyObject)
              .collect(Collectors.toList());
   }

   public void setDependencies(List<DependencyDeclaration> dependencies) {
      this.dependencies = dependencies;
   }

   @Override
   public List<AbstractPlugin> getPlugins() {
      return plugins.stream()
              .map(PluginDeclaration::toPluginObject).toList();
   }

   public void setPlugins(List<PluginDeclaration> plugins) {
      this.plugins = plugins;
   }

   public File getOriginFile() {
      return originFile;
   }

   public void setOriginFile(File originFile) {
      this.originFile = originFile;
      this.originFileAsString = FileUtils.getFileAsString(originFile);
   }

   public FileWriter instantiateFileWriter() {
      return tryInstantiateFileWriterFromFile(originFile);
   }


   @Override
   public void addDependency(Dependency dependency) {
      String declaration = "\n" + dependency.getDependencyType().typeName + " (\""
              + dependency.getGroupName().trim()
              + ":"
              + dependency.getArtifact().trim()
              + ":"
              + dependency.getVersion().trim()
              + "\")";


      int indexOfDependenciesBlock = getIndexOfDependenciesBlock();
      addTextToOriginFile(declaration, indexOfDependenciesBlock);
      this.dependencies.add(new DependencyDeclaration(declaration.replace("\n", ""), indexOfDependenciesBlock));
   }

   @Override
   public <T extends AbstractPlugin> void addPlugin(T plugin) {
      String declaration = "id '" + plugin.getId().trim() + "'\n";

      int indexOfPluginBlock = getIndexOfStringWithRegex(originFileAsString, "plugins");
      addTextToOriginFile(declaration, indexOfPluginBlock);
      tryRewriteOriginFile();
      this.plugins.add(new PluginDeclaration(declaration, indexOfPluginBlock));
   }

   @Override
   public void removeDependency(Dependency dependency) {
      commentDependency(dependency); //TODO find a way to do this *without coding like monkey*
      //DUDE IS THAT BANANA?
   }


   private DependencyDeclaration getDeclarationOfDependency(Dependency dependency) {
      return this.dependencies.stream()
              .filter(dependencyDeclaration ->
                  dependencyDeclaration.toDependencyObject().equals(dependency))
              .findFirst()
              .orElseThrow(() ->
                      new DependencyNotFoundException("Dependency " + dependency.getArtifact() + " not found"));
   }

   @Override
   public <T extends AbstractPlugin> void removePlugin(T plugin) {
      //Fuck you
   }

   @Override
   public void commentDependency(Dependency dependency) {
      DependencyDeclaration dependencyDeclaration = getDeclarationOfDependency(dependency);
      commentLine(dependencyDeclaration.getDeclarationLine());
   }

   private void commentLine(int lineIndex){
      addTextToOriginFile("//", lineIndex + 1);
      tryRewriteOriginFile();
   }

   private int getIndexOfDependenciesBlock() {
      return getIndexOfStringWithRegex(originFileAsString, "dependencies.*\\{");
   }

   private void addTextToOriginFile(String text, int indexOfWhereToWrite) {
      String secondOriginFileHalf = text + originFileAsString.substring(indexOfWhereToWrite);
      originFileAsString = originFileAsString.substring(0, indexOfWhereToWrite) + secondOriginFileHalf;
   }

   private void tryRewriteOriginFile() {
      try (FileWriter originFileWriter = instantiateFileWriter()) {
         originFileWriter.write(originFileAsString);
      } catch (IOException e) {
         throw new IllegalStateException(e);
      }
   }

   private FileWriter tryInstantiateFileWriterFromFile(File file) {
      try {
         return new FileWriter(file);
      } catch (IOException e) {
         throw new IllegalStateException(e);
      }
   }

   @Override
   public String toString() {
      return "GradleBuildFile{" +
              "plugins=" + plugins +
              ", dependencies=" + dependencies +
              '}';
   }
}
