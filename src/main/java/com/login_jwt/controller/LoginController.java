package com.login_jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.login_jwt.common.APIResponse;
import com.login_jwt.model.Login_user;
import com.login_jwt.model.User_model;
import com.login_jwt.service.LoginService_layer;
import com.login_jwt.util.JWT_util;


@RestController
public class LoginController {

	@Autowired
	LoginService_layer loginservice_layer ;
	
	
	User_model usermodel ; 
	
	@Autowired
	Login_user loginuser;
	
	@Autowired
	JWT_util jwtutil ;
	
	@PostMapping("/signup")	
	public ResponseEntity<APIResponse> signup(@RequestBody User_model usermodel) {
		
		System.out.println("*******************hitted the signup api controller***********************");
	
		APIResponse apiresponse = loginservice_layer.signup(usermodel);
		
     	return ResponseEntity.status(apiresponse.getStatus()).body(apiresponse);
		
	}
	
	
	@PostMapping("/login")
	public  ResponseEntity<APIResponse> login(@RequestBody Login_user login  ){
		
		 APIResponse apiresponse = loginservice_layer.login(login);
		 
		 return ResponseEntity.status(apiresponse.getStatus()).body(apiresponse);
	}
	
	@GetMapping("/validatetoken")
	public ResponseEntity<APIResponse> validateToken(@RequestHeader(value = "auth" ) String token ){
	
		APIResponse apiresponse = new APIResponse();
		
	try{
		jwtutil.verifyJWT(token);
	}
	catch(Exception e ){
		e.printStackTrace();
		
	}
	System.out.println(token );
		
		apiresponse.setStatus(HttpStatus.OK.value());
		apiresponse.setData("Token validated good to go ");
		
		return ResponseEntity.status(apiresponse.getStatus()).body(apiresponse);
		
		
	}
	
}
	
	