package io.github.seujorgenochurras.command.annotation;

import io.github.seujorgenochurras.command.AbstractCommand;
import jline.internal.Nullable;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SubCommand {
   @Nullable
   Class<? extends AbstractCommand> of() default AbstractCommand.class;
   String name();
}
