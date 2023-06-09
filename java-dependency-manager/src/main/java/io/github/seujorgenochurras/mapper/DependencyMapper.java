package io.github.seujorgenochurras.mapper;

import io.github.seujorgenochurras.mapper.gradlew.GradleMapper;
import io.github.seujorgenochurras.mapper.maven.MavenPomMapper;

import java.io.File;
import java.util.NoSuchElementException;

public abstract class DependencyMapper {
   protected final File rootFile;

   protected DependencyMapper(File rootFile) {
      this.rootFile = rootFile;
   }

   public static DependencyManagerFile mapFile(File file) {
      return getDependencyTypeFromFile(file).map();
   }

   private static DependencyMapper getDependencyTypeFromFile(File file) {
      String fileName = file.getName();
      if (fileName.equals("build.gradle.kts") || fileName.equals("build.gradle")) {
         return new GradleMapper(file);
      } else if (fileName.equals("pom.xml")) {
         return new MavenPomMapper(file);
      }
      throw new NoSuchElementException("Dependency file not found");
   }


   protected abstract DependencyManagerFile map();

   protected abstract void mapDependencies();

   protected abstract void mapPlugins();

}
