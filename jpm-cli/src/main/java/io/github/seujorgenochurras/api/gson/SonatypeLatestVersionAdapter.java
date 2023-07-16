package io.github.seujorgenochurras.api.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class SonatypeLatestVersionAdapter implements JsonDeserializer<String> {
   @Override
   public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
      if(json.isJsonPrimitive()) return json.getAsString();
      return json.getAsJsonObject().get("version").getAsString();
   }
}
