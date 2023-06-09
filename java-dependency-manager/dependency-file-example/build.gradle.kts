plugins {
    id("java-library")
    id("maven-publish")
    id("signing")
}

build.gradle
val group = "io.github.seujorgenochurras"
val versionNum = "0.3.1"
repositories {
    mavenCentral()
}

dependencies{
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.14.2")
    testImplementation  ("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}
