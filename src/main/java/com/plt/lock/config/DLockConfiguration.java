package com.plt.lock.config;

import com.plt.lock.aop.DLockInterceptor;
import com.plt.lock.aop.DLockPointcutAdvisor;
import com.plt.lock.aop.LockAttributeSource;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

/**
 * @author zxq
 */
@Configuration
public class DLockConfiguration {
    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public LockAttributeSource lockAttributeSource() {
        return new LockAttributeSource();
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public DLockPointcutAdvisor dLockPointcutAdvisor() {
        DLockPointcutAdvisor dLockPointcutAdvisor = new DLockPointcutAdvisor();
        dLockPointcutAdvisor.setLockAttributeSource(lockAttributeSource());
        dLockPointcutAdvisor.setAdvice(dLockInterceptor());
        return dLockPointcutAdvisor;
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public DLockInterceptor dLockInterceptor() {
        DLockInterceptor dLockInterceptor = new DLockInterceptor();
        dLockInterceptor.setLockAttributeSource(lockAttributeSource());
        return dLockInterceptor;
    }
}
