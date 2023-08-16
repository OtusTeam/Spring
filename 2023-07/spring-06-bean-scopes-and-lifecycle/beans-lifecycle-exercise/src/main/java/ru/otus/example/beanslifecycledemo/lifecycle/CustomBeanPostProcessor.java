package ru.otus.example.beanslifecycledemo.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import ru.otus.example.beanslifecycledemo.domain.Phone;

import java.lang.reflect.Field;

public class CustomBeanPostProcessor implements BeanPostProcessor {

    public static final String GREETING_PROPERTY = "greeting";

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
            System.out.println("Шаг #9: BeanPostProcessor.postProcessAfterInitialization\n");
        }

/*
        if (bean.getClass().isAssignableFrom(Phone.class)) {
            updateGreeting(bean);
        }
*/
        return bean;
    }

    private void updateGreeting(Object bean) {
        Class<?> aClass = Phone.class;
        try {
            Field greetingField = aClass.getDeclaredField(GREETING_PROPERTY);

            greetingField.setAccessible(true);
            greetingField.set(bean, "Ай-да в гараж. Стихи читать!");
        } catch (Exception e) {
            throw new InvalidPropertyException(Phone.class, GREETING_PROPERTY,
                    "Bean class does not have expected property", e);
        }
    }
}
