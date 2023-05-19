package io.github.seujorgenochurras.command.toolbox.builders;

import de.codeshelf.consoleui.elements.ConfirmChoice;
import de.codeshelf.consoleui.elements.PromptableElementIF;

import java.util.List;

public final class ConsoleConfirmBuilder implements CommandConsoleBuilder{
   private String name;
   private String message;
   private ConfirmChoice.ConfirmationValue defaultConfirmationValue = ConfirmChoice.ConfirmationValue.YES;

   public ConsoleConfirmBuilder name(String name) {
      this.name = name;
      return this;
   }

   public ConsoleConfirmBuilder message(String message) {
      this.message = message;
      return this;
   }

   public ConsoleConfirmBuilder defaultValue(ConfirmChoice.ConfirmationValue defaultConfirmationValue) {
      this.defaultConfirmationValue = defaultConfirmationValue;
      return this;
   }
   @Override
   public List<PromptableElementIF> build() {
      return List.of(new ConfirmChoice(message, name, defaultConfirmationValue));
   }
}
