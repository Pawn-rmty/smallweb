package com.rmty.framework;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rmty.framework.bean.Handler;
import com.rmty.framework.bean.Param;
import com.rmty.framework.bean.View;
import com.rmty.framework.helper.BeanHelper;
import com.rmty.framework.helper.ConfigHelper;
import com.rmty.framework.helper.ControllerHelper;
import com.rmty.framework.util.*;


@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        HelperLoader.init();
        //注册 https://www.cnblogs.com/juncaoit/p/7368171.html
        ServletContext servletContext = servletConfig.getServletContext();
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
    }



    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestMethod = request.getMethod().toLowerCase();
        String requestPath = request.getPathInfo();
        Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
        if (handler != null) {
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);

            //para todo
            Map<String,Object> paramap = new HashMap<String, Object>();
            Param param = new Param(paramap);
            Method actionMethod = handler.getActionMethod();
            Object result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
            handleViewResult((View) result, request, response);

        }

    }

    private void handleViewResult(View view, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = view.getPath();
        if (StringUtil.isNotEmpty(path)) {
            Map<String, Object> model = view.getModel();
            for (Map.Entry<String, Object> entry : model.entrySet()) {
                request.setAttribute(entry.getKey(), entry.getValue());
            }
            request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(request, response);
        }
    }
}
