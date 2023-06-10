package io.github.seujorgenochurras.mapper.gradlew.tree.mapper;

import io.github.seujorgenochurras.mapper.gradlew.tree.GradleTree;
import io.github.seujorgenochurras.mapper.gradlew.tree.GradleTreeBuilder;
import io.github.seujorgenochurras.mapper.gradlew.tree.node.GradleNode;
import io.github.seujorgenochurras.mapper.gradlew.tree.node.GradleNodeGroup;
import io.github.seujorgenochurras.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static io.github.seujorgenochurras.utils.StringUtils.getAllLineTextUsingCharIndex;
import static io.github.seujorgenochurras.utils.StringUtils.getTextBefore;

public class GradleTreeFileMapper implements GradleTreeMapper {

    private final File fileToMap;

    private GradleTreeFileMapper(File fileToMap) {
        this.fileToMap = fileToMap;
    }

    public static GradleTree mapFile(File file) {
        return new GradleTreeFileMapper(file).map();
    }

    @Override
    public GradleTree map() {
        List<GradleNodeGroup> nodeGroups = mapAllNodeGroups();
        return GradleTreeBuilder.startBuild()
                .childNodeGroups(nodeGroups)
                .getBuildResult();
    }

    private List<GradleNodeGroup> mapAllNodeGroups() {
        String fileToMapContents = FileUtils.getFileAsString(fileToMap);

        List<GradleNodeGroup> groupsFound = new ArrayList<>();

        AtomicInteger indexOfCurrentChar = new AtomicInteger(-1);

        AtomicInteger openCurlyBracesCount = new AtomicInteger(0);

        List<GradleNodeGroup> previousNodeGroups = new ArrayList<>();

        fileToMapContents.chars().forEach((character) -> {
            indexOfCurrentChar.getAndIncrement();


            if (character == '{' || character == '}' || isInsideNodeGroup(openCurlyBracesCount.get())) {
                GradleNodeGroup currentNodeGroup;
                if (previousNodeGroups.size() >= 1) {
                    currentNodeGroup = previousNodeGroups.get(previousNodeGroups.size() - 1);
                } else {
                    currentNodeGroup = new GradleNodeGroup();
                }
                if (character == '{') {

                    previousNodeGroups.add(currentNodeGroup);

                    openCurlyBracesCount.getAndIncrement();

                    currentNodeGroup.setGroupName(getTextBefore(indexOfCurrentChar.get(), fileToMapContents));

                    if (isInsideNodeGroup(openCurlyBracesCount.get())) {
                        GradleNodeGroup fatherNodeGroup = previousNodeGroups.get(openCurlyBracesCount.get() - 1);
                        fatherNodeGroup.appendNodeGroup(currentNodeGroup);
                    }

                } else if (character == '}') {
                    openCurlyBracesCount.decrementAndGet();
                    previousNodeGroups.remove(openCurlyBracesCount.get());
                } else {
                    currentNodeGroup.addNode(new GradleNode(getAllLineTextUsingCharIndex(indexOfCurrentChar.get(), fileToMapContents)));
                }
                if (!isInsideNodeGroup(openCurlyBracesCount.get())) {
                    groupsFound.add(currentNodeGroup);
                }
            }
        });
        return groupsFound;
    }


    private boolean isInsideNodeGroup(int curlyBracesCount) {
        return curlyBracesCount > 0;
    }
/*

PP = curly braces open count
PJ = list of nodeGroups

publishing { PP = 0; PJ = [publishing]
    publications { PP = 1; PJ = [publishing, publications]
        create<MavenPublication>("mavenJava") { PP = 2
            groupId = group
            artifactId = "java-dependency-manager"
            version = versionNum
            from(components["java"])

            pom { PP = 3; PJ = [publishing, publications, pom]
                name.set("Java Dependency Manager")
                description.set("A library to manage either gradle (kotlin/groovy) or Maven (pom) files")
                url.set("https://github.com/seujorgenochurras/java-dependency-manager")

                licenses { PP = 4; PJ = [publishing, publications, pom, licenses]
                    license {PP = 5; PJ = [publishing, publications, pom, licenses, LINCENCE]
                        name.set("MIT License")
                        url.set("http://www.opensource.org/licenses/mit-license.php")
                    } PP = 4; PJ = [publishing, publications, pom, LINCENCES]
                } PP = 3; PJ = [publishing, publications, pom]
                developers PP = 4; PJ = [publishing, publications, pom, DEVELOPERS]{
                    developer {
                        id.set("LILJ")
                        name.set("Little Jhey")
                        email.set("jjotinha_oficial@outlook.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/java-dependency-manager")
                    developerConnection.set("scm:git:git://github.com/java-dependency-manager")
                    url.set("https://github.com/seujorgenochurras/java-dependency-manager")
                }
            }
        }
    }
    repositories {
        maven {

            name = "OSSRH"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = project.properties["repoUser"].toString()
                password = project.properties["repoPassword"].toString()
            }
        }
    }
}

 */

}
