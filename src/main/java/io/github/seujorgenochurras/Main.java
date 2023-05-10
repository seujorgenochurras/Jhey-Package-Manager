package io.github.seujorgenochurras;

import io.github.seujorgenochurras.command.CliHandler;


public class Main {
   public static void main(String[] args) {

      try {
         String[] aArgs = {"Cli", "-m=\"cokc\""};
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