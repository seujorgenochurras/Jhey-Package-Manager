package io.github.seujorgenochurras.domain.dependency;

import io.github.seujorgenochurras.utils.StringUtils;

public class DependencyDeclaration {
   private String rawDeclaration;
   private int declarationLine;

   private Dependency dependency;

   public DependencyDeclaration(String rawDeclaration, int declarationLine) {
      this.rawDeclaration = rawDeclaration;
      this.declarationLine = declarationLine;
   }

   public String getRawDeclaration() {
      return rawDeclaration;
   }

   public DependencyDeclaration setRawDeclaration(String rawDeclaration) {
      this.rawDeclaration = rawDeclaration;
      return this;
   }

   public int getDeclarationLine() {
      return declarationLine;
   }

   public DependencyDeclaration setDeclarationLine(int declarationLine) {
      this.declarationLine = declarationLine;
      return this;
   }
   public Dependency toDependencyObject(){
      if (dependency == null) {

         String cleanDeclaration = rawDeclaration.replaceAll(" {2}", "");
         String dependencyType = cleanDeclaration.split("[(\"]")[0];

         cleanDeclaration = cleanDeclaration.replaceAll("[(\")]", " ")
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
              ", declarationLine=" + declarationLine +
              ", dependency=" + dependency +
              '}';
   }
}
