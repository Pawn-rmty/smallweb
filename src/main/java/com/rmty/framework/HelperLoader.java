package com.rmty.framework;

import com.rmty.framework.helper.*;
import com.rmty.framework.util.ClassUtil;


public final class HelperLoader {

    public static void init() {
        Class<?>[] classList = {
                //加载顺序注意

                ClassHelper.class,
                BeanHelper.class,
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class

        };
        for (Class<?> cls : classList) {

            ClassUtil.loadClass(cls.getName());

        }
    }
}