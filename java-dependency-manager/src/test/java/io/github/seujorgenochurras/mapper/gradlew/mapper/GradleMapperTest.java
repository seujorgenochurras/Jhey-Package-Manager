package io.github.seujorgenochurras.mapper.gradlew.mapper;


import io.github.seujorgenochurras.domain.dependency.Dependency;
import io.github.seujorgenochurras.domain.dependency.DependencyBuilder;
import io.github.seujorgenochurras.domain.dependency.DependencyType;
import io.github.seujorgenochurras.mapper.DependencyManagerFile;
import io.github.seujorgenochurras.mapper.DependencyMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GradleMapperTest {

   public static final DependencyManagerFile dependencyManagerFile =
           DependencyMapper.mapFile(new File("dependency-file-example/build.gradle"));

   @Test
   void isMappingDependencies() {
      assertEquals(13, dependencyManagerFile.getDependencies().size());
   }

   @Test
   void isMappingDependencyTypes() {
      List<Dependency> dependencies = dependencyManagerFile.getDependencies();

      assertEquals(DependencyType.IMPLEMENTATION, dependencies.get(0).getDependencyType());
      assertEquals(DependencyType.TEST_IMPLEMENTATION, dependencies.get(3).getDependencyType());
      assertEquals(DependencyType.RUNTIME_ONLY, dependencies.get(4).getDependencyType());
      assertEquals(DependencyType.TEST_IMPLEMENTATION, dependencies.get(5).getDependencyType());
      assertEquals(DependencyType.TEST_RUNTIME_ONLY, dependencies.get(6).getDependencyType());
      assertEquals(DependencyType.TEST_COMPILE_ONLY, dependencies.get(7).getDependencyType());
      assertEquals(DependencyType.RUNTIME_ONLY, dependencies.get(8).getDependencyType());
      assertEquals(DependencyType.IMPLEMENTATION, dependencies.get(9).getDependencyType());
      assertEquals(DependencyType.API, dependencies.get(10).getDependencyType());
      assertEquals(DependencyType.COMPILE_ONLY, dependencies.get(11).getDependencyType());
      assertEquals(DependencyType.COMPILE_ONLY_API, dependencies.get(12).getDependencyType());
   }

   @Test
   void isMappingPlugins() {
      assertNotEquals(5, dependencyManagerFile.getPlugins().size());
   }

   @Test
   void isAddingDependency() {
      Dependency dependency = DependencyBuilder.startBuild()
              .group("test.test")
              .artifact("testImpl")
              .version("69.42.0")
              .buildResult();
      dependencyManagerFile.addDependency(dependency);
      assertTrue(dependencyManagerFile.getDependencies().contains(dependency));
   }


}

