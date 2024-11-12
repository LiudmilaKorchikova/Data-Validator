plugins {
    id("java")
    id("checkstyle")
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

checkstyle {
    toolVersion = "10.12.4"
    configFile = file("config/checkstyle/checkstyle.xml")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}