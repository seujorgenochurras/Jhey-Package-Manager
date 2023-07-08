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

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Plugin plugin)) return false;

      return getId() != null ? getId().equals(plugin.getId()) : plugin.getId() == null;
   }

   @Override
   public int hashCode() {
      return getId() != null ? getId().hashCode() : 0;
   }
}

