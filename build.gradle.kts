plugins {
    kotlin("jvm") version "1.9.22"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "world.avionik"
version = "1.0.1"

repositories {
    mavenCentral()
    mavenLocal()

    maven("https://oss.sonatype.org/content/repositories/central")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    api("org.jetbrains.kotlin:kotlin-stdlib:1.9.22")
    api(kotlin("reflect"))
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    compileOnly("org.spigotmc:spigot-api:1.20.2-R0.1-SNAPSHOT")
}