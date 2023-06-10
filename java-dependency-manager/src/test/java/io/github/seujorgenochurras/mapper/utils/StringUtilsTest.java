package io.github.seujorgenochurras.mapper.utils;

import static org.junit.jupiter.api.Assertions.*;

import io.github.seujorgenochurras.utils.StringUtils;
import org.junit.jupiter.api.Test;


class StringUtilsTest {

   @Test
   void isGettingAllLineTextUsingCharIndex(){
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
      assertEquals("aulavaa {meuPau", StringUtils.getAllLineTextUsingCharIndex(163 , lineToTest));
   }
   @Test
   void isGettingTextBefore(){
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
      assertEquals("repositories ", StringUtils.getTextBefore(191 , lineToTest));
   }
}
