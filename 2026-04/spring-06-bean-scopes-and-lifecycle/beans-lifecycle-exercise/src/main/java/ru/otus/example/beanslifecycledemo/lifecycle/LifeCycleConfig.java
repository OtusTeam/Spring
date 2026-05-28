package ru.otus.example.beanslifecycledemo.lifecycle;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(CustomBeanDefinitionRegistrar.class)
@Configuration
public class LifeCycleConfig {
/*
    @Bean
    public BeanFactoryPostProcessor customBeanFactoryPostProcessor() {
        return new CustomBeanFactoryPostProcessor();
    }

    @Bean
    public BeanPostProcessor customBeanPostProcessor() {
        return new CustomBeanPostProcessor();
    }

    @ConditionalOnProperty(name = "lifecycle.print.enabled", havingValue = "true")
    @Bean(initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    public CustomLifeCycleBean customLifeCycleBean() {
        return new CustomLifeCycleBean();
    }
*/
}
