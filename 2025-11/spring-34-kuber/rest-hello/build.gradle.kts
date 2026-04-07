plugins {
    id("java")
    id("org.springframework.boot")
    id("com.google.cloud.tools.jib")
    id("fr.brouillard.oss.gradle.jgitver")
}


apply(plugin = "com.google.cloud.tools.jib")

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
}

tasks {
    jib {
        container {
            creationTime.set("USE_CURRENT_TIMESTAMP")
        }
        from {
            image = "bellsoft/liberica-openjdk-alpine-musl:21.0.1"
        }
        to {
            tags = setOf(project.version.toString())
            image = "registry.gitlab.com/petrelevich/dockerregistry/${project.name}"
            auth {
                username = System.getenv("GITLAB_USERNAME")
                password = System.getenv("GITLAB_PASSWORD")
            }
        }
    }
//docker login registry.gitlab.com
//docker run -p 8080:8080 registry.gitlab.com/petrelevich/dockerregistry/rest-hello

    build {
        dependsOn(jib)
    }
}