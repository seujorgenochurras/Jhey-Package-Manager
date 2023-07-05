package io.github.seujorgenochurras.mapper.gradlew.mapper.kotlin;


import io.github.seujorgenochurras.domain.dependency.Dependency;
import io.github.seujorgenochurras.domain.dependency.DependencyBuilder;
import io.github.seujorgenochurras.domain.dependency.DependencyType;
import io.github.seujorgenochurras.mapper.DependencyManagerFile;
import io.github.seujorgenochurras.mapper.DependencyMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GradleKotlinBuildFileTest {

   public static final DependencyManagerFile dependencyManagerFile =
           DependencyMapper.mapFile(new File("dependency-file-example/build.gradle.kts"));

   @Test
   void isMappingDependencies() {
      assertTrue(dependencyManagerFile.getDependencies().size() > 3);
   }

   @Test
   void isMappingDependencyTypes() {
      List<Dependency> dependencies = dependencyManagerFile.getDependencies();

      assertEquals(DependencyType.IMPLEMENTATION, dependencies.get(0).getDependencyType());
      assertEquals(DependencyType.TEST_IMPLEMENTATION, dependencies.get(3).getDependencyType());
   }

   @Test
   void isMappingPlugins() {
      assertTrue(dependencyManagerFile.getPlugins().size() > 1);
   }

   @Test
   void isAddingDependency() {
      Dependency dependency = DependencyBuilder.startBuild()
              .group("test.test")
              .artifact("testImpl")
              .version("69.42.0")
              .buildResult();

      Dependency dependency2 = DependencyBuilder.startBuild()
              .group("test.222test")
              .artifact("tes222tImpl")
              .version("629.422.0")
              .buildResult();
      Dependency dependency3 = DependencyBuilder.startBuild()
              .group("test111.test")
              .artifact("tes111tImpl")
              .version("69.11142.0")
              .buildResult();
      dependencyManagerFile.addDependency(dependency);
      dependencyManagerFile.addDependency(dependency2);
      dependencyManagerFile.addDependency(dependency3);
      assertTrue(dependencyManagerFile.getDependencies().contains(dependency));
      assertTrue(dependencyManagerFile.getDependencies().contains(dependency2));
      assertTrue(dependencyManagerFile.getDependencies().contains(dependency3));
   }


}

