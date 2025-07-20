package com.example.beanlifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class BeanConfiguration {

    // Alternative way to create a bean with custom init and destroy methods
    @Bean(initMethod = "customInit", destroyMethod = "customDestroy")
    public LifeCycleBean anotherLifeCycleBean() {
        log.info("Creating AnotherLifeCycleBean via @Bean method");
        LifeCycleBean bean = new LifeCycleBean();
        bean.setCustomProperty("Configured via @Bean method");
        return bean;
    }
}
