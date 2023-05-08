package io.github.seujorgenochurras.command.arg;

import io.github.seujorgenochurras.command.Flag;

import java.util.List;

public class CliArgument {
   private final List<Flag> flagList;

   public CliArgument(List<Flag> flagList) {
      this.flagList = flagList;
   }
}
