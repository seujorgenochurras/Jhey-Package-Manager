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
   private String fileToMapContents;
   private Integer indexOfCurrentChar = -1;
   private Integer openCurlyBracesCount = 0;
   private Character currentChar;

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

   public String getTextOfCurrentLine() {
      return StringUtils.getAllLineTextUsingCharIndex(indexOfCurrentChar, fileToMapContents);
   }

   public String getAllTextBeforeCurrentChar() {
      return StringUtils.getTextBeforeChar(indexOfCurrentChar, fileToMapContents);
   }

   public void appendCurrentTreeToFatherTree() {
      if (openCurlyBracesCount <= 1) return;

      GradleTree fatherNodeGroup = previousGradleTree.get(openCurlyBracesCount - 2);
      if (!fatherNodeGroup.equals(getCurrentGradleTree())) {
         fatherNodeGroup.appendNodeGroup(getCurrentGradleTree());
      }
   }

   public void updateWithChar(Character character) {
      this.currentChar = character;
   }

   public void incrementIndexOfCurrentChar() {
      indexOfCurrentChar++;
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

   public TreeMapperPackage setFileToMapContents(String fileToMapContents) {
      this.fileToMapContents = fileToMapContents;
      return this;
   }

   public Character getCurrentChar() {
      return currentChar;
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
              "fileToMapContents='" + fileToMapContents + '\'' +
              ", groupsFound=" + gradleTreesFound +
              ", indexOfCurrentChar=" + indexOfCurrentChar +
              ", openCurlyBracesCount=" + openCurlyBracesCount +
              ", currentChar=" + currentChar +
              ", previousNodeGroups=" + previousGradleTree +
              '}' + "\n\n\n";
   }
}
