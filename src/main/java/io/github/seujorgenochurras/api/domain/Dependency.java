package io.github.seujorgenochurras.api.domain;

import com.google.gson.annotations.SerializedName;

public class Dependency extends AbstractDependency {
   @SerializedName("g")
   private String groupName;
   @SerializedName("a")
   private String artifact;
   @SerializedName("latestVersion")
   private String latestVersion;

   @SerializedName("id")
   private String identifier;

   public String getIdentifier() {
      return identifier;
   }

   @Override
   public String getVersion() {
      return latestVersion;
   }

   @Override
   public String getArtifactName() {
      return artifact;
   }

   @Override
   public String getGroupName() {
      return groupName;
   }

   public void setGroupName(String groupName) {
      this.groupName = groupName;
   }

   public void setArtifact(String artifact) {
      this.artifact = artifact;
   }

   public void setVersion(String latestVersion) {
      this.latestVersion = latestVersion;
   }

   @Override
   public String toString() {
      return "Dependency{" +
              "groupName='" + groupName + '\'' +
              ", artifact='" + artifact + '\'' +
              ", latestVersion='" + latestVersion + '\'' +
              '}';
   }
}
