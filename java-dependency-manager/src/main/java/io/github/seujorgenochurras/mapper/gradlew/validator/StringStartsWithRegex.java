package io.github.seujorgenochurras.mapper.gradlew.validator;

import io.github.seujorgenochurras.utils.StringUtils;

public class StringStartsWithRegex implements GradleValidator<String>{
   private final String regex;

   public StringStartsWithRegex(String regex) {
      this.regex = regex;
   }

   @Override
   public boolean validate(String s) {
      return StringUtils.stringStartsWithRegex(regex, s);
   }
}
