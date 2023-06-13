package io.github.seujorgenochurras.mapper.gradlew.validator;

import java.util.function.Function;

public class WhenStringSplittedWith implements GradleValidator<String> {
   private final String splitString;
   private Function<String[], Boolean> innerValidator;

   public WhenStringSplittedWith(String splitString) {
      this.splitString = splitString;
   }

   public WhenStringSplittedWith sizeGreaterThen(int size) {
      this.innerValidator = stringArr -> stringArr.length > size;
      return this;
   }


   @Override
   public boolean validate(String s) {
      return innerValidator.apply(s.split(splitString));
   }
}
