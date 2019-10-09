package com.foo.lock.annotation;

import java.lang.annotation.*;

/**
 * @author zxq
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DLock {
    String key();
    String expire() default "PT30S";
}
