pluginManagement {
    val jgitver: String by settings
    val dependencyManagement: String by settings
    val springframeworkBoot: String by settings
    val sonarlint: String by settings
    val spotless: String by settings
    val jib: String by settings

    plugins {
        id("fr.brouillard.oss.gradle.jgitver") version jgitver
        id("io.spring.dependency-management") version dependencyManagement
        id("org.springframework.boot") version springframeworkBoot
        id("name.remal.sonarlint") version sonarlint
        id("com.diffplug.spotless") version spotless
        id("com.google.cloud.tools.jib") version jib
    }
}
rootProject.name = "spring-cloud"

include("api-gateway")
include("config-server")
include("eureka-server")
include("service-client")
include("service-client-info")
include("service-order")

include("kafka-log-appender")
