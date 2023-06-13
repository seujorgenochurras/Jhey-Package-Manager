package io.github.seujorgenochurras.mapper.gradlew.tree;

import io.github.seujorgenochurras.mapper.gradlew.tree.node.GradleTree;
import io.github.seujorgenochurras.utils.NotFoundException;

import java.util.Set;

public class GradleForest {
    private final Set<GradleTree> gradleTrees;

     GradleForest(Set<GradleTree> gradleTrees) {
        // Only by builder
        this.gradleTrees = gradleTrees;
    }

    public GradleTree getTreeByName(String treeName) {
        return this.gradleTrees.stream()
                .filter(tree -> tree.getTreeName().trim().equals(treeName))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No tree with name " + treeName + " found"));
    }

    public Set<GradleTree> getGradleTrees() {
        return gradleTrees;
    }

}
