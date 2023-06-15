package io.github.seujorgenochurras.mapper.gradlew.tree.mapper.chain;

import java.util.ArrayList;
import java.util.List;

public class MapperResponsibilityChain {
    private final List<GradleTreeMapperChainHandler> handlers = new ArrayList<>();
    private TreeMapperPackage treeMapperPackage;
    private Boolean hasBeenHandled = false;

    private MapperResponsibilityChain() {
    }

    public static MapperResponsibilityChain startChain() {
        return new MapperResponsibilityChain();
    }

    public MapperResponsibilityChain addHandler(GradleTreeMapperChainHandler handler) {
        this.handlers.add(handler);
        return this;
    }

    public TreeMapperPackage getTreeMapperPackage() {
        return treeMapperPackage;
    }

    public void setHasBeenHandled(boolean hasBeenHandled) {
        this.hasBeenHandled = hasBeenHandled;
    }

    public void handlePackage(TreeMapperPackage treeMapperPackage) {
        this.treeMapperPackage = treeMapperPackage;
        for (GradleTreeMapperChainHandler handler : handlers) {
            handler.handle(this);
            if (hasBeenHandled) break;
        }
        hasBeenHandled = false;
    }

    @Override
    public String toString() {
        return "MapperResponsibilityChain{" +
                "treeMapperPackage=" + treeMapperPackage +
                ", handlers=" + handlers +
                ", hasBeenHandled=" + hasBeenHandled +
                '}';
    }
}
