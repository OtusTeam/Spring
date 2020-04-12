package ru.otus.example.beanslifecycledemo.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class CustomLifeCycleBean implements InitializingBean, DisposableBean, BeanNameAware, BeanFactoryAware, ApplicationContextAware {

    @Override
    public void setBeanName(String s) {
        System.out.println("Шаг #2: BeanNameAware\n");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("Шаг #3: BeanFactoryAware\n");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("Шаг #4: ApplicationContextAware\n");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Шаг #6: @PostConstruct\n");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Шаг #7: InitializingBean.afterPropertiesSet\n");
    }

    public void customInitMethod() {
        System.out.println("Шаг #8: CustomLifeCycleBean.customInitMethod\n");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("Шаг #10: @PreDestroy\n");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Шаг #11: DisposableBean.destroy\n");
    }

    public void customDestroyMethod() {
        System.out.println("Шаг #12: CustomLifeCycleBean.customDestroyMethod\n");
    }
}
