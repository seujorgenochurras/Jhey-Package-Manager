package io.github.seujorgenochurras.mapper.gradlew.mapper.groovy;

import io.github.seujorgenochurras.domain.dependency.Dependency;
import io.github.seujorgenochurras.domain.dependency.DependencyBuilder;
import io.github.seujorgenochurras.domain.dependency.DependencyType;
import io.github.seujorgenochurras.mapper.DependencyManagerFile;
import io.github.seujorgenochurras.mapper.DependencyMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GradleGroovyBuildFileTest {
   public static final DependencyManagerFile dependencyManagerFile =
           DependencyMapper.mapFile(new File("src/test/dependency-file-example/build.gradle"));

   public static final DependencyManagerFile staticDependencyManagerFile =
           DependencyMapper.mapFile(new File("src/test/dependency-file-example/static/build.gradle"));

   @Test
   void isMappingDependencies() {
      assertEquals(17, staticDependencyManagerFile.getDependencies().size());
   }

   @Test
   void isMappingDependencyTypes() {
      List<Dependency> dependencies = staticDependencyManagerFile.getDependencies();

      assertEquals(DependencyType.IMPLEMENTATION, dependencies.get(0).getDependencyType());
      assertEquals(DependencyType.IMPLEMENTATION, dependencies.get(1).getDependencyType());
      assertEquals(DependencyType.TEST_IMPLEMENTATION, dependencies.get(2).getDependencyType());
      assertEquals(DependencyType.RUNTIME_ONLY, dependencies.get(3).getDependencyType());
      assertEquals(DependencyType.TEST_IMPLEMENTATION, dependencies.get(4).getDependencyType());
      assertEquals(DependencyType.TEST_RUNTIME_ONLY, dependencies.get(5).getDependencyType());
      assertEquals(DependencyType.TEST_COMPILE_ONLY, dependencies.get(6).getDependencyType());
      assertEquals(DependencyType.RUNTIME_ONLY, dependencies.get(7).getDependencyType());
      assertEquals(DependencyType.IMPLEMENTATION, dependencies.get(8).getDependencyType());
      assertEquals(DependencyType.API, dependencies.get(9).getDependencyType());
      assertEquals(DependencyType.COMPILE_ONLY, dependencies.get(10).getDependencyType());

   }

   @Test
   void isMappingPlugins() {
      assertTrue(dependencyManagerFile.getPlugins().size() >= 1);
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

      @Test
      void isRemovingDependencies(){
         Dependency firstDependency = dependencyManagerFile.getDependencies().get(0);
         Dependency secondDependency = dependencyManagerFile.getDependencies().get(1);
         Dependency thirdDependency = dependencyManagerFile.getDependencies().get(2);
         assertTrue(dependencyManagerFile.getDependencies().contains(firstDependency));
         assertTrue(dependencyManagerFile.getDependencies().contains(secondDependency));
         assertTrue(dependencyManagerFile.getDependencies().contains(thirdDependency));

         dependencyManagerFile.removeDependency(firstDependency);
         dependencyManagerFile.removeDependency(secondDependency);
         dependencyManagerFile.removeDependency(thirdDependency);

         assertFalse(dependencyManagerFile.getDependencies().contains(firstDependency));
         assertFalse(dependencyManagerFile.getDependencies().contains(secondDependency));
         assertFalse(dependencyManagerFile.getDependencies().contains(thirdDependency));

         dependencyManagerFile.addDependency(firstDependency);
         dependencyManagerFile.addDependency(secondDependency);
         dependencyManagerFile.addDependency(thirdDependency);
      }
}
