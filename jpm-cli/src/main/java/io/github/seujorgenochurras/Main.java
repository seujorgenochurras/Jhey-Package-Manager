package io.github.seujorgenochurras;

import io.github.seujorgenochurras.command.cli.CliHandler;
import io.github.seujorgenochurras.command.cli.utils.ansi.LoadingAnimation;
import org.fusesource.jansi.AnsiConsole;

public class Main {
    public static void main(String[] args) {
        AnsiConsole.systemInstall(); //needed because windows has no AnsiConsole by default

        CliHandler.handleCliArguments(args);
        LoadingAnimation.animateLoading(1);

        System.out.println("meu pau");
    }
}
