package io.github.seujorgenochurras.domain.dependency.util;

import io.github.seujorgenochurras.domain.dependency.Dependency;
import io.github.seujorgenochurras.domain.dependency.DependencyBuilder;
import io.github.seujorgenochurras.domain.dependency.DependencyType;
import io.github.seujorgenochurras.utils.StringUtils;

public class StringToDependencyParser {
   private StringToDependencyParser(){}

   public static Dependency stringToDependency(String dependencyDeclaration){
      String cleanDeclaration = dependencyDeclaration.replaceAll(" {2}", "");
      String dependencyType = cleanDeclaration.split("[(\"']")[0];

      cleanDeclaration = cleanDeclaration.replaceAll("[(\"')]", " ")
              .replaceFirst("^(.*?) ", "");

      String[] dependencyDeclarationComponents = cleanDeclaration.split(":");
      dependencyDeclarationComponents = StringUtils.trimStringArr(dependencyDeclarationComponents);

      String dependencyGroup = dependencyDeclarationComponents[0];
      String dependencyArtifact = dependencyDeclarationComponents[1];
      String dependencyVersion = dependencyDeclarationComponents.length == 3 ? dependencyDeclarationComponents[2] : "";
      return DependencyBuilder.startBuild()
              .group(dependencyGroup)
              .artifact(dependencyArtifact)
              .version(dependencyVersion)
              .dependencyType(DependencyType.getTypeByName(dependencyType))
              .buildResult();
   }
}
