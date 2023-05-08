package io.github.seujorgenochurras;


import io.github.seujorgenochurras.command.CliHandler;

import java.util.Arrays;

public class Main {
   public static void main(String[] args) {

      System.out.println(Arrays.toString(args));
      try {

         CliHandler.handleCliArguments(args);

      } catch (NullPointerException e) {
         System.out.println("Command not found!");
      }catch (ArrayIndexOutOfBoundsException e){
         System.out.println("No args provided");
      }
   }
}