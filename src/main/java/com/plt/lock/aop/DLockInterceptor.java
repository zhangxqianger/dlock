package com.plt.lock.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.Assert;

/**
 * @author zxq
 */
public class DLockInterceptor implements MethodInterceptor, BeanFactoryAware {
    private BeanFactory beanFactory;
    private LockAttributeSource lockAttributeSource;
    private SpelExpressionParser parserSpel = new SpelExpressionParser();
    private DefaultParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        this.stringRedisTemplate = beanFactory.getBean(StringRedisTemplate.class);
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        LockAttribute lockAttribute = lockAttributeSource.getLockAttribute(invocation.getMethod(), invocation.getClass());
        if (lockAttribute == null) {
            return invocation.proceed();
        }

        String[] paramNames = parameterNameDiscoverer.getParameterNames(invocation.getMethod());
        if (paramNames != null && paramNames.length > 0) {
            Expression expression = parserSpel.parseExpression(lockAttribute.getKey());
            EvaluationContext context = new StandardEvaluationContext();
            Object[] arguments = invocation.getArguments();
            for (int i = 0; i < paramNames.length; i++) {
                context.setVariable(paramNames[i], arguments[i]);
            }
            Object value = expression.getValue(context);
            Assert.notNull(value, "表达式错误");
            ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
            Boolean locked = ops.setIfAbsent(
                    invocation.getThis().getClass().getName() + ":" +
                            invocation.getMethod().getName() + ":" +
                            value,
                    "1",
                    lockAttribute.getExpire());
            if (locked == Boolean.FALSE) {
                throw new IllegalAccessException("接口重复调用");
            }
        }
        return invocation.proceed();
    }

    public void setLockAttributeSource(LockAttributeSource lockAttributeSource) {
        this.lockAttributeSource = lockAttributeSource;
    }

}
