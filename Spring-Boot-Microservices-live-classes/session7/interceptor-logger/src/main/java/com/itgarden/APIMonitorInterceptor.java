package com.itgarden;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Component
public class APIMonitorInterceptor implements HandlerInterceptor {

    /*
     * This method used to intercept the request before sending request to the controller
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
//        String endPoint = request.getServletContext().getContextPath();
//        String method = request.getMethod();
//        System.out.println("The Rest endpoint : " + endPoint + " starts at "
//                + startTime + " Method Name " + method);

        System.out.println("");
        return true;
    }

    /*
     * This method is called after request processing completed by the controller
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView
            modelAndView) throws Exception {

    }

    /*
     * This method called after rendering the view in client side.
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception
            ex) throws Exception {
        try {
            long endTime = System.currentTimeMillis();
            long startTime = (Long) request.getAttribute("startTime");
            long timeDuration = endTime - startTime;

            System.out.println("[" + handler + " ] Ended processing and took time : " + timeDuration + "ms");
//            String endPoint = request.getServletContext().getContextPath();
//            String method = request.getMethod();
//            System.out.println("The Rest endpoint : " + endPoint + " ends at "
//                    + endTime + " Method Name " + method);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
