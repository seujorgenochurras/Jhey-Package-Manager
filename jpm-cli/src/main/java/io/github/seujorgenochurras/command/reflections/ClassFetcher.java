package io.github.seujorgenochurras.command.reflections;

import org.reflections.Reflections;

import java.util.HashSet;
import java.util.Set;

public class ClassFetcher {
   private final Set<Class<?>> fetchedClasses = new HashSet<>();

   private final String packageToFetch;

   public ClassFetcher(String packageToFetch) {
      this.packageToFetch = packageToFetch;
   }

   public static ClassFetcher startFetch() {
      final String basePackage = "io";
      return new ClassFetcher(basePackage);
   }

   public static ClassFetcher startFetchForPackage(String packageName) {
      return new ClassFetcher(packageName);
   }

   public ClassFetcher getAllSubClassesOf(Class<?> clazz) {
      Reflections reflections = new Reflections(packageToFetch);
      fetchedClasses.addAll(reflections.getSubTypesOf(clazz));
      return this;
   }

   public Set<Class<?>> finishFetch() {
      return fetchedClasses;
   }
}
