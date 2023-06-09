package io.github.seujorgenochurras.utils;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class BetterNodeList {
   private final NodeList rootNodeList;

   public BetterNodeList(NodeList rootNodeList) {
      this.rootNodeList = rootNodeList;
   }
   public void forEachChild(Consumer<Node> action){
      if(rootNodeList.getLength() < 0) throw new IllegalStateException("Node list cannot be empty");
      for(int i = 0; i < rootNodeList.getLength() -1; i++){
         action.accept(rootNodeList.item(i));
      }
   }
   public Node getChildNodeByName(String childNodeName){
      AtomicReference<Node> nodeFound = new AtomicReference<>();
      this.forEachChild(node -> {
         if(node.getNodeName().equals(childNodeName)){
            nodeFound.set(node);
         }
      });
      return nodeFound.get();
   }

   public NodeList getRootNodeList() {
      return rootNodeList;
   }
}
