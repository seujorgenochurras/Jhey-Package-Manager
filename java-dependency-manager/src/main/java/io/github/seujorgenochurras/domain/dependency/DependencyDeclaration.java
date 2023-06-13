package io.github.seujorgenochurras.domain.dependency;

import io.github.seujorgenochurras.utils.StringUtils;

public class DependencyDeclaration {
   private String rawDeclaration;
   private Dependency dependency;

   public DependencyDeclaration(String rawDeclaration) {
      this.rawDeclaration = rawDeclaration;
   }

   public String getRawDeclaration() {
      return rawDeclaration;
   }

   public DependencyDeclaration setRawDeclaration(String rawDeclaration) {
      this.rawDeclaration = rawDeclaration;
      return this;
   }

   public Dependency toDependencyObject(){
      if (dependency == null) {

         String cleanDeclaration = rawDeclaration.replaceAll(" {2}", "");
         String dependencyType = cleanDeclaration.split("[(\"']")[0];

         cleanDeclaration = cleanDeclaration.replaceAll("[(\"')]", " ")
                 .replaceFirst("^(.*?) ", "");

         String[] dependencyDeclarationComponents = cleanDeclaration.split(":");
         dependencyDeclarationComponents = StringUtils.trimStringArr(dependencyDeclarationComponents);

         String dependencyGroup = dependencyDeclarationComponents[0];
         String dependencyArtifact = dependencyDeclarationComponents[1];
         String dependencyVersion = dependencyDeclarationComponents.length == 3 ? dependencyDeclarationComponents[2] : "";
         dependency = DependencyBuilder.startBuild()
                 .group(dependencyGroup)
                 .artifact(dependencyArtifact)
                 .version(dependencyVersion)
                 .dependencyType(DependencyType.getTypeByName(dependencyType))
                 .buildResult();
      }
      return dependency;
   }


   @Override
   public String toString() {
      return "DependencyDeclaration{" +
              "rawDeclaration='" + rawDeclaration + '\'' +
              ", dependency=" + dependency +
              '}';
   }
}
