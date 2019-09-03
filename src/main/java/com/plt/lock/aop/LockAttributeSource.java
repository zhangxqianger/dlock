package com.plt.lock.aop;

import com.plt.lock.annotation.DLock;
import org.springframework.core.MethodClassKey;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zxq
 */
public class LockAttributeSource {
    private Map<Object, LockAttribute> cache = new ConcurrentHashMap<>(1024);
    private static final LockAttribute DEFAULT_LOCK_ATTRIBUTE  = new LockAttribute();

    LockAttribute getLockAttribute(Method method, @Nullable Class<?> targetClass) {
        if (method.getDeclaringClass() == Object.class) {
            return null;
        }
        Object cacheKey = getCacheKey(method, targetClass);
        LockAttribute cached = this.cache.get(cacheKey);
        if (cached != null) {
            if (cached == DEFAULT_LOCK_ATTRIBUTE) {
                return null;
            } else {
                return cached;
            }
        } else {
            LockAttribute lockAttribute = computeAttribute(method);
            if (lockAttribute != null) {
                this.cache.put(cacheKey, lockAttribute);
            }  else {
                this.cache.put(cacheKey, DEFAULT_LOCK_ATTRIBUTE);
            }
            return lockAttribute;
        }
    }

    private Object getCacheKey(Method method, @Nullable Class<?> targetClass) {
        return new MethodClassKey(method, targetClass);
    }

    private LockAttribute computeAttribute(Method method) {
        DLock dLock = method.getAnnotation(DLock.class);
        if (dLock == null) {
            return null;
        } else {
            return parseAttribute(dLock);
        }
    }

    private LockAttribute parseAttribute(DLock dLock) {
        LockAttribute lockAttribute = new LockAttribute();
        Assert.hasText(dLock.key(), "lock key must not empty");
        lockAttribute.setKey(dLock.key());
        String expire = dLock.expire();
        Duration parse = Duration.parse(expire.toUpperCase());
        lockAttribute.setExpire(parse);
        return lockAttribute;
    }
}
