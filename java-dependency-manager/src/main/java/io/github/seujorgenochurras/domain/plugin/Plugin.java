package io.github.seujorgenochurras.domain.plugin;

public class Plugin {
   private String id;

   public Plugin(String id) {
      this.id = id;
   }

   public Plugin() {
   }

   public String getId() {
      return id;
   }

   public Plugin setId(String id) {
      this.id = id;
      return this;
   }

   @Override
   public String toString() {
      return "Plugin{" +
              "id='" + id + '\'' +
              '}';
   }
}
