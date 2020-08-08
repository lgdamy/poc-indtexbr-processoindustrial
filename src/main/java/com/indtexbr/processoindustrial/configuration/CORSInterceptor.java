package com.indtexbr.processoindustrial.configuration;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class CORSInterceptor extends HandlerInterceptorAdapter {


    @Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Enumeration<String> reqHeaders = request.getHeaders("Access-Control-Request-Headers");

        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, CONNECT, OPTIONS, TRACE, PATCH");
        response.setHeader("Access-Control-Max-Age", "3600");

        StringBuilder stringBuilder = new StringBuilder("Content-Disposition, Location");
        while (reqHeaders.hasMoreElements()) {
            stringBuilder.append(", ").append(reqHeaders.nextElement());
        }

        response.setHeader("Access-Control-Allow-Headers", stringBuilder.toString());
        response.setHeader("Access-Control-Expose-Headers", stringBuilder.toString());
        response.setHeader("Access-Control-Allow-Credentials", "false");

         return true;
	}

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(200);
        }
    }
}