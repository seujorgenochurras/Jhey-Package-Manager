package io.github.seujorgenochurras.api.command.jpm;

import io.github.seujorgenochurras.api.command.jpm.prompt.DependencyCollectionPrompter;
import io.github.seujorgenochurras.api.domain.Dependency;
import io.github.seujorgenochurras.api.domain.IDependency;
import io.github.seujorgenochurras.api.service.MavenService;
import io.github.seujorgenochurras.command.ICommand;
import io.github.seujorgenochurras.command.arg.CommandArgumentBuilder;
import io.github.seujorgenochurras.command.arg.flag.pattern.FlagPatternCollection;
import io.github.seujorgenochurras.command.arg.flag.ValidFlagArgumentTypes;
import io.github.seujorgenochurras.command.cli.utils.ansi.LoadingAnimation;
import io.github.seujorgenochurras.command.toolbox.CommandToolBox;
import io.github.seujorgenochurras.domain.dependency.DependencyBuilder;
import io.github.seujorgenochurras.mapper.DependencyManagerFile;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class JpmCommand implements ICommand {
    private final MavenService mavenService = new MavenService();

    @Override
    public void invoke(CommandToolBox toolBox) {
        String libName = toolBox.getCommandArgs().getFlagByName("i").getValueAsString();

        CompletableFuture<ArrayList<Dependency>> dependenciesFound = mavenService.async().searchForDependencyAsync(libName);

        mavenService.async().stopExecutors();

        IDependency dependencyChosen = DependencyCollectionPrompter.startPrompt()
                .promptDependenciesAsync(dependenciesFound)
                .getDependencyChosen()
                .promptVersion()
                .getResultedDependency();


        DependencyManagerFile dependencyManagerFile = toolBox.getDependencyManager();
        dependencyManagerFile.addDependency(DependencyBuilder.startBuild()
                .group(dependencyChosen.getGroupName())
                .artifact(dependencyChosen.getArtifactName())
                .version(dependencyChosen.getVersion())
                .buildResult());

        System.out.println("Successfully installed " + dependencyChosen.getFullName());
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
