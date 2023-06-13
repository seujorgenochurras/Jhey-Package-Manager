package io.github.seujorgenochurras.mapper.gradlew.tree.mapper;

import io.github.seujorgenochurras.mapper.gradlew.tree.GradleForest;
import io.github.seujorgenochurras.mapper.gradlew.tree.GradleTreeBuilder;
import io.github.seujorgenochurras.mapper.gradlew.tree.mapper.chain.MapperResponsibilityChain;
import io.github.seujorgenochurras.mapper.gradlew.tree.mapper.chain.TreeMapperPackage;
import io.github.seujorgenochurras.mapper.gradlew.tree.mapper.chain.handler.OnCharIsCloseCurlyBraces;
import io.github.seujorgenochurras.mapper.gradlew.tree.mapper.chain.handler.OnCharIsInsideNodeGroup;
import io.github.seujorgenochurras.mapper.gradlew.tree.mapper.chain.handler.OnCharIsOpenCurlyBraces;
import io.github.seujorgenochurras.mapper.gradlew.tree.node.GradleTree;
import io.github.seujorgenochurras.utils.FileUtils;

import java.io.File;
import java.util.Set;

public class GradleForestFileMapper implements GradleForestMapper {

   private final File fileToMap;

   private GradleForestFileMapper(File fileToMap) {
      this.fileToMap = fileToMap;
   }

   public static GradleForest mapFile(File file) {
      return new GradleForestFileMapper(file).map();
   }

   @Override
   public GradleForest map() {
      Set<GradleTree> gradleTrees = mapAllNodeGroups();
      return GradleTreeBuilder.startBuild()
              .gradleTrees(gradleTrees)
              .getBuildResult();
   }

   private Set<GradleTree> mapAllNodeGroups() {
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
         if (character == '{' || character == '}' || treeMapperPackage.isInsideTree())
            packageResponsibilityChain.handlePackage(treeMapperPackage);
      }

      return treeMapperPackage.getGradleTreesFound();
   }
}