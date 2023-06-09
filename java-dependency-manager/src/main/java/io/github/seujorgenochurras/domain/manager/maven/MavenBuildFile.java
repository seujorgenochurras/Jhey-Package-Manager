package io.github.seujorgenochurras.domain.manager.maven;

import io.github.seujorgenochurras.domain.AbstractPlugin;
import io.github.seujorgenochurras.domain.dependency.Dependency;
import io.github.seujorgenochurras.mapper.DependencyManagerFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MavenBuildFile implements DependencyManagerFile {

   private static final Logger logger = Logger.getLogger(MavenBuildFile.class.getName());
   private final File rootFile;
   private Transformer transformer;
   private DOMSource domSource;

   private List<Dependency> dependencies = new ArrayList<>();
   private List<AbstractPlugin> plugins = new ArrayList<>();

   private Document pomFileDocument;

   public MavenBuildFile(File rootFile) {
      this.rootFile = rootFile;
      initComponents();
   }

   private void initComponents() {
      tryInitTransformer();
      tryInitPomFileDocument();
      initDomSource();
   }

   private void tryInitPomFileDocument() {
      try {
         DocumentBuilder documentBuilder = DocumentBuilderFactory.newDefaultInstance().newDocumentBuilder();
         this.pomFileDocument = documentBuilder.parse(rootFile);
      } catch (ParserConfigurationException | IOException | SAXException e) {
         logger.severe(e.getMessage());
         e.printStackTrace();
      }
   }

   private void initDomSource() {
      this.domSource = new DOMSource(pomFileDocument);
   }

   private void tryInitTransformer() {
      try {
         this.transformer = TransformerFactory.newDefaultInstance().newTransformer();
         transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
      } catch (TransformerConfigurationException e) {
         logger.severe(e.getMessage());
         e.printStackTrace();
      }
   }

   @Override
   public void addDependency(Dependency dependency) {
      pomFileDocument.getElementsByTagName("dependencies").item(0).appendChild(createDependencyElement(dependency));
      updateMavenPomFile();
   }


   @Override
   public <T extends AbstractPlugin> void addPlugin(T plugin) {
      //nope
   }

   @Override
   public List<? extends AbstractPlugin> getPlugins() {
      return plugins;
   }

   public MavenBuildFile setPlugins(List<AbstractPlugin> plugins) {
      this.plugins = plugins;
      return this;
   }

   @Override
   public List<Dependency> getDependencies() {
      return dependencies;
   }

   public MavenBuildFile setDependencies(List<Dependency> dependencies) {
      this.dependencies = dependencies;
      return this;
   }

   @Override
   public void removeDependency(Dependency dependency) {
      //Not yet
   }

   @Override
   public void commentDependency(Dependency dependency) {
      //Next update
   }

   @Override
   public <T extends AbstractPlugin> void removePlugin(T plugin) {
      //fuck you
   }

   private void updateMavenPomFile() {
      tryUpdatePomFile();
   }

   private Element createDependencyElement(Dependency dependency) {
      Element dependencyElement = this.pomFileDocument.createElement("dependency");

      Element artifactElement = pomFileDocument.createElement("artifactId");
      artifactElement.setTextContent(dependency.getArtifact());

      Element groupIdElement = pomFileDocument.createElement("groupId");
      groupIdElement.setTextContent(dependency.getGroupName());

      Element versionElement = pomFileDocument.createElement("version");
      versionElement.setTextContent(dependency.getVersion());

      dependencyElement.appendChild(artifactElement);
      dependencyElement.appendChild(groupIdElement);
      dependencyElement.appendChild(versionElement);
      dependencyElement.normalize();

      return dependencyElement;
   }

   private void tryUpdatePomFile() {
      try {
         this.transformer.transform(domSource, new StreamResult(rootFile));
      } catch (TransformerException e) {
         logger.severe(e.getMessage());
         e.printStackTrace();
      }
   }
}
