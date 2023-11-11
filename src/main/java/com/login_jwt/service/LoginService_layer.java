package com.login_jwt.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.login_jwt.DAO.User_dao;
import com.login_jwt.common.APIResponse;
import com.login_jwt.model.Login_user;
import com.login_jwt.model.User_model;
import com.login_jwt.util.JWT_util;



@Service
public class LoginService_layer {

	public LoginService_layer() {
		super();
	}

	@Autowired
	APIResponse apiresponse ;
		
	User_model usermodel ;
	
	@Autowired
	JWT_util jwtutil;
	
	@Autowired
	User_dao user_dao ;
	
	
	public APIResponse signup(User_model usermodel) {

		
		if(Objects.nonNull(usermodel.getName()) && !"".equalsIgnoreCase(usermodel.getName())) {
			usermodel.setName(usermodel.getName());
			System.out.println(usermodel.getName());
			}
		

		if(Objects.nonNull(usermodel.getEmail())&& !"".equalsIgnoreCase(usermodel.getEmail())){
			usermodel.setEmail(usermodel.getEmail());
		}
		
		if(Objects.nonNull(usermodel.getPhone())){
			usermodel.setPhone(usermodel.getPhone());
		}
		
		if(Objects.nonNull(usermodel.getPassword())&& !"".equalsIgnoreCase(usermodel.getPassword())){
			usermodel.setPassword(usermodel.getPassword());
		}
		
		 LocalDateTime currentDateTime = LocalDateTime.now();
	        
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        
	        String currentDateTimeAsString = currentDateTime.format(formatter);

	        usermodel.setCreatedAt(currentDateTimeAsString);
        
	        User_model usm =       user_dao.signup_dao(usermodel);

		apiresponse.setData(usm);
		   
          return apiresponse;
		
	}


	
	public APIResponse login(Login_user login_json) {
	
		System.out.println("username : "+ login_json.getName());

		System.out.println("password" + login_json.getPassword());
		
		User_model user_login_dao =  user_dao.login(login_json);
		
		
		   if(user_login_dao.getName() == null){
			   apiresponse.setStatus(HttpStatus.BAD_REQUEST.value());
			   apiresponse.setData("user authentication failed");
		   }
		
		   else if(user_login_dao.getName().equalsIgnoreCase(login_json.getName()) && 
				   user_login_dao.getPassword().equalsIgnoreCase(login_json.getPassword())) {
			   
               // generate JWT			   
			   String token  = jwtutil.generateJWT(user_login_dao);	
			   
			   Map<String,Object> token_HM = new HashMap<>();
			   
			   token_HM.put("accesstoken" , token);
			   
			   apiresponse.setData(token_HM);   
		   }
		 
		return apiresponse  ;
		
	}
	
}
