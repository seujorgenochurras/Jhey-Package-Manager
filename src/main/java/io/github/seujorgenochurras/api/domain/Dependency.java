package io.github.seujorgenochurras.api.domain;

import com.google.gson.annotations.SerializedName;

public class Dependency implements IDependency {
   @SerializedName("g")
   private String groupName;
   @SerializedName("a")
   private String artifact;
   @SerializedName("latestVersion")
   private String latestVersion;

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

   @Override
   public String toString() {
      return "Dependency{" +
              "groupName='" + groupName + '\'' +
              ", artifact='" + artifact + '\'' +
              ", latestVersion='" + latestVersion + '\'' +
              '}';
   }
}
