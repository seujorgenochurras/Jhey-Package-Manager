package io.github.seujorgenochurras.mapper.gradlew.tree;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class GradleForestTransformer {

   private GradleForestTransformer(){}
   public static void transform(GradleForest gradleForest, File file) {
      String gradleForestBody = gradleForest.rawToString();
      tryWriteFile(gradleForestBody, file);
   }

   private static void tryWriteFile(String thingToWrite, File file) {
      try (FileWriter fileWriter = new FileWriter(file)) {
         fileWriter.write(thingToWrite);
         fileWriter.flush();
      } catch (IOException e) {
         Logger.getLogger(GradleForestTransformer.class.getName()).severe("Couldn't transform file " + file.getName());
         e.printStackTrace();
      }
   }
}
