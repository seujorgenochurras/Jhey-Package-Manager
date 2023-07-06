package io.github.seujorgenochurras.domain.plugin.util;

import io.github.seujorgenochurras.domain.plugin.Plugin;

public class StringToPluginParser {
   private StringToPluginParser(){}
   public static Plugin stringToPlugin(String pluginDeclaration){
      return new Plugin(pluginDeclaration.replaceAll("[()\"]", ""));
   }
}
