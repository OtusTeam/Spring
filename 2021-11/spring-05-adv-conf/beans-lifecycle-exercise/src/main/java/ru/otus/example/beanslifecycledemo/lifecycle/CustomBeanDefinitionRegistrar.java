package ru.otus.example.beanslifecycledemo.lifecycle;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Optional;

public class CustomBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {
    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry,
                                        BeanNameGenerator importBeanNameGenerator) {
        registerBeanDefinitions(importingClassMetadata, registry);
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata,
                                        BeanDefinitionRegistry registry) {

        registerBeanFactoryPostProcessor(registry);
        registerBeanPostProcessor(registry);

        if (Optional.ofNullable(environment.getProperty("lifecycle.print.enabled", Boolean.class)).orElse(false)) {
            System.out.println("Шаг #0: ImportBeanDefinitionRegistrar.registerBeanDefinitions\n");
            registerCustomLifeCycleBean(registry);
        }

    }

    private void registerCustomLifeCycleBean(BeanDefinitionRegistry registry) {
        GenericBeanDefinition gbd = new GenericBeanDefinition();
        gbd.setBeanClass(CustomLifeCycleBean.class);
        registry.registerBeanDefinition("customLifeCycleBean", gbd);
    }

    private void registerBeanFactoryPostProcessor(BeanDefinitionRegistry registry) {
        GenericBeanDefinition gbd = new GenericBeanDefinition();
        gbd.setBeanClass(CustomBeanFactoryPostProcessor.class);
        registry.registerBeanDefinition("customBeanFactoryPostProcessor", gbd);
    }

    private void registerBeanPostProcessor(BeanDefinitionRegistry registry) {
        GenericBeanDefinition gbd = new GenericBeanDefinition();
        gbd.setBeanClass(CustomBeanPostProcessor.class);
        registry.registerBeanDefinition("customBeanPostProcessor", gbd);
    }

}
