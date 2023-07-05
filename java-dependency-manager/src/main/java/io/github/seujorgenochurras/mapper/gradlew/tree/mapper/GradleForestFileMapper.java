package io.github.seujorgenochurras.mapper.gradlew.tree.mapper;

import io.github.seujorgenochurras.mapper.gradlew.tree.GradleForest;
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
        return new GradleForest(gradleTrees);
    }

    private Set<GradleTree> mapAllNodeGroups() {
        String fileToMapContents = FileUtils.getFileAsString(fileToMap);

        TreeMapperPackage treeMapperPackage = new TreeMapperPackage();

        MapperResponsibilityChain packageResponsibilityChain = MapperResponsibilityChain.startChain()
                .addHandler(new OnCharIsOpenCurlyBraces())
                .addHandler(new OnCharIsCloseCurlyBraces())
                .addHandler(new OnCharIsInsideNodeGroup());

        for (String line : fileToMapContents.lines().toList()) {
            treeMapperPackage.setLine(line);
            packageResponsibilityChain.handlePackage(treeMapperPackage);
        }

        return treeMapperPackage.getGradleTreesFound();
    }
}