package com.plt.lock;

import com.plt.lock.annotation.DLock;
import com.plt.lock.config.Foo;
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
