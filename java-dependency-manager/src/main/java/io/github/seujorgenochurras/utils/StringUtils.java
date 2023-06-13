package io.github.seujorgenochurras.utils;

import java.util.HashMap;
import java.util.List;
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

    public static int getIndexOfStringWithRegex(String string, String regex) {

        Matcher matcher = Pattern.compile(regex).matcher(string);
        matcher.find();
        return matcher.end();
    }
    public static String getFirstMatchOfString(String regex, String string){
        Matcher matcher = generateStringMatcherFromRegex(string, regex);
        matcher.find();

        try{
            return matcher.group();
        }catch (IndexOutOfBoundsException | IllegalStateException e){
            return "null";
        }

    }

    public static char lastCharOfString(String string) {
        if (string.length() == 0) return '\0';
        return string.charAt(string.length() - 1);
    }

    public static String[] trimStringArr(String[] stringsArr) {
        String[] trimmedStringArr = new String[stringsArr.length];
        for (int i = 0; i < stringsArr.length; i++) {
            trimmedStringArr[i] = stringsArr[i].trim();
        }
        return trimmedStringArr;
    }

    public static String getAllLineTextUsingCharIndex(int charIndex, String stringToGetLineFrom) {
        List<String> lines = stringToGetLineFrom.lines().toList();
        int charCount = 0;
        String lineFound = "";
        for(String line : lines){
            charCount += line.length()+1;
            if(charCount >= charIndex) {
                lineFound = line;
                break;
            }
        }
        return lineFound;
    }

    public static String getTextBeforeChar(int charIndex, String stringToGetLineFrom){
        String allTextAtCharIndex = getAllLineTextUsingCharIndex(charIndex, stringToGetLineFrom);
        char charContents = stringToGetLineFrom.charAt(charIndex);

        return allTextAtCharIndex.substring(0, allTextAtCharIndex.indexOf(charContents));

    }

    public static boolean stringContainsAnyMatchesOf(String regex, String stringToCheck){
        return !getAllMatchesOfMatcher(generateStringMatcherFromRegex(stringToCheck, regex)).isEmpty();
    }
    public static boolean stringStartsWithRegex(String regex, String stringToCheck){
        return stringToCheck.startsWith(getFirstMatchOfString(regex, stringToCheck));
    }
}
