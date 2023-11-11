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
		
		System.out.println("Requested URI :_:_:  " + request.getRequestURI());
		
		String accesstoken = request.getHeader("accesstoken");
		
	
			if(!request.getRequestURI().equalsIgnoreCase("/login")){
			jwtutil.verifyJWT(accesstoken);
			System.out.println("prehandle token validated");
			
			}
	
		
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		
		
		
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler, Exception ex)throws Exception {
		
	
		
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	

}
