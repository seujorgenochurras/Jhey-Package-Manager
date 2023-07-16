package io.github.seujorgenochurras.api.service;

import com.google.gson.Gson;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import io.github.seujorgenochurras.api.domain.Dependency;
import io.github.seujorgenochurras.api.domain.IDependency;
import io.github.seujorgenochurras.api.gson.MavenDependencyAdapter;
import io.github.seujorgenochurras.api.util.RequestUtils;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MavenService {

    //We have to send this 2 requests because maven api for searching is shit (maybe I'm too dumb to understand it)
    //searching results of maven api are in god knows what order.
    //Sonatype's results are ordered by relevance which is way more relevant to our project
    private static final String SONATYPE_API_INTERNAL = "https://central.sonatype.com/api/internal/browse/components";
    private static final String MAVEN_DOMAIN = "https://search.maven.org/solrsearch/";
    private AsyncMavenService asyncMavenService;

    public ArrayList<Dependency> searchForDependency(String dependencyName) {
        return searchForDependency(dependencyName, 10);
    }

    public AsyncMavenService async() {
        if (asyncMavenService == null || asyncMavenService.executorService.isShutdown()) {
            this.asyncMavenService = new AsyncMavenService();
        }
        return asyncMavenService;
    }

    public ArrayList<Dependency> searchForDependency(String dependencyName, int maximumNumberOfResults) {
        HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofString("" +
                "{\"page\": 0," +
                "\"size\":" + maximumNumberOfResults + "," +
                "\"searchTerm\": \"" + dependencyName + "\"," +
                "\"filter\":[]}");

        Gson gson = new Gson();
        HttpResponse<String> response = RequestUtils.makePostRequestTo(SONATYPE_API_INTERNAL, bodyPublisher);
        assert response != null : "Couldn't send request to sonatype api internal";

        SearchResponse mavenResponse = gson.fromJson(response.body(), SearchResponse.class);

        return mavenResponse.dependencies;
    }

    public List<Dependency> searchVersionsOf(IDependency dependency) {
        String requestUri = MAVEN_DOMAIN + "select?q=g:"
                + dependency.getGroupName()
                + "+AND+" + "a:"
                + dependency.getArtifactName()
                + "&core=gav";

        HttpResponse<String> response = RequestUtils.makeGetRequestTo(requestUri);

        Gson gson = new Gson();
        SearchResponse searchResponse = gson.fromJson(response.body(), SearchResponse.class);
        return searchResponse.dependencies;
    }

    private static final class SearchResponse {
        @SerializedName(value = "components", alternate = "response")
        @JsonAdapter(MavenDependencyAdapter.class)
        ArrayList<Dependency> dependencies;
    }

    public final class AsyncMavenService {
        private final ExecutorService executorService = Executors.newFixedThreadPool(2);

        public void stopExecutors() {
            executorService.shutdown();
            asyncMavenService = null;
        }

        public CompletableFuture<ArrayList<Dependency>> searchForDependencyAsync(String dependencyName) {
            CompletableFuture<ArrayList<Dependency>> future = new CompletableFuture<>();
            executorService.submit(() -> {
                future.completeAsync(() -> searchForDependency(dependencyName));
            });
            return future;
        }

        public CompletableFuture<List<Dependency>> searchVersionsOfAsync(IDependency dependency) {
            CompletableFuture<List<Dependency>> future = new CompletableFuture<>();
            executorService.submit(() -> {
                future.completeAsync(() -> searchVersionsOf(dependency));
            });
            return future;
        }
    }
}
