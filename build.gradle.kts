plugins {
    id("net.fabricmc.fabric-loom") version "1.17-SNAPSHOT"
    id("org.jetbrains.kotlin.jvm") version "2.3.21"
    id("maven-publish")
}

group = providers.gradleProperty("group").get()
version = providers.gradleProperty("version").get()

base {
    archivesName.set("pig-vision")
}

loom {
    splitEnvironmentSourceSets()

    mods {
        create("pigvision") {
            sourceSet(sourceSets["main"])
            sourceSet(sourceSets["client"])
        }
    }
}

dependencies {
    minecraft(
        "com.mojang:minecraft:${providers.gradleProperty("minecraft_version").get()}"
    )

    implementation(
        "net.fabricmc:fabric-loader:${providers.gradleProperty("loader_version").get()}"
    )

    implementation(
        "net.fabricmc.fabric-api:fabric-api:" +
                providers.gradleProperty("fabric_api_version").get()
    )

    implementation(
        "net.fabricmc:fabric-language-kotlin:" +
                providers.gradleProperty("fabric_language_kotlin_version").get()
    )
}

tasks.processResources {
    inputs.property("version", project.version)

    filesMatching("fabric.mod.json") {
        expand("version" to project.version)
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(25)
}

kotlin {
    jvmToolchain(25)

    compilerOptions {
        jvmTarget.set(
            org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_25
        )
    }
}

java {
    withSourcesJar()

    sourceCompatibility = JavaVersion.VERSION_25
    targetCompatibility = JavaVersion.VERSION_25
}

tasks.jar {
    inputs.property("projectName", project.name)

    from("LICENSE") {
        rename { "${it}_${project.name}" }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}