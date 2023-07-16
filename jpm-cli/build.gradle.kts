plugins {
    id("java")
}

group = "io.github.seujorgenochurras"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
    implementation ("de.codeshelf.consoleui:consoleui:0.0.13")
    implementation ("com.google.code.gson:gson:2.10.1")
    implementation ("io.github.seujorgenochurras:java-dependency-manager:0.6.0")
}

tasks.test {
    useJUnitPlatform()
}
