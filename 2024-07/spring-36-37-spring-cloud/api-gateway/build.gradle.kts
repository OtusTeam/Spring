dependencies {
    implementation(project(":kafka-log-appender"))
    implementation("net.logstash.logback:logstash-logback-encoder")
    implementation("ch.qos.logback:logback-classic")

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("io.micrometer:micrometer-registry-prometheus")

    implementation("org.springframework.cloud:spring-cloud-starter-gateway")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

    implementation("io.micrometer:micrometer-tracing-bridge-otel") // bridges the Micrometer Observation API to OpenTelemetry.
    implementation("io.opentelemetry:opentelemetry-exporter-zipkin") // reports traces to Zipkin.
}
