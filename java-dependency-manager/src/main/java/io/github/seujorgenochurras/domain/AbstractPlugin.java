package io.github.seujorgenochurras.domain;

public class AbstractPlugin {
   private String id;

   public AbstractPlugin(String id) {
      this.id = id;
   }

   public AbstractPlugin() {
   }

   public String getId() {
      return id;
   }

   public AbstractPlugin setId(String id) {
      this.id = id;
      return this;
   }

   @Override
   public String toString() {
      return "AbstractPlugin{" +
              "id='" + id + '\'' +
              '}';
   }
}
