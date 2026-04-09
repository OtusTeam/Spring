dependencies {
    implementation("ch.qos.logback:logback-classic")
    implementation ("org.apache.kafka:kafka-clients")
}

tasks {
    bootJar {
        enabled = false
    }
}
