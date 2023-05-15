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
      return tryCastValueTo(String.class);
   }
   public int getValueAsInteger(){
      return tryCastValueTo(Integer.class);
   }
   public boolean getValueAsBoolean(){
      return tryCastValueTo(Boolean.class);
   }
   public double getValueAsDouble(){
      return tryCastValueTo(Double.class);
   }
   public <T> T getValueAs(Class<T> t){
      return tryCastValueTo(t);
   }
   private <T> T tryCastValueTo (Class<T> classToCast){
      try {
         return classToCast.cast(value);
      }catch (ClassCastException e){
         logger.severe("Cannot cast value to " + classToCast.getName());
         throw e;
      }
   }
}
