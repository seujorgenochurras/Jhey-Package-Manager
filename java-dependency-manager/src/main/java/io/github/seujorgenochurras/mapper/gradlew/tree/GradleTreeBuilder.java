package io.github.seujorgenochurras.mapper.gradlew.tree;

import io.github.seujorgenochurras.mapper.gradlew.tree.node.GradleNodeGroup;

import java.util.Set;

public class GradleTreeBuilder {
    private Set<GradleNodeGroup> childNodeGroups;

    private GradleTreeBuilder(){}

    public static GradleTreeBuilder startBuild(){
        return new GradleTreeBuilder();
    }
    public GradleTreeBuilder childNodeGroups(Set<GradleNodeGroup> childNodeGroups) {
        this.childNodeGroups = childNodeGroups;
        return this;
    }

    public GradleTree getBuildResult(){
        return new GradleTree(childNodeGroups);
    }

}
