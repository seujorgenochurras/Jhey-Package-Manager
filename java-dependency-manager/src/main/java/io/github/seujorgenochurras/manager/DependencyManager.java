package io.github.seujorgenochurras.manager;

import io.github.seujorgenochurras.mapper.DependencyManagerFile;
import io.github.seujorgenochurras.file.DependencyFileNotFoundException;
import io.github.seujorgenochurras.file.FileSearcher;
import io.github.seujorgenochurras.mapper.DependencyMapper;
import io.github.seujorgenochurras.utils.StringUtils;

import java.io.File;

public class DependencyManager {

   private DependencyManager() {
   }

   /**
    Tries to find any dependency manager file <i>such as (build.gradle, build.gradle.kts, and maven.pom) </i>
    *
    * @param filePath the generic path of the dependency manager file (it shouldn't contain the file name in the path)
    * @return A dependency manager file as object, so you can do some cool stuff with it
    * @see DependencyManagerFile
    */
   public static DependencyManagerFile getDependencyManagerFile(String filePath){
      if(StringUtils.lastCharOfString(filePath) != '/'){
         filePath = filePath.concat("/");
      }

      return DependencyMapper.mapFile(getDependencyManagerAsFile(filePath));
   }


   public static DependencyManagerFile getDependencyManagerFile() {
      return getDependencyManagerFile(System.getProperty("user.dir"));
   }

   private static File getDependencyManagerAsFile(String path) {
      return FileSearcher
              .searchForFileIn("build.gradle", path)
              .ifNotFoundSearchFor("build.gradle.kts")
              .ifNotFoundSearchFor("pom.xml")
              .ifNotFoundThrow(() -> new DependencyFileNotFoundException("No dependency manager file found"))
              .getSearchResult();
   }
}
