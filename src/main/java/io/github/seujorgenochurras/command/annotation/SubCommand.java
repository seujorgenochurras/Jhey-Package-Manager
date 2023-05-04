package io.github.seujorgenochurras.command.annotation;

import io.github.seujorgenochurras.command.AbstractCommand;
import jline.internal.Nullable;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SubCommand {
   @Nullable
   Class<? extends AbstractCommand> of() default NotImplementedFatherCommand.class;
   //If the annotation is being used in an inner class, then its father command will be the outer class.

   String name();

   final class NotImplementedFatherCommand extends AbstractCommand {
      @Override
      public void invoke(String[] params) {
      }
   }
}
