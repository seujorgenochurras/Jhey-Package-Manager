package io.github.seujorgenochurras.api.command.jpm.prompt;

import io.github.seujorgenochurras.api.domain.IDependency;
import io.github.seujorgenochurras.api.service.DependencyService;
import io.github.seujorgenochurras.command.util.ansi.LoadingAnimation;
import io.github.seujorgenochurras.command.toolbox.CommandConsole;
import io.github.seujorgenochurras.command.toolbox.builders.ConsoleListBuilder;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import static io.github.seujorgenochurras.command.toolbox.CommandConsole.ConsolePromptAnswer;

public class DependencyCollectionPrompter {
    private static final CommandConsole console = new CommandConsole();
    private static final Logger logger = Logger.getLogger(DependencyCollectionPrompter.class.getName());
    private SingleDependencyPrompter dependencyChosen;


    private DependencyCollectionPrompter() {
    }

    public static DependencyCollectionPrompter startPrompt() {
        return new DependencyCollectionPrompter();
    }

    public <T extends Collection<? extends IDependency>> DependencyCollectionPrompter promptDependenciesAsync(
            CompletableFuture<T> completableDependencies) {

        System.out.println("Found ... dependencies\n");
        LoadingAnimation.startAnimation(135);


        T dependenciesFound = tryGetCompletableFuture(completableDependencies);
        ConsoleListBuilder listBuilder = console
                .addNewListPrompt()
                .message("Found " + dependenciesFound.size() + " dependencies")
                .pageSize(5);

        dependenciesFound.forEach(dependencyFound ->
                listBuilder.newItem()
                        .name(dependencyFound.getFullName())
                        .add());

        DependencyService dependencyService = new DependencyService(dependenciesFound);

        LoadingAnimation.stopAllAnimations();
        ConsolePromptAnswer answer = console.prompt(listBuilder);
        IDependency promptResult = dependencyService.getDependencyByFullName(answer.getResult());
        this.dependencyChosen = SingleDependencyPrompter.dependencyToPrompt(promptResult);
        return this;
    }

    public SingleDependencyPrompter getDependencyChosen() {
        return dependencyChosen;
    }

    public <T> T tryGetCompletableFuture(CompletableFuture<T> completableFuture) {
        try {
            return completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            logger.severe("Something went terrible wrong");
            e.printStackTrace();
            return null;
        }
    }
}
