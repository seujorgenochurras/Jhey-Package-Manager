package io.github.seujorgenochurras.mapper.gradlew.validator;

public interface GradleValidator<T> {
   boolean validate(T t);
}
