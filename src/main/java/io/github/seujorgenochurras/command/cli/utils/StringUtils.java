package io.github.seujorgenochurras.command.cli.utils;

import java.util.Arrays;

public class StringUtils {
   private StringUtils(){}

   public static String removeArraySyntaxFromRawStringArr(String rawStringArr){
      String stringArrSyntaxRegex = "\\[|]|[?<=\"'],";
      return rawStringArr.replaceAll(stringArrSyntaxRegex, "");
   }
   public static String removeArraySyntaxFromRawStringArr(String[] rawStringArr){
      return removeArraySyntaxFromRawStringArr(Arrays.toString(rawStringArr));
   }
}
