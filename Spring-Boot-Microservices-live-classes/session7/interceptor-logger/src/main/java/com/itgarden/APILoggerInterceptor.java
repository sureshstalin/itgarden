package com.itgarden;

import com.itgarden.entity.Customer;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class APILoggerInterceptor implements HandlerInterceptor {

    /*
     * This method used to intercept the request before sending request to the controller
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("This is Pre Handle call back method " + handler.getClass().getName());
        if (handler != null) {

            if (handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                System.out.println("Handle method name  " + handlerMethod.getMethod().getName());
                System.out.println("Bean name " + handlerMethod.getBean().getClass().getName());
                MethodParameter parameters[] = handlerMethod.getMethodParameters();
                for (MethodParameter parameter : parameters) {
                    System.out.println("Method Param name  " + parameter.getParameter().getName());
                }
            }
        }
        return true;
    }

    /*
     * This method is called after request processing completed by the controller
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView
            modelAndView) throws Exception {
        System.out.println("This is Post Handle");

    }

    /*
     * This method called after rendering the view in client side.
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception
            ex) throws Exception {
        System.out.println("This is After Completion Handle call back method " + handler.getClass().getName());
    }
}
