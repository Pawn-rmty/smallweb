package com.rmty.demo.aspect;

import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.rmty.framework.annotation.Aspect;
import com.rmty.framework.annotation.Controller;
import com.rmty.framework.proxy.AspectProxy;

@Aspect(Controller.class)
public class ControllerAspectAnother extends AspectProxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);

    private long begin;

    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
        LOGGER.debug("--- begin_ ---");
    }

    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable {
        LOGGER.debug("--- end ---");
    }
}

