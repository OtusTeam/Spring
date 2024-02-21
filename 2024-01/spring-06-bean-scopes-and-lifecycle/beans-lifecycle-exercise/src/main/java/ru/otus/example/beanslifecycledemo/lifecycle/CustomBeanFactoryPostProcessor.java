package ru.otus.example.beanslifecycledemo.lifecycle;

import org.springframework.beans.BeanMetadataAttribute;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import ru.otus.example.beanslifecycledemo.domain.FriendPhoneNumber;
import ru.otus.example.beanslifecycledemo.domain.GirlfiendPhoneNumber;

public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    private static final String CLASS_NAME_ATTR = "className";

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        if (beanFactory.containsBean("customLifeCycleBean")) {
            System.out.println("Шаг #1: BeanFactoryPostProcessor.postProcessBeanFactory\n");
        }

/*        for (String beanName : beanFactory.getBeanDefinitionNames()) {
            var d = beanFactory.getBeanDefinition(beanName);
            if (d instanceof ScannedGenericBeanDefinition scannedBeanDefinition) {

                if (!GirlfiendPhoneNumber.class.getName().equalsIgnoreCase(d.getBeanClassName())) {
                    continue;
                }
                d.setBeanClassName(FriendPhoneNumber.class.getName());
                var classNameAttr = new BeanMetadataAttribute(CLASS_NAME_ATTR, FriendPhoneNumber.class.getName());
                scannedBeanDefinition.addMetadataAttribute(classNameAttr);
            }
        }*/
    }
}
