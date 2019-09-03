package com.plt.lock.aop;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;

/**
 * @author zxq
 */
public class DLockPointcutAdvisor extends AbstractBeanFactoryPointcutAdvisor {
    private LockAttributeSource lockAttributeSource;

    @Override
    public Pointcut getPointcut() {
        return new DLockAttributeSourcePointcut() {
            @Override
            LockAttributeSource getLockAttributeSource() {
                return lockAttributeSource;
            }
        };
    }

    public void setLockAttributeSource(LockAttributeSource lockAttributeSource) {
        this.lockAttributeSource = lockAttributeSource;
    }
}
