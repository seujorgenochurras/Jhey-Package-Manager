package io.github.seujorgenochurras.mapper.utils;

import static io.github.seujorgenochurras.utils.StringUtils.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class StringUtilsTest {

   @Test
   void stringStartsWithRegexTest(){
      assertTrue(stringStartsWithRegex("[0-9]", "1212123"));
      assertFalse(stringStartsWithRegex("[0-9]", "awdjasodjasozxc"));
      assertTrue(stringStartsWithRegex("implementation|testImplementation", "implementation (\"meuSaco\")"));
      assertFalse(stringStartsWithRegex("implementation|testImplementation", "meu saco (testImplementation)"));
   }

   @Test
   void isGettingAllLineTextUsingCharIndex() {
      String lineToTest = "awijdaoiwjdoaiw{\n" +
              "plugins {meuPau\n" +
              "id 'org.springframework.boot' version '3.0.2'\n" +
              "id 'java-library'\n" +
              "id 'maven-publish'\n" +
              "id 'signing'\n" +
              "}\n" +
              "awijdaoiwjdoaiw{\n" +
              "aulavaa {meuPau\n" +
              "id 'EMAUHDIAUWDAW\n" +
              "id 'AWDA-AWD'\n" +
              "id 'AWDA-publish'\n" +
              "id 'DAWDA'\n" +
              "}";
      assertEquals("aulavaa {meuPau", getAllLineTextUsingCharIndex(163, lineToTest));
   }

   @Test
   void isGettingTextBefore() {
      String lineToTest = "plugins \n" +
              "    id 'org.springframework.boot' version '3.0.2'\n" +
              "    id 'java-library'\n" +
              "    id 'maven-publish'\n" +
              "    id 'signing'\n" +
              "}\n" +
              "\n" +
              "group 'io.github.seujorgenochurras'\n" +
              "version '1.2.1'\n" +
              "\n" +
              "\n" +
              "repositories {\n" +
              "    mavenCentral()\n" +
              "}\n" +
              "\n";
      assertEquals("repositories ", getTextBeforeChar(191, lineToTest));
   }

   @Test
   void isStringContainsAnyMatchesOfWorking() {
      String dependency1 = "implementation (\"org.seleniumhq.selenium:selenium-java:4.8.1\")";
      String dependency2 = "implementation ('io.github.seujorgenochurras:selenium-custom-elements:0.1.0')";
      String dependency3 = "implementation ('com.google.code.gson:gson:2.10.1')";
      String notDependency1 = "MEU PAU DE ASAS";
      String notDependency2 = "{}{})()()\"\" FODASSE!";
      String notDependency3 = "rem ipsum dolor sit amet, consectetur ad";

      String stringImplementationRegex = "(testImplementation|implementation|runtime_only|testRuntimeOnly).*";

      assertTrue(stringContainsAnyMatchesOf(stringImplementationRegex, dependency1));
      assertTrue(stringContainsAnyMatchesOf(stringImplementationRegex, dependency2));
      assertTrue(stringContainsAnyMatchesOf(stringImplementationRegex, dependency3));

      assertFalse(stringContainsAnyMatchesOf(stringImplementationRegex, notDependency1));
      assertFalse(stringContainsAnyMatchesOf(stringImplementationRegex, notDependency2));
      assertFalse(stringContainsAnyMatchesOf(stringImplementationRegex, notDependency3));
   }
}
