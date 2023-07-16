package io.github.seujorgenochurras;

import io.github.seujorgenochurras.manager.DependencyManager;
import io.github.seujorgenochurras.mapper.DependencyManagerFile;

import java.io.File;

public class Example {
   public static void main(String[] args) {
      System.out.println("meu pau que tre cutuca");
      long start = System.currentTimeMillis();
      mapFiles(1000);
      long end = System.currentTimeMillis();
      System.out.println("took " + (end - start) + " ms");
   }

   static File[] filesToMap = {
           new File("java-dependency-manager/src/test/dependency-file-example/build.gradle.kts"),
           new File("java-dependency-manager/src/test/dependency-file-example/build.gradle"),
           new File("java-dependency-manager/src/test/dependency-file-example/pom.xml")
   };

   public static void mapFiles(int mapQuantity) {
      for (int i = 0; i < mapQuantity; i++) {
         for (int j = 0; j < 3; j++) {
            DependencyManagerFile dependencyManagerFile = DependencyManager.getDependencyManagerFile(filesToMap[j]);
            dependencyManagerFile.getDependencies();
         }
      }
   }
}

