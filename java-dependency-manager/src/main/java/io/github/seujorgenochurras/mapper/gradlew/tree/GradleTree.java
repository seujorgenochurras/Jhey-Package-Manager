package io.github.seujorgenochurras.mapper.gradlew.tree;

import io.github.seujorgenochurras.mapper.gradlew.tree.node.GradleNodeGroup;
import io.github.seujorgenochurras.utils.NotFoundException;

import java.util.Set;

public class GradleTree {
    private final Set<GradleNodeGroup> childNodeGroups;

     GradleTree(Set<GradleNodeGroup> childNodeGroups) {
        // Only by builder
        this.childNodeGroups = childNodeGroups;
    }

    public GradleNodeGroup getNodeGroupByName(String nodeGroupName) {
        return this.childNodeGroups.stream()
                .filter(nodeGroup -> nodeGroup.getGroupName().trim().equals(nodeGroupName))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No groups with name " + nodeGroupName + " found"));
    }

    public Set<GradleNodeGroup> getChildNodeGroups() {
        return childNodeGroups;
    }

}
