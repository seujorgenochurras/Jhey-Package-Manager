package io.github.seujorgenochurras.mapper.gradlew.tree.mapper;

import io.github.seujorgenochurras.mapper.gradlew.tree.GradleTree;
import io.github.seujorgenochurras.mapper.gradlew.tree.GradleTreeBuilder;
import io.github.seujorgenochurras.mapper.gradlew.tree.node.GradleNode;
import io.github.seujorgenochurras.mapper.gradlew.tree.node.GradleNodeGroup;
import io.github.seujorgenochurras.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
      List<GradleNodeGroup> nodeGroups = mapAllNodeGroups();
      return GradleTreeBuilder.startBuild()
              .childNodeGroups(nodeGroups)
              .getBuildResult();
   }

   private List<GradleNodeGroup> mapAllNodeGroups() {
      String fileToMapContents = FileUtils.getFileAsString(fileToMap);

      List<GradleNodeGroup> groupsFound = new ArrayList<>();
      AtomicInteger indexOfCurrentChar = new AtomicInteger(-1);
      AtomicInteger openCurlyBracesCount = new AtomicInteger(0);
      List<GradleNodeGroup> previousNodeGroups = new ArrayList<>();

      fileToMapContents.chars().forEach(character -> {
         indexOfCurrentChar.getAndIncrement();


         if (character == '{' || character == '}' || isInsideNodeGroup(openCurlyBracesCount.get())) {
            GradleNodeGroup currentNodeGroup;
            if (!previousNodeGroups.isEmpty()) {
               currentNodeGroup = previousNodeGroups.get(previousNodeGroups.size() - 1);
            } else {
               currentNodeGroup = new GradleNodeGroup();
            }
            if (character == '{') {
               previousNodeGroups.add(currentNodeGroup);
               openCurlyBracesCount.getAndIncrement();
               currentNodeGroup.setGroupName(getTextBefore(indexOfCurrentChar.get(), fileToMapContents));

               if (isInsideNodeGroup(openCurlyBracesCount.get())) {
                  GradleNodeGroup fatherNodeGroup = previousNodeGroups.get(openCurlyBracesCount.get() - 1);
                  fatherNodeGroup.appendNodeGroup(currentNodeGroup);
               }

            } else if (character == '}') {
               openCurlyBracesCount.decrementAndGet();
               previousNodeGroups.remove(openCurlyBracesCount.get());
            } else {
               currentNodeGroup.addNode(new GradleNode(getAllLineTextUsingCharIndex(indexOfCurrentChar.get(), fileToMapContents)));
            }
            if (!isInsideNodeGroup(openCurlyBracesCount.get())) {
               groupsFound.add(currentNodeGroup);
            }
         }
      });
      return groupsFound;
   }


   private boolean isInsideNodeGroup(int curlyBracesCount) {
      return curlyBracesCount > 0;
   }
}