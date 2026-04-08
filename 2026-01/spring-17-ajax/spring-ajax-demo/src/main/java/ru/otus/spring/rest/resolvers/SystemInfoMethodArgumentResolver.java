package ru.otus.spring.rest.resolvers;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import ru.otus.spring.domain.SystemInfo;
import ru.otus.spring.service.SystemInfoService;

@Component
public class SystemInfoMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private final SystemInfoService systemInfoService;

    public SystemInfoMethodArgumentResolver(SystemInfoService systemInfoService) {
        this.systemInfoService = systemInfoService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(SystemInfo.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        return systemInfoService.getSystemInfo();
    }
}
