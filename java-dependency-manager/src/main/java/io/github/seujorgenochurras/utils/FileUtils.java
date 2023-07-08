package io.github.seujorgenochurras.utils;

import java.io.*;
import java.util.NoSuchElementException;

public class FileUtils {
   private FileUtils(){}

   public static String getFileAsString(File file){
      StringBuilder fileAsString = new StringBuilder();

      try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))){
         String currentLine;
         while ((currentLine = bufferedReader.readLine()) != null){
            fileAsString.append(currentLine).append("\n");
         }

      } catch (IOException e) {
         throw new NoSuchElementException(e);
      }
      return fileAsString.toString();
   }

}
