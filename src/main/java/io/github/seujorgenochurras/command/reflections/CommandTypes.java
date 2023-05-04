package io.github.seujorgenochurras.command.reflections;

import io.github.seujorgenochurras.command.annotation.Command;
import io.github.seujorgenochurras.command.annotation.SubCommand;

import java.lang.annotation.Annotation;

public enum CommandTypes {
   COMMAND(Command.class),
   SUB_COMMAND(SubCommand.class);

   public final Class<? extends Annotation> commandTypeClass;

   CommandTypes(Class<? extends Annotation> commandTypeClass) {
      this.commandTypeClass = commandTypeClass;
   }
}
