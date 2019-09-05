package ru.otus.example.beanslifecycledemo.lifecycle;

import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import ru.otus.example.beanslifecycledemo.domain.Phone;

import java.lang.reflect.Field;

public class CustomBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().equals(CustomLifeCycleBean.class)) {
            System.out.println("Шаг #5: BeanPostProcessor.postProcessBeforeInitialization\n");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().equals(CustomLifeCycleBean.class)) {
            System.out.println("Шаг #8: BeanPostProcessor.postProcessAfterInitialization\n");
        }

        if (bean.getClass().isAssignableFrom(Phone.class)) {
            updateGreeting(bean);
        }
        return bean;
    }

    @SneakyThrows
    private void updateGreeting(Object bean) {
        Class aClass = Phone.class;
        Field greetingField = aClass.getDeclaredField("greeting");

        greetingField.setAccessible(true);
        greetingField.setAccessible(true);
        greetingField.set(bean, "Ай-да в гараж. Стихи читать!");
    }
}
