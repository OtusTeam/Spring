dependencies {
    implementation(project(":kafka-log-appender"))
    implementation("net.logstash.logback:logstash-logback-encoder")

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("io.micrometer:micrometer-registry-prometheus")

    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

    implementation("io.micrometer:micrometer-tracing-bridge-otel") // bridges the Micrometer Observation API to OpenTelemetry.
    implementation("io.opentelemetry:opentelemetry-exporter-zipkin") // reports traces to Zipkin.
    implementation("io.github.openfeign:feign-micrometer")

    implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j")
}
