package io.github.seujorgenochurras.domain.dependency;

public class Dependency {
   private String groupName;
   private String artifact;
   private String version;

   private DependencyType dependencyType = DependencyType.IMPLEMENTATION;

   Dependency() {
      //only by builder
   }

   public DependencyType getDependencyType() {
      return dependencyType;
   }

   public Dependency setDependencyType(DependencyType dependencyType) {
      this.dependencyType = dependencyType;
      return this;
   }

   public String getGroupName() {
      return groupName;
   }

   public Dependency setGroupName(String groupName) {
      this.groupName = groupName;
      return this;
   }

   public String getArtifact() {
      return artifact;
   }

   public Dependency setArtifact(String artifact) {
      this.artifact = artifact;
      return this;
   }

   public String getVersion() {
      return version;
   }

   public Dependency setVersion(String version) {
      this.version = version;
      return this;
   }

   @Override
   public String toString() {
      return "Dependency{" +
              "groupName='" + groupName + '\'' +
              ", artifact='" + artifact + '\'' +
              ", version='" + version + '\'' +
              ", dependencyType=" + dependencyType +
              '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Dependency that)) return false;
      return getDependencyType().equals(that.dependencyType) &&
              getArtifact().equals(that.artifact) &&
              getVersion().equals(that.version);
   }

   @Override
   public int hashCode() {
      int result = getGroupName() != null ? getGroupName().hashCode() : 0;
      result = 31 * result + (getArtifact() != null ? getArtifact().hashCode() : 0);
      result = 31 * result + (getVersion() != null ? getVersion().hashCode() : 0);
      result = 31 * result + (getDependencyType() != null ? getDependencyType().hashCode() : 0);
      return result;
   }
}

