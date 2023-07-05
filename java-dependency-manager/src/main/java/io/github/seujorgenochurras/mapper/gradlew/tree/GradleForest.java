package io.github.seujorgenochurras.mapper.gradlew.tree;

import io.github.seujorgenochurras.mapper.gradlew.tree.node.GradleTree;
import io.github.seujorgenochurras.utils.NotFoundException;

import java.util.Set;

public record GradleForest(Set<GradleTree> gradleTrees) {

   public GradleTree getTreeByName(String treeName) {
      return this.gradleTrees.stream()
              .filter(tree -> tree.getTreeName().trim().equals(treeName))
              .findFirst()
              .orElseThrow(() -> new NotFoundException("No tree with name " + treeName + " found"));
   }
   public String rawToString(){
      StringBuilder result = new StringBuilder();

      this.gradleTrees.forEach(tree -> {
         result.append(tree.getRawString());
      });
      return result.toString();
   }

   @Override
   public String toString() {
      return "GradleForest{" +
              "gradleTrees=" + gradleTrees +
              '}';
   }
}
