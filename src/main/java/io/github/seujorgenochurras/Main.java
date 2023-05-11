package io.github.seujorgenochurras;

import io.github.seujorgenochurras.command.cli.CliHandler;


public class Main {
   public static void main(String[] args) {

      try {
         String[] aArgs = {"Cli", "-f"};

         CliHandler.handleCliArguments(aArgs);

      } catch (NullPointerException e) {
         System.out.println("Command not found!");
         System.out.println(e.getMessage());
         throw e;
      }catch (ArrayIndexOutOfBoundsException e){
         System.out.println("No args provided");
         throw e;
      }
   }
}