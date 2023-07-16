package io.github.seujorgenochurras.command.toolbox;

import io.github.seujorgenochurras.command.arg.CommandArgs;
import io.github.seujorgenochurras.manager.DependencyManager;
import io.github.seujorgenochurras.mapper.DependencyManagerFile;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CommandToolBox {

    private static final ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final CommandArgs commandArgs;
    private final String currentDirPath = System.getProperty("user.dir");
    private DependencyManagerFile dependencyManager;

    public CommandToolBox(CommandArgs commandArgs) {
        this.commandArgs = commandArgs;
        generateDependencyManagerAsync().whenComplete((result, throwable) ->
                this.dependencyManager = result);
        executorService.shutdown();
    }

    private CompletableFuture<DependencyManagerFile> generateDependencyManagerAsync() {
        CompletableFuture<DependencyManagerFile> future = new CompletableFuture<>();
        executorService.submit(() ->
                future.complete(DependencyManager.getDependencyManagerFile(currentDirPath))
        );
        executorService.shutdown();
        return future;
    }

    public CommandArgs getCommandArgs() {
        return commandArgs;
    }

    public String getCurrentDirPath() {
        return currentDirPath;
    }

    public DependencyManagerFile getDependencyManager() {
        return dependencyManager;
    }

}
