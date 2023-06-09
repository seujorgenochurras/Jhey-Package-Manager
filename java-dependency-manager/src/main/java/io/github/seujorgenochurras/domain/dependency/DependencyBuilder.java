package io.github.seujorgenochurras.domain.dependency;

public class DependencyBuilder {
   private DependencyBuilder() {
   }

   private final Dependency buildResult = new Dependency();

   public static DependencyBuilder startBuild() {
      return new DependencyBuilder();
   }

   public DependencyBuilder version(String version) {
      this.buildResult.setVersion(version);
      return this;
   }

   public DependencyBuilder group(String groupName) {
      this.buildResult.setGroupName(groupName);
      return this;
   }

   public DependencyBuilder artifact(String artifactName) {
      this.buildResult.setArtifact(artifactName);
      return this;
   }

   public DependencyBuilder dependencyType(DependencyType dependencyType) {
      this.buildResult.setDependencyType(dependencyType);
      return this;
   }

   public Dependency buildResult() {
      return buildResult;
   }
}
