package com.login_jwt.config;

import java.rmi.registry.Registry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webConfiguration  implements WebMvcConfigurer{

	@Autowired
	JWT_Interceptor jwtinterceptor ;
	
	
	   @Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(jwtinterceptor) ;  
		   
		
	}
	
	
	
}
