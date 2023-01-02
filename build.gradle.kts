import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin(Plugins.KotlinJvm.name) version Plugins.KotlinJvm.version
}

group = Environments.group
version = Environments.version

repositories {
    mavenCentral()
}

dependencies {
    implementation(Libs.Logging.log4jCore)
    implementation(Libs.Logging.log4jApi)
    implementation(Libs.Logging.log4jSlf4j)
    implementation(Libs.Logging.logging)
    implementation(Libs.Kafka.client)
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = Environments.KotlinOption.jvmTarget
}