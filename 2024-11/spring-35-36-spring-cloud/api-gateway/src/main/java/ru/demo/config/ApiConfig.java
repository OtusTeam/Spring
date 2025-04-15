package ru.demo.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.demo.controller.XrequestFilter;

@Configuration
@EnableConfigurationProperties(ApplConfigProperties.class)
@EnableDiscoveryClient
public class ApiConfig {

    @Bean
    public XrequestFilter xrequestFilter() {
        return new XrequestFilter();
    }

    @Bean
    RouteLocator gateway(
            RouteLocatorBuilder rlb, ApplConfigProperties applConfigProperties, XrequestFilter xrequestFilter) {
        var routesBuilder = rlb.routes();
        for (var route : applConfigProperties.getApiRoutes()) {
            routesBuilder.route(route.id(), routeSpec -> routeSpec
                    .path(String.format("/%s/**", route.from()))
                    .filters(fs -> fs.filters(xrequestFilter)
                            .rewritePath(String.format("/%s/(?<segment>.*)", route.from()), "/${segment}"))
                    .uri(String.format("%s/@", route.to())));
        }
        return routesBuilder.build();
    }
}
