package io.github.seujorgenochurras.mapper.gradlew.tree.mapper.chain;

import io.github.seujorgenochurras.mapper.gradlew.tree.node.GradleNodeGroup;
import io.github.seujorgenochurras.utils.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class TreeMapperPackage {
   private final Set<GradleNodeGroup> groupsFound = new LinkedHashSet<>();
   private final List<GradleNodeGroup> previousNodeGroups = new ArrayList<>();
   private String fileToMapContents;
   private Integer indexOfCurrentChar = -1;
   private Integer openCurlyBracesCount = 0;
   private Character currentChar;

   private GradleNodeGroup currentNodeGroup;

   public boolean isInsideNodeGroup() {
      return openCurlyBracesCount > 0;
   }


   public GradleNodeGroup getCurrentNodeGroup() {
      return currentNodeGroup;
   }

   public TreeMapperPackage setCurrentNodeGroup(GradleNodeGroup currentNodeGroup) {
      this.currentNodeGroup = currentNodeGroup;
      return this;
   }

   public void addToGroupsFound(GradleNodeGroup nodeGroup) {
      this.groupsFound.add(nodeGroup);
   }

   public String getTextOfCurrentLine() {
      return StringUtils.getAllLineTextUsingCharIndex(indexOfCurrentChar, fileToMapContents);
   }

   public String getAllTextBeforeCurrentChar() {

      return StringUtils.getTextBeforeChar(indexOfCurrentChar, fileToMapContents);
   }

   public void addCurrentNodeGroupToPreviousNodeGroup() {
      if (openCurlyBracesCount <= 1) return;
      GradleNodeGroup fatherNodeGroup = previousNodeGroups.get(openCurlyBracesCount - 2);
      if (!fatherNodeGroup.equals(getCurrentNodeGroup())) {
         fatherNodeGroup.appendNodeGroup(getCurrentNodeGroup());
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
      this.getPreviousNodeGroups().remove(openCurlyBracesCount.intValue());
   }

   public String getFileToMapContents() {
      return fileToMapContents;
   }

   public TreeMapperPackage setFileToMapContents(String fileToMapContents) {
      this.fileToMapContents = fileToMapContents;
      return this;
   }

   public Integer getIndexOfCurrentChar() {
      return indexOfCurrentChar;
   }

   public Integer getOpenCurlyBracesCount() {
      return openCurlyBracesCount;
   }

   public Character getCurrentChar() {
      return currentChar;
   }

   public List<GradleNodeGroup> getPreviousNodeGroups() {
      return previousNodeGroups;
   }

   public void addToPreviousNodeGroups(GradleNodeGroup group) {
      this.previousNodeGroups.add(group);
   }

   public Set<GradleNodeGroup> getGroupsFound() {
      return groupsFound;
   }

   @Override
   public String toString() {
      return "TreeMapperPackage{" +
              "fileToMapContents='" + fileToMapContents + '\'' +
              ", groupsFound=" + groupsFound +
              ", indexOfCurrentChar=" + indexOfCurrentChar +
              ", openCurlyBracesCount=" + openCurlyBracesCount +
              ", currentChar=" + currentChar +
              ", previousNodeGroups=" + previousNodeGroups +
              '}' + "\n\n\n";
   }
}
