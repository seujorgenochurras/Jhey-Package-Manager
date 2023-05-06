package io.github.seujorgenochurras.command.annotation;

import io.github.seujorgenochurras.command.ICommand;
import jline.internal.Nullable;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SubCommand {
   @Nullable
   Class<? extends ICommand> of() default NotImplementedFatherCommand.class;
   //If the annotation is being used in an inner class, then its father command will be the outer class.

   String name();

   final class NotImplementedFatherCommand implements ICommand {
      @Override
      public String[] getNames() {
         return new String[0];
      }

      @Override
      public void invoke(String[] params) {
      }
   }
}
