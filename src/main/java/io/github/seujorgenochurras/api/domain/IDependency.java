package io.github.seujorgenochurras.api.domain;

public interface IDependency {
     String getVersion();

     String getArtifactName();

     String getGroupName();



    default String getFullName(){
        return getGroupName() + ":" + getArtifactName() + ":" + getVersion();
    }
}
