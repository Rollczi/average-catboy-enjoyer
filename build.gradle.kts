plugins {
    id("java")
}

group = "dev.rollczi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.panda-lang.org/releases")
}

dependencies {
    implementation("dev.rollczi:litecommands-jda:3.9.0")
    implementation("net.dv8tion:JDA:5.2.1")
    implementation("com.squareup.okhttp:okhttp:2.7.5")
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("net.dzikoysk:cdn:1.14.5")

    testImplementation(platform("org.junit:junit-bom:5.11.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}