package com.rmty.framework.helper;

import java.lang.reflect.Method;
import java.util.*;
import com.rmty.framework.annotation.Action;
import com.rmty.framework.bean.*;

public final class ControllerHelper {

    private static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();

    static {
        Set<Class<?>> controllerClassSet = com.rmty.framework.helper.ClassHelper.getControllerClassSet();
        if (!controllerClassSet.isEmpty()) {
            for (Class<?> controllerClass : controllerClassSet) {
                Method[] methods = controllerClass.getDeclaredMethods();
                if (methods.length!=0) {
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(Action.class)) {
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();
                            String[] arr = mapping.split(":");
                            if ( arr.length == 2) {
                                String requestMethod = arr[0];
                                String requestPath = arr[1];
                                Request request = new Request(requestMethod, requestPath);
                                Handler handler = new Handler(controllerClass, method);
                                ACTION_MAP.put(request, handler);
                            }

                        }
                    }
                }
            }
        }
    }


    public static Handler getHandler(String requestMethod, String requestPath) {
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }
}
