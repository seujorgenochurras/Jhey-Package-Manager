package io.github.seujorgenochurras.mapper.gradlew.mapper;


import io.github.seujorgenochurras.domain.dependency.Dependency;
import io.github.seujorgenochurras.domain.dependency.DependencyBuilder;
import io.github.seujorgenochurras.manager.DependencyManager;
import io.github.seujorgenochurras.mapper.DependencyManagerFile;
import io.github.seujorgenochurras.mapper.DependencyMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

class GradleMapperTest {

   public static final DependencyManagerFile dependencyManagerFile =
           DependencyMapper.mapFile(new File("java-dependency-manager/dependency-file-example/build.gradle"));

   @Test
   void isMappingDependencies(){
      assertEquals(12, dependencyManagerFile.getDependencies().size());
   }
   @Test
   void isMappingPlugins(){
      assertNotEquals(5, dependencyManagerFile.getPlugins().size());
   }

   @Test
   void isAddingDependency(){
      Dependency dependency = DependencyBuilder.startBuild()
              .group("test.test")
              .artifact("testImpl")
              .version("69.42.0")
              .buildResult();
      dependencyManagerFile.addDependency(dependency);
      assertTrue(dependencyManagerFile.getDependencies().contains(dependency));
   }


}
