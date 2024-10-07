package ru.otus.testingdemo.config;

import feign.InvocationContext;
import feign.ResponseInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.testingdemo.converter.WindDirectionConverter;
import ru.otus.testingdemo.dto.WeatherProjection;

@RequiredArgsConstructor
@Component
public class WindDirectionResponseInterceptor implements ResponseInterceptor {

    private final WindDirectionConverter windDirectionConverter;

    @Override
    public Object intercept(InvocationContext invocationContext, Chain chain) throws Exception {
        var result = chain.next(invocationContext);
        if (result instanceof WeatherProjection projection) {
            var windDirection = windDirectionConverter.convert(projection.getWindDegree());
            projection.setWindDirection(windDirection);
        }
        return result;
    }
}
