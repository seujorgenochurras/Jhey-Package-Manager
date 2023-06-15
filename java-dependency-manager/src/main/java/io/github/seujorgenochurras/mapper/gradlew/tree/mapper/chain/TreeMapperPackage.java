package io.github.seujorgenochurras.mapper.gradlew.tree.mapper.chain;

import io.github.seujorgenochurras.mapper.gradlew.tree.node.GradleTree;
import io.github.seujorgenochurras.utils.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class TreeMapperPackage {
   private final Set<GradleTree> gradleTreesFound = new LinkedHashSet<>();
   private final List<GradleTree> previousGradleTree = new ArrayList<>();
   private Integer openCurlyBracesCount = 0;
   private String line;
   private GradleTree currentGradleTree;

   public boolean isInsideTree() {
      return openCurlyBracesCount > 1;
   }

   public boolean isMappingTree() {
      return openCurlyBracesCount > 0;
   }


   public GradleTree getCurrentGradleTree() {
      return currentGradleTree;
   }

   public void setCurrentGradleTree(GradleTree currentGradleTree) {
      this.currentGradleTree = currentGradleTree;
   }

   public String getAllTextBeforeOpenCurlyBraces() {
      if(!line.contains("{")) return "";
      return StringUtils.getTextBeforeChar(line.indexOf("{"), line);
   }

   public void appendCurrentTreeToFatherTree() {
      if (openCurlyBracesCount <= 1) return;

      GradleTree fatherNodeGroup = previousGradleTree.get(openCurlyBracesCount - 2);
      if (!fatherNodeGroup.equals(getCurrentGradleTree())) {
         fatherNodeGroup.appendNodeGroup(getCurrentGradleTree());
      }
   }

   public void setLine(String line){
      this.line = line;
   }

   public String getLine() {
      return line;
   }

   public void incrementOpenCurlyBracesCount() {
      openCurlyBracesCount++;
   }

   public void decrementOpenCurlyBracesCount() {
      openCurlyBracesCount--;
   }

   public void removeLastNodeGroupFromPreviousNodeGroups() {
      this.getPreviousGradleTree().remove(openCurlyBracesCount.intValue());
   }

   public List<GradleTree> getPreviousGradleTree() {
      return previousGradleTree;
   }

   public void addToPreviousTrees(GradleTree group) {
      this.previousGradleTree.add(group);
   }

   public Set<GradleTree> getGradleTreesFound() {
      return gradleTreesFound;
   }

   @Override
   public String toString() {
      return "TreeMapperPackage{" +
              ", groupsFound=" + gradleTreesFound +
              ", openCurlyBracesCount=" + openCurlyBracesCount +
              ", previousNodeGroups=" + previousGradleTree +
              '}' + "\n\n\n";
   }
}
