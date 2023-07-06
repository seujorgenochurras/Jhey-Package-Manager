package io.github.seujorgenochurras.mapper.gradlew.validator.chain;

import io.github.seujorgenochurras.mapper.gradlew.validator.GradleValidator;

import java.util.ArrayList;
import java.util.List;

public class GradleValidatorChain<T> {
   private final List<GradleValidator<T>> gradleValidators = new ArrayList<>();

   private GradleValidatorChain(GradleValidator<T> gradleValidator) {
      this.gradleValidators.add(gradleValidator);
   }

   public static <T> GradleValidatorChain<T> initialValidator(GradleValidator<T> initialGradleValidator) {
      return new GradleValidatorChain<>(initialGradleValidator);
   }

   public GradleValidatorChain<T> addValidator(GradleValidator<T> validator) {
      this.gradleValidators.add(validator);
      return this;
   }

   public boolean validate(T t) {
      boolean isValid = false;
      for (GradleValidator<T> gradleValidator : gradleValidators){
         isValid = gradleValidator.validate(t);
         if(!isValid) break;
      }
      return isValid;
   }

}
