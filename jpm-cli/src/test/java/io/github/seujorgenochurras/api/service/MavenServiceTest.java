package io.github.seujorgenochurras.api.service;

import io.github.seujorgenochurras.api.domain.Dependency;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MavenServiceTest {
   MavenService mavenService = new MavenService();

   @Test
   void isRequestingDependencies() {
      ArrayList<Dependency> dependenciesFound =
              mavenService.searchForDependency("selenium", 10);
      assertEquals(10, dependenciesFound.size());
      dependenciesFound.forEach(dependency -> assertTrue(testDependency(dependency)));
   }
   @Test
    void isRequestingDependencyVersion(){
      List<Dependency> versionsFound =
              mavenService.searchVersionsOf(new Dependency("org.seleniumhq.selenium", "selenium", "2.0rc2"));
      versionsFound.forEach(versionFound -> assertTrue(testDependency(versionFound)));
      assertTrue(versionsFound.size() > 9);
   }

   boolean testDependency(Dependency dependency){

      return   dependency.getArtifactName() != null
              && dependency.getGroupName() != null
              && dependency.getVersion() != null;
   }
}
