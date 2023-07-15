package io.github.seujorgenochurras.api.gson;

import com.google.gson.*;
import io.github.seujorgenochurras.api.domain.Dependency;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MavenDependencyAdapter implements JsonDeserializer<ArrayList<Dependency>> {
   @Override
   public ArrayList<Dependency> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
      ArrayList<Dependency> dependenciesFound = new ArrayList<>();
      JsonArray serializedDependencies;
      if (json.isJsonObject()) {
         serializedDependencies = json.getAsJsonObject().get("docs").getAsJsonArray();
      } else {
         serializedDependencies = json.getAsJsonArray();
      }
      serializedDependencies.forEach(jsonElement ->
              dependenciesFound.add(context.deserialize(jsonElement.getAsJsonObject(), Dependency.class)));

      return dependenciesFound;
   }
}
