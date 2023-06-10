package io.github.seujorgenochurras.mapper.gradlew.tree;

import io.github.seujorgenochurras.mapper.gradlew.tree.node.GradleNodeGroup;
import io.github.seujorgenochurras.utils.NotFoundException;

import java.util.List;

public class GradleTree {
    private final List<GradleNodeGroup> childNodeGroups;

     GradleTree(List<GradleNodeGroup> childNodeGroups) {
        // Only by builder
        this.childNodeGroups = childNodeGroups;
    }

    public GradleNodeGroup getNodeGroupByName(String nodeGroupName) {
        return this.childNodeGroups.stream()
                .filter(nodeGroup -> nodeGroup.getGroupName().trim().equals(nodeGroupName))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No groups with name " + nodeGroupName + " found"));
    }

    public List<GradleNodeGroup> getChildNodeGroups() {
        return childNodeGroups;
    }

}
