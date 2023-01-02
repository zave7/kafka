/** Versions **/
object Versions {
    const val kotlinVersion = "1.7.21"
}

object Plugins {
    object KotlinJvm {
        const val name      = "jvm"
        const val version   = Versions.kotlinVersion
    }
}

/** Project Environments **/
object Environments {

    const val group     = "com.kafka"
    const val version   = "0.0.1-SNAPSHOT"

    object KotlinOption {
        const val jvmTarget     = "1.8"
    }

}

/** Dependencies **/
object Libs {

    object Logging {
        private const val log4jVersion = "2.19.0"
        private const val kotlinLoggingVersion = "1.12.5"
        const val logging       = "io.github.microutils:kotlin-logging:$kotlinLoggingVersion"
        const val log4jCore     = "org.apache.logging.log4j:log4j-core:$log4jVersion"
        const val log4jApi      = "org.apache.logging.log4j:log4j-api:$log4jVersion"
        const val log4jSlf4j    = "org.apache.logging.log4j:log4j-slf4j-impl:$log4jVersion"
    }

    object Kafka {
        private const val version = "2.5.0"
        const val client = "org.apache.kafka:kafka-clients:$version"
    }

}