// Bean that demonstrates the complete lifecycle
package com.example.beanlifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LifeCycleBean implements BeanNameAware, BeanFactoryAware,
        ApplicationContextAware, InitializingBean, DisposableBean {

    private String beanName;
    private BeanFactory beanFactory;
    private ApplicationContext applicationContext;
    private String customProperty;

    // Constructor
    public LifeCycleBean() {
        log.info("1. LifeCycleBean Constructor called - Bean instantiation");
    }

    // Setter for property injection demonstration
    public void setCustomProperty(String customProperty) {
        this.customProperty = customProperty;
        log.info("2. Property injection - setCustomProperty() called with value: {}", customProperty);
    }

    // BeanNameAware interface method
    @Override
    public void setBeanName(String name) {
        this.beanName = name;
        log.info("3. BeanNameAware.setBeanName() called with name: {}", name);
    }

    // BeanFactoryAware interface method
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        log.info("4. BeanFactoryAware.setBeanFactory() called");
    }

    // ApplicationContextAware interface method
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        log.info("5. ApplicationContextAware.setApplicationContext() called");
    }

    // @PostConstruct annotation method
    @PostConstruct
    public void postConstruct() {
        log.info("7. @PostConstruct method called - postConstruct()");
    }

    // InitializingBean interface method
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("8. InitializingBean.afterPropertiesSet() called");
    }

    // Custom init method (configured in @Bean annotation)
    public void customInit() {
        log.info("9. Custom init method called - customInit()");
    }

    // Business method to demonstrate bean is ready for use
    public void doSomething() {
        log.info("11. Bean is ready for use - doSomething() business method called");
        log.info("    Bean name: {}", beanName);
        log.info("    Custom property: {}", customProperty);
        log.info("    BeanFactory available: {}", beanFactory != null);
        log.info("    ApplicationContext available: {}", applicationContext != null);
    }

    // @PreDestroy annotation method
    @PreDestroy
    public void preDestroy() {
        log.info("12. @PreDestroy method called - preDestroy()");
    }

    // DisposableBean interface method
    @Override
    public void destroy() throws Exception {
        log.info("13. DisposableBean.destroy() called");
    }

    public void customDestroy() {
        log.info("14. Custom destroy method called - customDestroy()");
    }
}
