package io.github.seujorgenochurras.api.commands;

import io.github.seujorgenochurras.command.Command;
import io.github.seujorgenochurras.command.arg.ArgumentBuilder;
import io.github.seujorgenochurras.command.arg.FlagArgumentTypes;

public class HelloWorldCommand extends Command{


   public void init(){
//      Args helloWorldArgs = ArgumentBuilder.startBuild()
//              .flag("-m").followedBy(FlagArgumentTypes.STRING).addFlag()
//              .build();
//
//      Command command = CommandBuilder.startBuild()
//              .arguments(helloWorldArgs)
//              .name("HelloW")
//              .build();
//
//      command.getArgs().getFlagByName("-m").getFlagArguments()[0];

   }

   @Override
   public void invoke(String[] params) {
      System.out.println("Hello, World!");
   }
   @Override
   public String getName(){
      return "HelloW";
   }

}
