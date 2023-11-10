package com.login_jwt.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.login_jwt.util.JWT_util;

@Component
public class JWT_Interceptor implements HandlerInterceptor {
	
	@Autowired
	JWT_util jwtutil ;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
	
		System.out.println("******************************preHandle interceptor went in");
		
		System.out.println("printing the URI :: " + request.getRequestURI());
		String auth = request.getHeader("auth");
		
		System.out.println(auth+ " accesstoken");
		
		jwtutil.verifyJWT(auth);		
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		
		System.out.println("******************************postHandle interceptor method went in");
		
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler, Exception ex)throws Exception {
		
		System.out.println("*************************************after completion interceptor method went in");
		
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	

}
