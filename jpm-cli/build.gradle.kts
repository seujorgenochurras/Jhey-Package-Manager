plugins {
    id("java")
}

group = "io.github.seujorgenochurras"
version = "unspecified"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation ("de.codeshelf.consoleui:consoleui:0.0.13")
    implementation ("org.reflections:reflections:0.10.2")
    implementation ("ch.qos.logback:logback-classic:1.4.7")
    implementation ("com.google.code.gson:gson:2.10.1")
    implementation ("io.github.seujorgenochurras:java-dependency-manager:0.4.3")
}

tasks.test {
    useJUnitPlatform()
}
