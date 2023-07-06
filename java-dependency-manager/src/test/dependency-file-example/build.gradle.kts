plugins {
    id("java-library")
    id("maven-publish")
    id("signing")
}
repositories {
    mavenCentral()
}
dependencies {
    implementation("test.test:testImpl:69.42.0")
    implementation("test.222test:tes222tImpl:629.422.0")
    implementation("test111.test:tes111tImpl:69.11142.0")
implementation ("test.test:testImpl:69.42.0")
implementation ("test.222test:tes222tImpl:629.422.0")
implementation ("test111.test:tes111tImpl:69.11142.0")
}
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = group
            artifactId = "java-dependency-manager"
            version = versionNum
            from(components["java"])

            pom {
                name.set("Java Dependency Manager")
                description.set("A library to manage either gradle (kotlin/groovy) or Maven (pom) files")
                url.set("https://github.com/seujorgenochurras/java-dependency-manager")

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("http://www.opensource.org/licenses/mit-license.php")
}
}
                developers {
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
java {
    withJavadocJar()
    withSourcesJar()
}
signing {
    sign(publishing.publications["mavenJava"])
}
tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
