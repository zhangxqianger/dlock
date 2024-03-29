package com.foo.lock.annotation;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Import;

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
