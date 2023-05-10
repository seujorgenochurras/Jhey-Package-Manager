package io.github.seujorgenochurras.command.arg.flag;

import java.util.logging.Logger;

public class Flag {
   private static final Logger logger = Logger.getLogger(Flag.class.getName());
   private Object value;
   private String name;

   public String getName() {
      return name;
   }

   public Flag setName(String name) {
      this.name = name;
      return this;
   }

   public Flag() {
   }
   public Flag(Object value, String name) {
      this.value = value;
      this.name = name;
   }
   public String getValueAsString(){

      return tryCastValueTo("");
   }
   public int getValueAsInteger(){
      return tryCastValueTo(-1);
   }
   public boolean getValueAsBoolean(){
      return tryCastValueTo(false);
   }
   public <T> T getValueAs(T t){
      return tryCastValueTo(t);
   }
   public double getValueAsDouble(){
      return tryCastValueTo(0.1);
   }

   @SuppressWarnings("unchecked")
   private <T> T tryCastValueTo (T t){
      try {
         return (T) value;
      }catch (ClassCastException e){
         logger.severe("Cannot cast value to " + t.getClass().getName());
         throw e;
      }
   }
}
