package com.plt.lock.aop;

import java.time.Duration;

/**
 * @author zxq
 */
class LockAttribute {
    private String value;
    private String key;
    private Duration expire;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Duration getExpire() {
        return expire;
    }

    public void setExpire(Duration expire) {
        this.expire = expire;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
