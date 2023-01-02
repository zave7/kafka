/**
 * 의존성 관리와 IDE 자동완성 지원을 위해 kotlin 코드를 가지는 buildSrc 모듈
 * 참조 : https://docs.gradle.org/current/userguide/organizing_gradle_projects.html#sec:build_sources
 *        https://loremipsumpark.gitbooks.io/android-tips/content/project-structure/gradle-managing-dependencies.html
 */

plugins {
    `kotlin-dsl`
//    kotlin("jvm") version "1.8.0"
}

repositories {
    mavenCentral()
}
//dependencies {
//    implementation(kotlin("stdlib-jdk8"))
//}
//val compileKotlin: KotlinCompile by tasks
//compileKotlin.kotlinOptions {
//    jvmTarget = "1.8"
//}
//val compileTestKotlin: KotlinCompile by tasks
//compileTestKotlin.kotlinOptions {
//    jvmTarget = "1.8"
//}