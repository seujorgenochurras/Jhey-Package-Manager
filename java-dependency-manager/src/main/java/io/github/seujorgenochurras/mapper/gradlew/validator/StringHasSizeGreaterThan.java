package io.github.seujorgenochurras.mapper.gradlew.validator;

public class StringHasSizeGreaterThan implements GradleValidator<String>{
   private final int stringMinimumSize;

   public StringHasSizeGreaterThan(int stringMinimumSize) {
      this.stringMinimumSize = stringMinimumSize;
   }

   @Override
   public boolean validate(String s) {
      return s.length() > stringMinimumSize;
   }
}
