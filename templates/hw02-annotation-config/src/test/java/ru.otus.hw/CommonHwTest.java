package ru.otus.hw;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.hw.config.AppProperties;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class CommonHwTest {

    private static final String CONFIGURATION_ANNOTATION_NAME = "org.springframework.context.annotation.Configuration";

    @DisplayName("")
    @Test
    void shouldNotContainConfigurationAnnotationAboveItSelf() {
        assertThat(AppProperties.class.isAnnotationPresent(Configuration.class))
                .withFailMessage("Класс свойств не является конфигурацией т.к. " +
                        "конфигурация для создания бинов, а тут просто компонент группирующий свойства приложения")
                .isFalse();
    }

    @Test
    void shouldNotContainPropertySourceAnnotationAboveItSelf() {
        assertThat(AppProperties.class.isAnnotationPresent(PropertySource.class))
                .withFailMessage("Аннотацию @PropertySource лучше вешать над конфигурацией, " +
                        "а класс свойств ей не является")
                .isFalse();
    }

    @Test
    void shouldNotContainFieldInjectedDependenciesOrProperties() {
        var provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter((mr, mf) -> {
            var metaData = mr.getClassMetadata();
            var annotationMetaData = mr.getAnnotationMetadata();
            var isTest = metaData.getClassName().endsWith("Test");
            var isInterface = metaData.isInterface();
            var isConfiguration = annotationMetaData.hasAnnotation(CONFIGURATION_ANNOTATION_NAME);
            var clazz = getBeanClassByName(metaData.getClassName());
            var classContainsFieldInjectedDependenciesOrProperties = Arrays.stream(clazz.getDeclaredFields())
                    .anyMatch(f -> f.isAnnotationPresent(Autowired.class) || f.isAnnotationPresent(Value.class));
            return !isTest && !isInterface && !isConfiguration && classContainsFieldInjectedDependenciesOrProperties;
        });

        var classesContainsFieldInjectedDependenciesOrProperties =
                provider.findCandidateComponents(Application.class.getPackageName());

        var classesNames = classesContainsFieldInjectedDependenciesOrProperties.stream()
                .map(BeanDefinition::getBeanClassName).collect(Collectors.joining("%n"));
        assertThat(classesContainsFieldInjectedDependenciesOrProperties)
                .withFailMessage("На курсе все внедрение рекомендовано осуществлять через конструктор (" +
                        "в т.ч. @Value). Следующие классы нарушают это правило: %n%s".formatted(classesNames))
                .isEmpty();
    }

    private Class<?> getBeanClassByName(String beanClassName) {
        try {
            return Class.forName(beanClassName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}