package io.github.seujorgenochurras.api.service;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import io.github.seujorgenochurras.api.util.RequestUtils;
import io.github.seujorgenochurras.api.domain.Dependency;
import io.github.seujorgenochurras.api.domain.IDependency;
import io.github.seujorgenochurras.api.domain.MavenResponse;

import java.net.http.HttpResponse;
import java.util.ArrayList;

public class MavenService {
   private static final String MAVEN_DOMAIN = "https://search.maven.org/solrsearch/";

   public ArrayList<Dependency> searchForDependency(String dependencyName) {
      return searchForDependency(dependencyName, 10);
   }

   public ArrayList<Dependency> searchForDependency(String dependencyName, int maximumNumberOfResults) {
      String requestUri = MAVEN_DOMAIN + "select?q=" + dependencyName + "&rows=" + maximumNumberOfResults + "&wt=json";

      HttpResponse<String> response = RequestUtils.makeGetRequestTo(requestUri);
      Gson gson = new Gson();

      assert response != null;
      MavenResponse mavenResponse = gson.fromJson(response.body(), MavenResponse.class);

      return mavenResponse.getMavenResponseDependencies().getDependencies();
   }

   public ArrayList<SimpleDependency> searchVersionsOf(IDependency dependency) {
      String requestUri = MAVEN_DOMAIN + "select?q=g:"
              + dependency.getGroupName()
              + "+AND+" + "a:"
              + dependency.getArtifactName()
              + "&core=gav";

      HttpResponse<String> response = RequestUtils.makeGetRequestTo(requestUri);

      Gson gson = new Gson();
      SimpleMavenResponse simpleMavenResponse = gson.fromJson(response.body(), SimpleMavenResponse.class);
      return simpleMavenResponse.getSimpleDependencies();

   }

   private static final class SimpleMavenResponse {
      @SerializedName("response")
      private SimpleMavenResponseDependencies simpleMavenResponseDependencies;

      public ArrayList<SimpleDependency> getSimpleDependencies() {
         return simpleMavenResponseDependencies.simpleDependencies;
      }

      private static final class SimpleMavenResponseDependencies {
         @SerializedName("docs")
         private ArrayList<SimpleDependency> simpleDependencies;
      }
   }

   public static final class SimpleDependency implements IDependency {
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
