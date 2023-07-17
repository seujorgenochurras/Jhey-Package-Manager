package io.github.seujorgenochurras.api.domain;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import io.github.seujorgenochurras.api.gson. SonatypeLatestVersionAdapter;
import io.github.seujorgenochurras.domain.dependency.DependencyType;

public class Dependency implements IDependency {
   @SerializedName(value = "g", alternate = "namespace")
   private String groupName;
   @SerializedName(value = "a", alternate = "name")
   private String artifact;
   @SerializedName(value = "latestVersion", alternate = {"latestVersionInfo", "v"})
   @JsonAdapter(SonatypeLatestVersionAdapter.class)
   private String version;

   private DependencyType implementationType = DependencyType.IMPLEMENTATION;

   public Dependency(String groupName, String artifact, String version) {
      this.groupName = groupName;
      this.artifact = artifact;
      this.version = version;
   }

   public Dependency() {
   }

   @Override
   public String getVersion() {
      return version;
   }

   @Override
   public String getArtifactName() {
      return artifact;
   }

   @Override
   public String getGroupName() {
      return groupName;
   }

   @Override
   public DependencyType getDependencyType() {
      return implementationType;
   }

   public void setImplementationType(DependencyType implementationType) {
      this.implementationType = implementationType;
   }

   @Override
   public String toString() {
      return "Dependency{" +
              "groupName='" + groupName + '\'' +
              ", artifact='" + artifact + '\'' +
              ", version='" + version + '\'' +
              '}';
   }

}
