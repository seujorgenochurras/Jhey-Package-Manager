package io.github.seujorgenochurras.mapper.gradlew.tree.mapper;

import io.github.seujorgenochurras.mapper.gradlew.tree.GradleTree;
import io.github.seujorgenochurras.mapper.gradlew.tree.GradleTreeBuilder;
import io.github.seujorgenochurras.mapper.gradlew.tree.mapper.chain.MapperResponsibilityChain;
import io.github.seujorgenochurras.mapper.gradlew.tree.mapper.chain.TreeMapperPackage;
import io.github.seujorgenochurras.mapper.gradlew.tree.mapper.chain.handler.OnCharIsCloseCurlyBraces;
import io.github.seujorgenochurras.mapper.gradlew.tree.mapper.chain.handler.OnCharIsInsideNodeGroup;
import io.github.seujorgenochurras.mapper.gradlew.tree.mapper.chain.handler.OnCharIsOpenCurlyBraces;
import io.github.seujorgenochurras.mapper.gradlew.tree.node.GradleNodeGroup;
import io.github.seujorgenochurras.utils.FileUtils;

import java.io.File;
import java.util.Set;

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


      TreeMapperPackage treeMapperPackage = new TreeMapperPackage()
              .setFileToMapContents(fileToMapContents);

      MapperResponsibilityChain packageResponsibilityChain = MapperResponsibilityChain.startChain()
              .addHandler(new OnCharIsOpenCurlyBraces())
              .addHandler(new OnCharIsCloseCurlyBraces())
              .addHandler(new OnCharIsInsideNodeGroup());


      for (Character character : fileToMapContents.toCharArray()) {
         treeMapperPackage.incrementIndexOfCurrentChar();
         treeMapperPackage.updateWithChar(character);
         if (character == '{' || character == '}' || treeMapperPackage.isInsideNodeGroup())
            packageResponsibilityChain.handlePackage(treeMapperPackage);
      }

      return treeMapperPackage.getGroupsFound();
   }
}