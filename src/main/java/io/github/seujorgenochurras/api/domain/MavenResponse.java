package io.github.seujorgenochurras.api.domain;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MavenResponse {

   @SerializedName("response")
   private ResponseDependencies mavenResponseDependencies;

   public ResponseDependencies getMavenResponseDependencies() {
      return mavenResponseDependencies;
   }


   public static final class ResponseDependencies{
      @SerializedName("docs")
      private ArrayList<Dependency> dependencies;

      @SerializedName("numFound")
      private long numberOfDependenciesFound;

      public long getNumberOfDependenciesFound() {
         return numberOfDependenciesFound;
      }

      public ArrayList<Dependency> getDependencies() {
         return dependencies;
      }
   }
}
