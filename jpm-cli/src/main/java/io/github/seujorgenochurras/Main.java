package io.github.seujorgenochurras;

import io.github.seujorgenochurras.command.cli.CliHandler;
import org.fusesource.jansi.AnsiConsole;

import java.time.OffsetDateTime;

public class Main {
    public static void main(String[] args) {
        AnsiConsole.systemInstall(); //needed because windows has no AnsiConsole by default

        CliHandler.handleCliArguments(args);
    }
}
