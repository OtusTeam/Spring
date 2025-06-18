package ru.demo.config;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "application")
public class ApplConfigProperties {
    private final String authHost;
    private final List<ApiRoute> apiRoutes;

    @ConstructorBinding
    public ApplConfigProperties(String authHost, List<ApiRoute> apiRoutes) {
        this.authHost = authHost;
        this.apiRoutes = apiRoutes;
    }

    public String getAuthHost() {
        return authHost;
    }

    public List<ApiRoute> getApiRoutes() {
        return apiRoutes;
    }

    @Override
    public String toString() {
        return "ApplConfigProperties{" + "authHost='" + authHost + '\'' + ", apiRoutes=" + apiRoutes + '}';
    }
}
