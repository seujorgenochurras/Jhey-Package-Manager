package io.github.seujorgenochurras.mapper.gradlew.tree.mapper.chain.handler;

import io.github.seujorgenochurras.mapper.gradlew.tree.mapper.chain.GradleTreeMapperChainHandler;
import io.github.seujorgenochurras.mapper.gradlew.tree.mapper.chain.MapperResponsibilityChain;
import io.github.seujorgenochurras.mapper.gradlew.tree.mapper.chain.TreeMapperPackage;
import io.github.seujorgenochurras.mapper.gradlew.tree.node.GradleTree;


public class OnCharIsOpenCurlyBraces implements GradleTreeMapperChainHandler {
   @Override
   public void handle(MapperResponsibilityChain currentChain) {
      if (currentChain.getTreeMapperPackage().getCurrentChar() != '{') return;
      TreeMapperPackage treeMapperPackage = currentChain.getTreeMapperPackage();

      treeMapperPackage.incrementOpenCurlyBracesCount();
      GradleTree currentNodeGroup = new GradleTree();
      treeMapperPackage.setCurrentGradleTree(currentNodeGroup);
      currentNodeGroup.setTreeName(treeMapperPackage.getAllTextBeforeCurrentChar());
      treeMapperPackage.addToPreviousNodeGroups(treeMapperPackage.getCurrentGradleTree());

      treeMapperPackage.addCurrentNodeGroupToPreviousNodeGroup();
      currentChain.setHasBeenHandled(true);
      if(treeMapperPackage.getOpenCurlyBracesCount() == 1){
         treeMapperPackage.getGradleTreesFound().add(treeMapperPackage.getCurrentGradleTree());
      }
   }
}
