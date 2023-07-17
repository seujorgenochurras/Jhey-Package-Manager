package io.github.seujorgenochurras;

import io.github.seujorgenochurras.api.command.jpm.JpmCommand;
import io.github.seujorgenochurras.command.toolbox.CommandToolBox;
import org.fusesource.jansi.AnsiConsole;
import picocli.CommandLine;

public class Main {
    public static void main(String[] args) {
        AnsiConsole.systemInstall(); //needed because windows has no AnsiConsole by default

        CommandToolBox toolBox = new CommandToolBox();
        new CommandLine(new JpmCommand(toolBox)).execute(args);

    }
}
