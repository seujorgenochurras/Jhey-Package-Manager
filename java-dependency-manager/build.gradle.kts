plugins {
    id("java-library")
    id("maven-publish")
    id("signing")
}


val group = "io.github.seujorgenochurras"
val versionNum = "0.4.1"
repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.14.2")
    testImplementation(platform("org.junit.jupiter:junit-jupiter-api:5.8.1"))
    testRuntimeOnly(platform("org.junit.jupiter:junit-jupiter-engine:5.8.1"))
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
