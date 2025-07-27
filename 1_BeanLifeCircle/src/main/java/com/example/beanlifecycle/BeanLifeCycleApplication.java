// Main Application Class
package com.example.beanlifecycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class BeanLifeCycleApplication {

    public static void main(String[] args) {
        log.info("=== STARTING SPRING APPLICATION ===");

        // Start the Spring application
        ConfigurableApplicationContext context = SpringApplication.run(BeanLifeCycleApplication.class, args);

        log.info("=== APPLICATION STARTED - GETTING BEAN ===");

        LifeCycleBean lifeCycleBean = context.getBean(LifeCycleBean.class);
        lifeCycleBean.doSomething();

        log.info("=== APPLICATION SHUTDOWN COMPLETE ===");
    }
}
