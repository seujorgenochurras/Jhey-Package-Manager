package io.github.seujorgenochurras.mapper.gradlew.tree.mapper.chain.handler;

import io.github.seujorgenochurras.mapper.gradlew.tree.mapper.chain.GradleTreeMapperChainHandler;
import io.github.seujorgenochurras.mapper.gradlew.tree.mapper.chain.MapperResponsibilityChain;
import io.github.seujorgenochurras.mapper.gradlew.tree.mapper.chain.TreeMapperPackage;

public class OnCharIsCloseCurlyBraces implements GradleTreeMapperChainHandler {
    @Override
    public void handle(MapperResponsibilityChain currentChain) {
        if (!currentChain.getTreeMapperPackage().getLine().contains("}")) return;
        TreeMapperPackage treeMapperPackage = currentChain.getTreeMapperPackage();

        if (treeMapperPackage.isInsideTree()) {
            treeMapperPackage.appendCurrentTreeToFatherTree();
        }
        treeMapperPackage.decrementOpenCurlyBracesCount();

        treeMapperPackage.removeLastNodeGroupFromPreviousNodeGroups();
        currentChain.setHasBeenHandled(true);
    }
}
