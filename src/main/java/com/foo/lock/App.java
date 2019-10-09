package com.foo.lock;

import com.foo.lock.annotation.EnableDLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author zxq
 */
@SpringBootApplication
@EnableDLock
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(App.class);
        TestBean bean = run.getBean(TestBean.class);
        Foo foo = new Foo();
        foo.setId(100);
        foo.setName("im foo");
        bean.testMethod(foo);
    }
}
