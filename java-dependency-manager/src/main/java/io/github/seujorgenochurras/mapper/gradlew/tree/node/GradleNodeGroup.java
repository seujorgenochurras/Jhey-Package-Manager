package io.github.seujorgenochurras.mapper.gradlew.tree.node;

import java.util.HashSet;
import java.util.Set;

public class GradleNodeGroup {
    private final Set<GradleNode> nodes = new HashSet<>();
    private final Set<GradleNodeGroup> childNodeGroups = new HashSet<>();
    private String groupName;
    private int groupLineStartPosition;
    private int groupLineFinalPosition;


    public Set<GradleNode> getNodes() {
        return nodes;
    }

    public void appendNodeGroup(GradleNodeGroup nodeGroup){
        this.childNodeGroups.add(nodeGroup);
    }

    public Set<GradleNodeGroup> getChildNodeGroups() {
        return childNodeGroups;
    }

    public void addNode(GradleNode node){
        this.nodes.add(node);
    }
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupLineStartPosition() {
        return groupLineStartPosition;
    }

    public void setGroupLineStartPosition(int groupLineStartPosition) {
        this.groupLineStartPosition = groupLineStartPosition;
    }

    public int getGroupLineFinalPosition() {
        return groupLineFinalPosition;
    }

    public void setGroupLineFinalPosition(int groupLineFinalPosition) {
        this.groupLineFinalPosition = groupLineFinalPosition;
    }

    @Override
    public String toString() {
        return "GradleNodeGroup{" +
                "nodes=" + nodes +
                ", childNodeGroups=" + childNodeGroups +
                ", groupName='" + groupName + '\'' +
                ", groupLineStartPosition=" + groupLineStartPosition +
                ", groupLineFinalPosition=" + groupLineFinalPosition +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GradleNodeGroup that)) return false;

        if (getGroupLineStartPosition() != that.getGroupLineStartPosition()) return false;
        if (getGroupLineFinalPosition() != that.getGroupLineFinalPosition()) return false;
        return getGroupName() != null ? getGroupName().equals(that.getGroupName()) : that.getGroupName() == null;
    }
}
