package io.github.seujorgenochurras.mapper.gradlew.tree.mapper.chain.handler;

import io.github.seujorgenochurras.mapper.gradlew.tree.mapper.chain.GradleTreeMapperChainHandler;
import io.github.seujorgenochurras.mapper.gradlew.tree.mapper.chain.MapperResponsibilityChain;
import io.github.seujorgenochurras.mapper.gradlew.tree.mapper.chain.TreeMapperPackage;
import io.github.seujorgenochurras.mapper.gradlew.tree.node.GradleNode;

public class OnCharIsInsideNodeGroup implements GradleTreeMapperChainHandler {
   @Override
   public void handle(MapperResponsibilityChain currentChain) {
      if (!currentChain.getTreeMapperPackage().isMappingTree()) return;

      TreeMapperPackage treeMapperPackage = currentChain.getTreeMapperPackage();
      treeMapperPackage.getCurrentGradleTree().addNode(new GradleNode(treeMapperPackage.getLine()));
   }
}
