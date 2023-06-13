package io.github.seujorgenochurras.mapper.gradlew.tree.node;

import java.util.HashSet;
import java.util.Set;

public class GradleTree {
    private final Set<GradleNode> nodes = new HashSet<>();
    private final Set<GradleTree> childGradleTrees = new HashSet<>();
    private String treeName;

    public Set<GradleNode> getNodes() {
        return nodes;
    }

    public void appendNodeGroup(GradleTree nodeGroup){
        this.childGradleTrees.add(nodeGroup);
    }

    public Set<GradleTree> getChildGradleTrees() {
        return childGradleTrees;
    }

    public void addNode(GradleNode node){
        this.nodes.add(node);
    }
    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    @Override
    public String toString() {
        return "GradleTree{" +
                "nodes=" + nodes +
                ", childGradleTrees=" + childGradleTrees +
                ", groupName='" + treeName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GradleTree that)) return false;
        return getTreeName() != null ? getTreeName().equals(that.getTreeName()) : that.getTreeName() == null;
    }
}
