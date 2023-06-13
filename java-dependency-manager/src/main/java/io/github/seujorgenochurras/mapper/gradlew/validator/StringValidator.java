package io.github.seujorgenochurras.mapper.gradlew.validator;

public class StringValidator implements GradleValidator<String>{
   @Override
   public boolean validate(String s) {
      //This is used only to initiate a validator chain with type String
      //Thankfully Java compiler does a great job at validating types
      //Also String is final so there should be no problem with this
      return true;
   }
}
