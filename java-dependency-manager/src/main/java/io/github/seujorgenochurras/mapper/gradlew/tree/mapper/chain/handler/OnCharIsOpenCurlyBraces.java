package io.github.seujorgenochurras.mapper.gradlew.tree.mapper.chain.handler;

import io.github.seujorgenochurras.mapper.gradlew.tree.mapper.chain.GradleTreeMapperChainHandler;
import io.github.seujorgenochurras.mapper.gradlew.tree.mapper.chain.MapperResponsibilityChain;
import io.github.seujorgenochurras.mapper.gradlew.tree.mapper.chain.TreeMapperPackage;
import io.github.seujorgenochurras.mapper.gradlew.tree.node.GradleTree;


public class OnCharIsOpenCurlyBraces implements GradleTreeMapperChainHandler {
   @Override
   public void handle(MapperResponsibilityChain currentChain) {
      if (!currentChain.getTreeMapperPackage().getLine().contains("{")) return;
      TreeMapperPackage treeMapperPackage = currentChain.getTreeMapperPackage();

      treeMapperPackage.incrementOpenCurlyBracesCount();
      GradleTree currentTree = new GradleTree();
      treeMapperPackage.setCurrentGradleTree(currentTree);

      currentTree.setTreeName(getTextBeforeOpenCurlyBraces(treeMapperPackage));

      treeMapperPackage.addToPreviousTrees(currentTree);

      if (treeMapperPackage.isInsideTree()){
         treeMapperPackage.appendCurrentTreeToFatherTree();
      }else{
         treeMapperPackage.getGradleTreesFound().add(currentTree);
      }
      currentChain.setHasBeenHandled(true);
   }
   private String getTextBeforeOpenCurlyBraces(TreeMapperPackage treeMapperPackage) {
      String line = treeMapperPackage.getLine();
      if(!line.contains("{")) return "";
      return line.substring(0, line.indexOf('{'));
   }

}
