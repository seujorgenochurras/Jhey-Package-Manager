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
   private final List<String> availableChoices = new ArrayList<>();
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

   private ConsoleListBuilder addItem(String item) {
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

   private List<ListItemIF> parseChoicesListToItemList(){
      return availableChoices.stream().map(choiceName -> new ListItem(choiceName, choiceName))
      .collect(Collectors.toList());
   }

   public static final class ConsoleListItem {

      private final ConsoleListBuilder consoleListBuilder;
      private String name;

      public ConsoleListItem(ConsoleListBuilder consoleListBuilder) {
         this.consoleListBuilder = consoleListBuilder;
      }

      public ConsoleListItem name(String name) {
         this.name = name;
         return this;
      }

      public ConsoleListBuilder add() {
         return consoleListBuilder.addItem(this.name);
      }
   }
}