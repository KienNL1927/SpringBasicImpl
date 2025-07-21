package com.example.beanlifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class BeanConfiguration {
    @Bean(initMethod = "init", destroyMethod = "cleanup")
    public LifeCycleBean lifeCycleBean(){
        log.info("Creating AnotherLifeCycleBean via @Bean method");
        LifeCycleBean lifeCycleBean = new LifeCycleBean();
        lifeCycleBean.setCustomProperty("Configured via @Bean method");
        return lifeCycleBean;
    }

}
