package io.github.seujorgenochurras.api.domain;

import io.github.seujorgenochurras.domain.dependency.DependencyType;

public interface IDependency {
   String getVersion();

   String getArtifactName();

   String getGroupName();

   DependencyType getDependencyType();

   default String getFullName() {
      return getGroupName() + ":" + getArtifactName() + ":" + getVersion();
   }
}
