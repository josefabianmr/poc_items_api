import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
  java
  application
  id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "com.samples"
version = "1.0.0"

repositories {
  mavenCentral()
}

val vertxVersion = "4.3.1"
val junitJupiterVersion = "5.7.0"

val mainVerticleName = "com.samples.infrastructure.rest.vertx.MainVerticle"
val launcherClassName = "io.vertx.core.Launcher"

val watchForChange = "src/**/*"
val doOnChange = "${projectDir}/gradlew classes"

application {
  mainClass.set(launcherClassName)
}

dependencies {

  implementation(platform("io.vertx:vertx-stack-depchain:$vertxVersion"))
  implementation("io.vertx:vertx-core")
  implementation("io.vertx:vertx-web")
  implementation("com.fasterxml.jackson.core:jackson-databind:2.13.3")

  implementation ("org.apache.commons:commons-lang3:3.12.0")
  implementation ("com.google.inject:guice:5.1.0")
  implementation ("io.vavr:vavr:0.10.4")
  implementation("org.slf4j:slf4j-api:1.7.36")

  compileOnly ("org.projectlombok:lombok:1.18.24")
  annotationProcessor ("org.projectlombok:lombok:1.18.24")

  testCompileOnly ("org.projectlombok:lombok:1.18.24")
  testAnnotationProcessor ("org.projectlombok:lombok:1.18.24")
  testImplementation("io.vertx:vertx-junit5")
  testImplementation("org.assertj:assertj-core:3.23.1")
  testImplementation("org.mockito:mockito-junit-jupiter:4.6.1")
  testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
  testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")

}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<ShadowJar> {
  archiveClassifier.set("fat")
  manifest {
    attributes(mapOf("Main-Verticle" to mainVerticleName))
  }
  mergeServiceFiles()
}

tasks.withType<Test> {
  useJUnitPlatform()
  testLogging {
    events = setOf(PASSED, SKIPPED, FAILED)
  }
}

tasks.withType<JavaExec> {
  args = listOf("run", mainVerticleName, "--redeploy=$watchForChange", "--launcher-class=$launcherClassName", "--on-redeploy=$doOnChange")
}
