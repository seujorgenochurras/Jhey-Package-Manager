package io.github.seujorgenochurras.mapper.gradlew.tree.mapper;

import io.github.seujorgenochurras.mapper.gradlew.tree.GradleTree;
import io.github.seujorgenochurras.mapper.gradlew.tree.GradleTreeBuilder;
import io.github.seujorgenochurras.mapper.gradlew.tree.node.GradleNode;
import io.github.seujorgenochurras.mapper.gradlew.tree.node.GradleNodeGroup;
import io.github.seujorgenochurras.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.List;

import static io.github.seujorgenochurras.utils.StringUtils.getAllLineTextUsingCharIndex;
import static io.github.seujorgenochurras.utils.StringUtils.getTextBefore;

public class GradleTreeFileMapper implements GradleTreeMapper {

   private final File fileToMap;

   private GradleTreeFileMapper(File fileToMap) {
      this.fileToMap = fileToMap;
   }

   public static GradleTree mapFile(File file) {
      return new GradleTreeFileMapper(file).map();
   }

   @Override
   public GradleTree map() {
      Set<GradleNodeGroup> nodeGroups = mapAllNodeGroups();
      return GradleTreeBuilder.startBuild()
              .childNodeGroups(nodeGroups)
              .getBuildResult();
   }

   private Set<GradleNodeGroup> mapAllNodeGroups() {
      String fileToMapContents = FileUtils.getFileAsString(fileToMap);

      Set<GradleNodeGroup> groupsFound = new LinkedHashSet<>();
      int indexOfCurrentChar = -1;
      int openCurlyBracesCount = 0;
      List<GradleNodeGroup> previousNodeGroups = new ArrayList<>();

      for (char character : fileToMapContents.toCharArray()) {
         indexOfCurrentChar++;

         if (character == '{' || character == '}' || isInsideNodeGroup(openCurlyBracesCount)) {
            GradleNodeGroup currentNodeGroup;
            if (!previousNodeGroups.isEmpty()) {
               currentNodeGroup = previousNodeGroups.get(previousNodeGroups.size() - 1);
            } else {
               currentNodeGroup = new GradleNodeGroup();
            }
            if (character == '{') {
               previousNodeGroups.add(currentNodeGroup);
               openCurlyBracesCount++;
               currentNodeGroup.setGroupName(getTextBefore(indexOfCurrentChar, fileToMapContents));

               if (isInsideNodeGroup(openCurlyBracesCount)) {
                  GradleNodeGroup fatherNodeGroup = previousNodeGroups.get(openCurlyBracesCount - 1);
                  fatherNodeGroup.appendNodeGroup(currentNodeGroup);
               }

            } else if (character == '}') {
               openCurlyBracesCount--;
               previousNodeGroups.remove(openCurlyBracesCount);
            } else {
               currentNodeGroup.addNode(
                       new GradleNode(getAllLineTextUsingCharIndex(indexOfCurrentChar, fileToMapContents).trim(), 0));
            }
            if (!isInsideNodeGroup(openCurlyBracesCount)) {
               groupsFound.add(currentNodeGroup);
            }
         }
      }
      return groupsFound;
   }


   private boolean isInsideNodeGroup(int curlyBracesCount) {
      return curlyBracesCount > 0;
   }
}