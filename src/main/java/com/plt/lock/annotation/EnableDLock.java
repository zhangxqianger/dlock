package com.plt.lock.annotation;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.lang.annotation.*;

/**
 * @author zxq
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(DLockRegistrar.class)
public @interface EnableDLock {
    AdviceMode mode() default AdviceMode.PROXY;

    boolean proxyTargetClass() default false;
}
