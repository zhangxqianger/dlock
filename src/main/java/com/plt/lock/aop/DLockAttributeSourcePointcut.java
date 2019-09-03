package com.plt.lock.aop;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * @author zxq
 */
abstract class DLockAttributeSourcePointcut extends StaticMethodMatcherPointcut {

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return getLockAttributeSource().getLockAttribute(method, targetClass) != null;
    }

    abstract LockAttributeSource getLockAttributeSource();

}
