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
    implementation("dev.rollczi:litecommands-jda:3.3.3")
    implementation("net.dv8tion:JDA:5.0.0-beta.20")
    implementation("com.squareup.okhttp:okhttp:2.7.5")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("net.dzikoysk:cdn:1.14.5")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}