package io.github.seujorgenochurras.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Supplier;

public class FileSearcher {

   private final String searchPath;
   private final List<String> filesNameToSearch = new ArrayList<>();
   private Supplier<? extends RuntimeException> notFoundSupplier = () -> new NoSuchElementException("File not found");

   public FileSearcher(String searchPath) {
      this.searchPath = searchPath;
   }

   public static FileSearcher searchForFileIn(String fileName, String searchPath) {
      FileSearcher fileSearcher = new FileSearcher(searchPath);
      return fileSearcher.addFileToSearch(fileName);
   }

   private FileSearcher addFileToSearch(String fileName) {
      this.filesNameToSearch.add(this.searchPath + fileName);
      return this;
   }

   public FileSearcher ifNotFoundSearchFor(String fileName) {
      return addFileToSearch(fileName);
   }


   private File searchFile(String fileNameAndPath) {
      File fileFound = new File(fileNameAndPath);
      if (!fileFound.exists()) return null;
      return fileFound;
   }

   public File getSearchResult() {

      File fileFound = null;

      for (String fileNameAndPath : filesNameToSearch) {
         if (!Objects.isNull(fileFound)) break;
         fileFound = searchFile(fileNameAndPath);
      }

      if (Objects.isNull(fileFound)) {
         throw this.notFoundSupplier.get();
      }

      return fileFound;
   }

   public <T extends RuntimeException> FileSearcher ifNotFoundThrow(Supplier<T> notFoundSupplier) {
      this.notFoundSupplier = notFoundSupplier;
      return this;
   }
}
