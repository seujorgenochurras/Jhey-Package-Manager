package io.github.seujorgenochurras.utils;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

   private StringUtils() {
      //Util class
   }

   public static Matcher generateStringMatcherFromRegex(String string, String regex) {
      Pattern pattern = Pattern.compile(regex);
      return pattern.matcher(string);
   }

   public static HashMap<Integer, String> getAllMatchesOfMatcher(Matcher matcher) {
      HashMap<Integer, String> matchingStrings = new HashMap<>();
      while (matcher.find()) {
         matchingStrings.put(matcher.end(0), matcher.group(0));
      }
      return matchingStrings;
   }

   /**
    * This function generates a generic regex for any block of code. <br>
    * The regex created by this function won't work for functions.<br>
    * Here's an example of a block that this function creates regex to
    * <pre>
    *    dependencies{
    *       ...
    *    }
    * </pre>
    *
    * @param codeBlockName the code block name
    * @return a regex of the code block
    */
   public static String generateRegexForCodeBlock(String codeBlockName) {
      return "(" + codeBlockName + ".*)\\{([^}]+)}";
   }

   public static int getIndexOfStringWithRegex(String string, String regex) {

      Matcher matcher = Pattern.compile(regex).matcher(string);
      matcher.find();
      return matcher.end();
   }

   public static char lastCharOfString(String string) {
      if (string.length() == 0) return '\0';
      return string.charAt(string.length() - 1);
   }
  
   public static String[] trimStringArr(String[] stringsArr){
      String[] trimmedStringArr = new String[stringsArr.length];
      for(int i = 0; i < stringsArr.length; i++){
         trimmedStringArr[i] = stringsArr[i].trim();
      }
      return trimmedStringArr;
   }
}
