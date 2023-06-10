package io.github.seujorgenochurras.mapper.gradlew.tree.node;

import java.util.ArrayList;
import java.util.List;

public class GradleNodeGroup {
    private final List<GradleNode> nodes = new ArrayList<>();
    private final List<GradleNodeGroup> childNodeGroups = new ArrayList<>();
    private String groupName;
    private int groupLineStartPosition;
    private int groupLineFinalPosition;


    public List<GradleNode> getNodes() {
        return nodes;
    }

    public void appendNodeGroup(GradleNodeGroup nodeGroup){
        this.childNodeGroups.add(nodeGroup);
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
}
