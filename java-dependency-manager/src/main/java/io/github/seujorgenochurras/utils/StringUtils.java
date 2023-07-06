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

    public static boolean stringContainsAnyMatchesOf(String regex, String stringToCheck){
        return !getAllMatchesOfMatcher(generateStringMatcherFromRegex(stringToCheck, regex)).isEmpty();
    }
    public static boolean stringStartsWithRegex(String regex, String stringToCheck){
        return stringToCheck.startsWith(getFirstMatchOfString(regex, stringToCheck));
    }

    /**
     * @param a
     * @param b
     * @return true if {@code a} happens in {@code b} in order, it just doesn't care about spaces or other letters before or after
     */
    public static boolean weakEquals(String a, String b){
       b = b.replace(" ", "");
        a = a.replace(" ", "");
        return a.contains(b);
    }
}
