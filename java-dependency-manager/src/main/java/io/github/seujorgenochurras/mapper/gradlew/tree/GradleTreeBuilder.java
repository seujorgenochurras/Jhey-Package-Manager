package io.github.seujorgenochurras.mapper.gradlew.tree;

import io.github.seujorgenochurras.mapper.gradlew.tree.node.GradleNodeGroup;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class GradleTreeBuilder {
    private HashSet<GradleNodeGroup> childNodeGroups;

    private GradleTreeBuilder(){}

    public static GradleTreeBuilder startBuild(){
        return new GradleTreeBuilder();
    }
    public GradleTreeBuilder childNodeGroups(HashSet<GradleNodeGroup> childNodeGroups) {
        this.childNodeGroups = childNodeGroups;
        return this;
    }

    public GradleTree getBuildResult(){
        return new GradleTree(childNodeGroups);
    }

}
