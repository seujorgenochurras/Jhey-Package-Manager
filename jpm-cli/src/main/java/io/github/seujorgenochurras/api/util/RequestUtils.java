package io.github.seujorgenochurras.api.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

public class RequestUtils {

   private RequestUtils() {
   }

   private static final Logger logger = Logger.getLogger(RequestUtils.class.getName());

   public static HttpResponse<String> makeGetRequestTo(String uri) {
      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest
              .newBuilder()
              .GET()
              .uri(tryGetUriFromString(uri))
              .build();
      try {
         return client.send(request, HttpResponse.BodyHandlers.ofString());

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
