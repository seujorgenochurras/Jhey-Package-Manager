package io.github.seujorgenochurras.api.commands;

import java.util.Arrays;
import java.util.function.Consumer;

public class Args {
   private String[] rawArgs;

   public Args(String[] rawArgs) {
      this.rawArgs = rawArgs;
   }
   public void forEachArg(Consumer<String> callback){
      Arrays.stream(this.rawArgs).forEach(callback);

   }


}
