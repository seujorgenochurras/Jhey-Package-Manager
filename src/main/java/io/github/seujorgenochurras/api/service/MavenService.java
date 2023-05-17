package io.github.seujorgenochurras.api.service;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import io.github.seujorgenochurras.api.commons.RequestUtils;
import io.github.seujorgenochurras.api.domain.Dependency;
import io.github.seujorgenochurras.api.domain.AbstractDependency;
import io.github.seujorgenochurras.api.domain.MavenResponse;

import java.net.http.HttpResponse;
import java.util.ArrayList;

public class MavenService {
   private static final String MAVEN_DOMAIN = "https://search.maven.org/solrsearch/";

   public ArrayList<Dependency> searchForDependency(String dependencyName){
      return searchForDependency(dependencyName, 10);
   }
   public ArrayList<Dependency> searchForDependency(String dependencyName, int maximumNumberOfResults){
      String requestUri = MAVEN_DOMAIN + "select?q=" + dependencyName + "&rows=" + maximumNumberOfResults + "&wt=json";

      HttpResponse<String> response = RequestUtils.makeGetRequestTo(requestUri);
      Gson gson = new Gson();

      assert response != null;
      MavenResponse mavenResponse = gson.fromJson(response.body(), MavenResponse.class);

      return mavenResponse.getMavenResponseDependencies().getDependencies();
   }
   public ArrayList<SimpleDependency> searchVersionsOf(AbstractDependency dependency){
      String requestUri = MAVEN_DOMAIN + "select?q=g:" + dependency.getGroupName() + "a:" + dependency.getArtifactName();
      HttpResponse<String> response = RequestUtils.makeGetRequestTo(requestUri);

      Gson gson = new Gson();

      assert response != null;
      SimpleMavenResponse simpleMavenResponse = gson.fromJson(response.body(), SimpleMavenResponse.class);

      return simpleMavenResponse.getSimpleDependencies();

   }
   private static final class SimpleMavenResponse{
      @SerializedName("docs")
      private ArrayList<SimpleDependency> simpleDependencies;

      public ArrayList<SimpleDependency> getSimpleDependencies() {
         return simpleDependencies;
      }
   }
   public static final class SimpleDependency extends AbstractDependency {
      @SerializedName("v")
      private String version;

      @SerializedName("g")
      private String groupName;

      @SerializedName("a")
      private String artifactName;

      @Override
      public String getVersion() {
         return version;
      }

      @Override
      public String getGroupName() {
         return groupName;
      }

      @Override
      public String getArtifactName() {
         return artifactName;
      }
   }
}
