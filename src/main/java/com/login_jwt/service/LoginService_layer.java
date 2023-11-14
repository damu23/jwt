package com.login_jwt.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.login_jwt.DAO.User_dao;
import com.login_jwt.common.APIResponse;
import com.login_jwt.model.Login_user;
import com.login_jwt.model.RefreshToken;
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
			
			BCryptPasswordEncoder encryptpassword = new BCryptPasswordEncoder();
		         
			String encryptedpassword = encryptpassword.encode(usermodel.getPassword());			
			
			usermodel.setPassword(encryptedpassword);
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

		
		User_dao u_dao = new User_dao();
		
		User_model user_login_dao =  u_dao.login(login_json); 
		
		System.out.println("in service layer " + user_login_dao.getName() + " ::: is the  name from DAO");
		
		////////////////////
		
		BCryptPasswordEncoder decrypt_password = new BCryptPasswordEncoder();			
		
		
		   if(user_login_dao.getName() == null){
			   apiresponse.setStatus(HttpStatus.BAD_REQUEST.value());
			   apiresponse.setData("user authentication failed");
		   }
		
		   else if(user_login_dao.getName().equalsIgnoreCase(login_json.getName()) &&
				   //decrypting password 
				   decrypt_password.matches(login_json.getPassword(), user_login_dao.getPassword()) ) {
			   			   
               // generate JWT			   
			   String token  = jwtutil.generateJWT(user_login_dao);	
			   
			   //new generated token insertion for the user  
			   user_dao.insertGenerated_tokenforUser_dao(user_login_dao.getId(),token);
			   
			   // inserting  data as a HashMap
			   Map<String,Object> token_HM = new HashMap<>();
			   
			   token_HM.put("accesstoken" , token);
			   
			   apiresponse.setData(token_HM);   
		   }
		 
		return apiresponse  ;
		
	}
	
	
	public RefreshToken refreshtoken(String username){
	
//		RefreshTOken.builder().userinfo(user_dao.for_refreshing_token(username)).token
		
		
		return null ;
	}
	
	
}
