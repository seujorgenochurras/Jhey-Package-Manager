package io.github.seujorgenochurras.api.domain;

import com.google.gson.annotations.SerializedName;

public class Dependency {
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

   public Dependency setIdentifier(String identifier) {
      this.identifier = identifier;
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

   public String getLatestVersion() {
      return latestVersion;
   }

   public Dependency setLatestVersion(String latestVersion) {
      this.latestVersion = latestVersion;
      return this;
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
