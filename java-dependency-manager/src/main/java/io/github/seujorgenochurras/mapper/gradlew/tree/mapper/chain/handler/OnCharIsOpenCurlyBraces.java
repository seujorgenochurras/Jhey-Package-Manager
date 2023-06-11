package io.github.seujorgenochurras.mapper.gradlew.tree.mapper.chain.handler;

import io.github.seujorgenochurras.mapper.gradlew.tree.mapper.chain.GradleTreeMapperChainHandler;
import io.github.seujorgenochurras.mapper.gradlew.tree.mapper.chain.MapperResponsibilityChain;
import io.github.seujorgenochurras.mapper.gradlew.tree.mapper.chain.TreeMapperPackage;
import io.github.seujorgenochurras.mapper.gradlew.tree.node.GradleNodeGroup;


public class OnCharIsOpenCurlyBraces implements GradleTreeMapperChainHandler {
   @Override
   public void handle(MapperResponsibilityChain currentChain) {
      if (currentChain.getTreeMapperPackage().getCurrentChar() != '{') return;
      TreeMapperPackage treeMapperPackage = currentChain.getTreeMapperPackage();

      treeMapperPackage.incrementOpenCurlyBracesCount();
      GradleNodeGroup currentNodeGroup = new GradleNodeGroup();
      treeMapperPackage.setCurrentNodeGroup(currentNodeGroup);
      currentNodeGroup.setGroupName(treeMapperPackage.getAllTextBeforeCurrentChar());
      treeMapperPackage.addToPreviousNodeGroups(treeMapperPackage.getCurrentNodeGroup());

      treeMapperPackage.addCurrentNodeGroupToPreviousNodeGroup();
      currentChain.setHasBeenHandled(true);
      if(treeMapperPackage.getOpenCurlyBracesCount() == 1){
         treeMapperPackage.getGroupsFound().add(treeMapperPackage.getCurrentNodeGroup());
      }
   }
}
