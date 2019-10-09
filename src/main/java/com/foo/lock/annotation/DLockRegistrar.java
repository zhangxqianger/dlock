package com.foo.lock.annotation;

import com.foo.lock.config.DLockConfiguration;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AdviceModeImportSelector;
import org.springframework.context.annotation.AutoProxyRegistrar;

/**
 * @author zxq
 */
public class DLockRegistrar extends AdviceModeImportSelector<EnableDLock> {
    @Override
    protected String[] selectImports(AdviceMode adviceMode) {
        return new String[]{
                AutoProxyRegistrar.class.getName(),
                DLockConfiguration.class.getName()
        };
    }
}
