package ru.otus.example.beanslifecycledemo.lifecycle;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LifeCycleConfig {

    @Bean
    public BeanFactoryPostProcessor customBeanFactoryPostProcessor() {
        return new CustomBeanFactoryPostProcessor();
    }

    @Bean
    public BeanPostProcessor customBeanPostProcessor() {
        return new CustomBeanPostProcessor();
    }

    @ConditionalOnProperty(name = "spring.shell.interactive.enabled", havingValue = "false")
    @Bean(initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    public CustomLifeCycleBean customLifeCycleBean() {
        return new CustomLifeCycleBean();
    }
}
