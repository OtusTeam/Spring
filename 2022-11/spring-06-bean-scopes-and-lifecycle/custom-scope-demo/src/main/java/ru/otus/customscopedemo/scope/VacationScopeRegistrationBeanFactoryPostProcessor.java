package ru.otus.customscopedemo.scope;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class VacationScopeRegistrationBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    private final VacationScope scope;

    public VacationScopeRegistrationBeanFactoryPostProcessor(VacationScope scope) {
        this.scope = scope;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        beanFactory.registerScope("vacation", scope);
    }
}
