package io.github.seujorgenochurras.mapper.gradlew.tree;

import io.github.seujorgenochurras.mapper.gradlew.tree.node.GradleTree;

import java.util.Set;

public class GradleTreeBuilder {
    private Set<GradleTree> childNodeGroups;

    private GradleTreeBuilder(){}

    public static GradleTreeBuilder startBuild(){
        return new GradleTreeBuilder();
    }
    public GradleTreeBuilder gradleTrees(Set<GradleTree> childNodeGroups) {
        this.childNodeGroups = childNodeGroups;
        return this;
    }

    public GradleForest getBuildResult(){
        return new GradleForest(childNodeGroups);
    }

}
