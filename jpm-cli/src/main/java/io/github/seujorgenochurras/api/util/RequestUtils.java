package io.github.seujorgenochurras.api.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

public class RequestUtils {
   private static final HttpClient client = HttpClient.newHttpClient();

   private RequestUtils() {
   }

   private static final Logger logger = Logger.getLogger(RequestUtils.class.getName());

   public static HttpResponse<String> makeGetRequestTo(String uri) {
      HttpRequest request = HttpRequest
              .newBuilder()
              .GET()
              .uri(tryGetUriFromString(uri))
              .build();
      return sendRequest(request);
   }
   public static HttpResponse<String> makePostRequestTo(String uri, HttpRequest.BodyPublisher bodyPublisher){
      HttpRequest request = HttpRequest
              .newBuilder()
              .setHeader("Content-Type", "application/json")
              .POST(bodyPublisher)
              .uri(tryGetUriFromString(uri))
              .build();
      return sendRequest(request);
   }

   public static HttpResponse<String> sendRequest(HttpRequest httpRequest){
      try {
         return client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

      } catch (IOException | InterruptedException e) {
         logger.severe("Something went wrong " + e.getMessage());
         e.printStackTrace();
         Thread.currentThread().interrupt();
         return null;
      }
   }

   private static URI tryGetUriFromString(String uri) {
      try {
         return new URI(uri);
      } catch (URISyntaxException e) {
         logger.severe("Cannot parse String " + uri + " to uri");
         e.printStackTrace();
         Thread.currentThread().interrupt();
         return null;
      }
   }
}
