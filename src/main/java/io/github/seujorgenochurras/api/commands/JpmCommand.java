package io.github.seujorgenochurras.api.commands;

import io.github.seujorgenochurras.api.domain.Dependency;
import io.github.seujorgenochurras.api.service.DependencyService;
import io.github.seujorgenochurras.command.ICommand;
import io.github.seujorgenochurras.command.arg.CommandArgumentBuilder;
import io.github.seujorgenochurras.command.arg.flag.CommandArgs;
import io.github.seujorgenochurras.command.arg.flag.FlagPatternCollection;
import io.github.seujorgenochurras.command.reflections.common.ValidFlagArgumentTypes;

import java.util.ArrayList;

public class JpmCommand implements ICommand {

   private final DependencyService dependencyService = new DependencyService();
   @Override
   public void invoke(CommandArgs args) {
      String libName = args.getFlagByName("i").getValueAsString();

      ArrayList<Dependency> dependenciesFound = dependencyService.searchForDependency(libName);

      System.out.println(dependenciesFound + "\n");
   }

   @Override
   public FlagPatternCollection commandArgsPattern() {
      return CommandArgumentBuilder
              .startBuild()

              .newFlag()
              .aliases("-i", "--install")
              .argType(ValidFlagArgumentTypes.STRING)
              .addFlag()

              .build();
   }

   @Override
   public String[] getNames() {
      return new String[]{"jpm"};
   }

}
