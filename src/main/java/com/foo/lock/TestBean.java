package com.foo.lock;

import com.foo.lock.annotation.DLock;
import org.springframework.stereotype.Component;

/**
 * @author zxq
 */
@Component
public class TestBean {
    @DLock(key = "#foo.id")
    public void testMethod(Foo foo) {
        System.out.println(foo);
    }
}
