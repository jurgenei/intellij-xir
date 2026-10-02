import org.jetbrains.intellij.tasks.BuildSearchableOptionsTask
import org.jetbrains.intellij.tasks.PatchPluginXmlTask

plugins {
  java
  id("org.jetbrains.intellij") version "1.17.4"
}

group = "com.jurgenei"
version = "0.1.11"

repositories {
  mavenCentral()
}

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(17))
  }
}

dependencies {
  implementation(files("lib/jparsec-3.1-SNAPSHOT.jar"))
}

sourceSets {
  named("main") {
    java.setSrcDirs(listOf("src"))
    resources.setSrcDirs(listOf("resources", "xir-src"))
  }
}

intellij {
  type.set("IU")
  version.set("2024.1")
}

tasks.withType<JavaCompile>().configureEach {
  options.encoding = "UTF-8"
  sourceCompatibility = "17"
  targetCompatibility = "17"
}

tasks.named<ProcessResources>("processResources") {
  from("META-INF") {
    include("plugin.xml")
    into("META-INF")
  }
}

tasks.named<PatchPluginXmlTask>("patchPluginXml") {
  sinceBuild.set("241")
  untilBuild.set("241.*")
}

tasks.withType<BuildSearchableOptionsTask>().configureEach {
  enabled = false
}
