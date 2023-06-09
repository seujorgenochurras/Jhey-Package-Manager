package io.github.seujorgenochurras.domain;

public class PluginDeclaration {
   private String rawDeclaration;
   private int declarationLine;
   private AbstractPlugin plugin;

   public PluginDeclaration(String rawDeclaration, int declarationLine) {
      this.rawDeclaration = rawDeclaration;
      this.declarationLine = declarationLine;
   }

   public String getRawDeclaration() {
      return rawDeclaration;
   }

   public PluginDeclaration setRawDeclaration(String rawDeclaration) {
      this.rawDeclaration = rawDeclaration;
      return this;
   }

   public int getDeclarationLine() {
      return declarationLine;
   }

   public PluginDeclaration setDeclarationLine(int declarationLine) {
      this.declarationLine = declarationLine;
      return this;
   }

   public AbstractPlugin toPluginObject() {
      if (plugin == null) {
         this.plugin = new AbstractPlugin(rawDeclaration.replaceAll("[()\"]", ""));
      }
      return plugin;
   }
}
