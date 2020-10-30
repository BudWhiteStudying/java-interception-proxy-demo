package com.budwhite.studying.intercept.execution.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.Duration;
import java.time.Instant;

public class BeforeAndAfterLoggingProxy implements InvocationHandler {

    private final Object originalObject;

    private BeforeAndAfterLoggingProxy(Object originalObject) {
        this.originalObject = originalObject;
    }

    public static Object newInstance(Object originalObject) {
        return Proxy.newProxyInstance(
                originalObject.getClass().getClassLoader(),
                originalObject.getClass().getInterfaces(),
                new BeforeAndAfterLoggingProxy(originalObject)
        );
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Instant initialInstant = Instant.now();
        System.out.println("Intercepted execution at "+ initialInstant);
        Object result = method.invoke(originalObject, args);
        System.out.println("Intercepted end of execution, elapsed = "+ Duration.between(initialInstant, Instant.now()));
        return result;
    }
}
