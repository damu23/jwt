package com.login_jwt.util;

import java.awt.RenderingHints.Key;
import java.util.Date;

import javax.crypto.interfaces.DHKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.login_jwt.model.User_model;
import com.login_jwt.service.LoginService_layer;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JWT_util {


//	
//	@Autowired
//	jwtPayload jwtpayload ;
//	
	public static  String secretKey = "secret_key";

	
	Date date = new Date();
	
	long expiryduration = 15 * 60 ;
	
	long millisecounds = System.currentTimeMillis();
	
	long expirytime = millisecounds + expiryduration *1000 ; 
	 	
	Date expiryat = new Date(expirytime);
	
	String secret = "secretkey for algo ";

	
	
	public String generateJWT(User_model user_login) {
	
		Claims claims = Jwts.claims().setIssuer(user_login.getId()+"").setIssuedAt(date).setExpiration(expiryat);	
		
		claims.put("name", user_login.getName());
		claims.put("email", user_login.getEmail());
				
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512,secret ).compact();
			
		
	}
	
	public String verifyJWT(String JW_TOken){
		
		
		try{
		Jwts.parser().setSigningKey(secret).parseClaimsJws(JW_TOken);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return null ;
		
	}
	
}
