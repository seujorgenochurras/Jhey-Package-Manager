package io.github.seujorgenochurras.command;

public interface ICommand {

   String[] getNames();

   void invoke(String[] args);
}
