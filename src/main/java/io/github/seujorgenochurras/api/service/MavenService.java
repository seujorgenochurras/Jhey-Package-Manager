package io.github.seujorgenochurras.api.service;

import com.google.gson.Gson;
import io.github.seujorgenochurras.api.commons.RequestUtils;
import io.github.seujorgenochurras.api.domain.Dependency;
import io.github.seujorgenochurras.api.domain.MavenResponse;

import java.net.http.HttpResponse;
import java.util.ArrayList;

public class MavenService {

   public ArrayList<Dependency> searchForDependency(String dependencyName){
      return searchForDependency(dependencyName, 10);
   }
   public ArrayList<Dependency> searchForDependency(String dependencyName, int maximumNumberOfResults){
      String requestUri = "https://search.maven.org/solrsearch/select?q=" + dependencyName + "&rows=" + maximumNumberOfResults + "&wt=json";

      HttpResponse<String> response = RequestUtils.makeGetRequestTo(requestUri);
      Gson gson = new Gson();
      MavenResponse mavenResponse = gson.fromJson(response.body(), MavenResponse.class);

      return mavenResponse.getMavenResponseDependencies().getDependencies();
   }
}
