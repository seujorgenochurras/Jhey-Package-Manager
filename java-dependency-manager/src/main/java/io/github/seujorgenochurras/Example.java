package io.github.seujorgenochurras;

import io.github.seujorgenochurras.domain.dependency.Dependency;
import io.github.seujorgenochurras.domain.dependency.DependencyBuilder;
import io.github.seujorgenochurras.domain.dependency.DependencyType;
import io.github.seujorgenochurras.mapper.DependencyManagerFile;
import io.github.seujorgenochurras.mapper.DependencyMapper;

import java.io.File;

public class Example {
   public static void main(String[] args)  {
     DependencyManagerFile dependencyManagerFile = DependencyMapper.mapFile(new File("java-dependency-manager/dependency-file-example/build.gradle"));

     Dependency dependency = DependencyBuilder.startBuild()
             .group("awiodjaw")
             .artifact("ajwodijawoda")
             .version("1.1.1.1")
             .dependencyType(DependencyType.IMPLEMENTATION)
             .buildResult();
     dependencyManagerFile.addDependency(dependency);
     dependencyManagerFile.commentDependency(dependency);
   }
}
