package io.github.seujorgenochurras.api.domain;

public abstract class AbstractDependency {
    abstract public String getVersion();
    abstract public String getArtifactName();
    abstract public String getGroupName();



    public String getFullName(){
        return getGroupName() + ":" + getArtifactName() + ":" + getVersion();
    }
}
