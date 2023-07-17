package io.github.seujorgenochurras.api.command.jpm;

import io.github.seujorgenochurras.api.command.jpm.prompt.DependencyCollectionPrompter;
import io.github.seujorgenochurras.api.domain.Dependency;
import io.github.seujorgenochurras.api.domain.IDependency;
import io.github.seujorgenochurras.api.service.MavenService;
import io.github.seujorgenochurras.command.toolbox.CommandToolBox;
import io.github.seujorgenochurras.domain.dependency.DependencyBuilder;
import io.github.seujorgenochurras.mapper.DependencyManagerFile;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@CommandLine.Command(name = "jpm", description = "Manages your dependency manager file", version = "0.5.0", mixinStandardHelpOptions = true)
public class JpmCommand implements Runnable {
    private final MavenService mavenService = new MavenService();

    private final CommandToolBox commandToolBox;

    public JpmCommand(CommandToolBox commandToolBox) {
        this.commandToolBox = commandToolBox;
    }

    @CommandLine.Option(names = {"-i", "--install"}, description = "The dependency name that you want to install",
            required = true)
    private String dependencyName;

    @Override
    public void run() {
        CompletableFuture<ArrayList<Dependency>> dependenciesFound = mavenService.async().searchForDependencyAsync(dependencyName);
        mavenService.async().stopExecutors();

        IDependency dependencyChosen = DependencyCollectionPrompter.startPrompt()
                .promptDependenciesAsync(dependenciesFound)
                .getDependencyChosen()
                .promptVersion()
                .getResultedDependency();

        DependencyManagerFile dependencyManagerFile = commandToolBox.getDependencyManager();
        dependencyManagerFile.addDependency(DependencyBuilder.startBuild()
                .group(dependencyChosen.getGroupName())
                .artifact(dependencyChosen.getArtifactName())
                .version(dependencyChosen.getVersion())
                .buildResult());

        System.out.println("Successfully installed " + dependencyChosen.getFullName());
    }
}
