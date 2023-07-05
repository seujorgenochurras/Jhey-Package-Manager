package io.github.seujorgenochurras.mapper.gradlew.tree.node;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class GradleTree {
    private final List<GradleNode> nodes = new ArrayList<>();
    private final Set<GradleTree> childGradleTrees = new LinkedHashSet<>();
    private String treeName;

    public List<GradleNode> getNodes() {
        return nodes;
    }

    public void appendNodeGroup(GradleTree nodeGroup) {
        this.childGradleTrees.add(nodeGroup);
    }

    public Set<GradleTree> getChildGradleTrees() {
        return childGradleTrees;
    }

    public void addNode(GradleNode node) {
        this.nodes.add(node);
    }

    public String getTreeName() {
        return treeName.replace("{", "").replace("}","");
    }
    public String getTreeRawName(){
        return treeName.concat("{");
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
    public String getRawString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getTreeRawName()).append("\n");
        nodes.forEach(node -> stringBuilder.append(node.getTextContents()).append("\n"));
        childGradleTrees.forEach(childTrees -> stringBuilder.append(childTrees.getRawString()).append("\n"));
        return stringBuilder.toString().concat("}\n");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GradleTree that)) return false;
        return getTreeName() != null ? getTreeName().equals(that.getTreeName()) : that.getTreeName() == null;
    }
}
