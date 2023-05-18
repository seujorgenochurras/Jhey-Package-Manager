package io.github.seujorgenochurras.command.toolbox.builders;

import de.codeshelf.consoleui.elements.ListChoice;
import de.codeshelf.consoleui.elements.PageSizeType;
import de.codeshelf.consoleui.elements.PromptableElementIF;
import de.codeshelf.consoleui.elements.items.ListItemIF;
import de.codeshelf.consoleui.elements.items.impl.ListItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class ConsoleListBuilder implements CommandConsoleBuilder {

   private final String listMessage;
   private PageSizeType pageSizeType = PageSizeType.ABSOLUTE;
   private final List<Choice> availableChoices = new ArrayList<>();
   private String listName = "defaultName";
   private int pageSize = 5;

   public ConsoleListBuilder(String listMessage) {
      this.listMessage = listMessage;
   }

   public ConsoleListBuilder pageSizeType(PageSizeType pageSizeType) {
      this.pageSizeType = pageSizeType;
      return this;
   }

   public ConsoleListBuilder name(String listName) {
      this.listName = listName;
      return this;
   }

   public ConsoleListBuilder pageSize(int pageSize) {
      this.pageSize = pageSize;
      return this;
   }

   private ConsoleListBuilder addChoice(Choice item) {
      this.availableChoices.add(item);
      return this;
   }

   public ConsoleListItem newItem() {
      return new ConsoleListItem(this);
   }

   @Override
   public List<PromptableElementIF> build() {
      ListChoice buildResult = new ListChoice(listMessage, listName, pageSize, pageSizeType, parseChoicesListToItemList());
      return List.of(buildResult);
   }

   private List<ListItemIF> parseChoicesListToItemList() {
      return availableChoices.stream().map(choice -> new ListItem(choice.name, choice.value))
              .collect(Collectors.toList());
   }

   public static final class ConsoleListItem {

      private final ConsoleListBuilder consoleListBuilder;
      private String name;

      private String value;

      public ConsoleListItem(ConsoleListBuilder consoleListBuilder) {
         this.consoleListBuilder = consoleListBuilder;
      }

      public ConsoleListItem name(String name) {
         this.name = name;
         return this;
      }

      public ConsoleListItem value(String value) {
         this.value = value;
         return this;
      }

      public ConsoleListBuilder add() {
         if (value == null) {
            return consoleListBuilder.addChoice(new Choice(name, name));
         }
         return consoleListBuilder.addChoice(new Choice(name, value));
      }
   }

   private record Choice(String name, String value) {
   }
}