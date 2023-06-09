package io.github.seujorgenochurras.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileUtils {
   private FileUtils(){}

   public static String getFileAsString(File file){
      StringBuilder fileAsString = new StringBuilder();

      try(Scanner scanner = new Scanner(file)){

         while (scanner.hasNextLine()){
            fileAsString.append(scanner.nextLine()).append("\n");
         }

      } catch (FileNotFoundException e) {
         throw new NoSuchElementException(e);
      }

      return fileAsString.toString();
   }
}
