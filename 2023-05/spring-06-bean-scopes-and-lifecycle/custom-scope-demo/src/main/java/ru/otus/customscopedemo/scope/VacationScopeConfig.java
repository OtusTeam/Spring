package ru.otus.customscopedemo.scope;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import ru.otus.customscopedemo.vacation.VacationCalendar;

@Configuration
public class VacationScopeConfig {

    @Bean(destroyMethod = "onDestroy")
    public VacationScope vacationScope(VacationCalendar vacationCalendar){
        return new VacationScope(vacationCalendar);
    }

    @DependsOn("vacationScope")
    @Bean
    public BeanFactoryPostProcessor vacationScopeRegistrationBeanFactoryPostProcessor(VacationScope vacationScope) {
        return new VacationScopeRegistrationBeanFactoryPostProcessor(vacationScope);
    }
}
