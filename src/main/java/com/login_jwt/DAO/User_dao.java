package com.login_jwt.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.login_jwt.connection.Get_connection;
import com.login_jwt.model.Login_user;
import com.login_jwt.model.User_model;

@Component
public class User_dao {

	@Autowired
	Get_connection mysql_connection;
	
	public  User_model signup_dao(User_model user_signup) {
	
		
		String insert_user = "insert into usermodel (id,name,email_id,password,created_at,phone) values (?,?,?,?,?,?)";
				
		try{		
			
			PreparedStatement prst = mysql_connection.getconnection().prepareStatement(insert_user);
			
			prst.setInt(1, user_signup.getId());
			prst.setString(2, user_signup.getName());
			prst.setString(3, user_signup.getEmail());
			prst.setString(4, user_signup.getPassword());
			prst.setString(5, user_signup.getCreatedAt());
			prst.setInt(6, user_signup.getPhone());
			
			
		   int rs = 	prst.executeUpdate();
			
			return user_signup ;
		   
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return null ;
	}
	
	public User_model login(Login_user login_user) {
		
		
		String query = "select * from usermodel where name = ? and password = ?";
		
		User_model user_obj = new User_model();
				
		try {
					
			PreparedStatement prst = mysql_connection.getconnection().prepareStatement(query);
			
			String name = login_user.getName();
						
			String password = login_user.getPassword() ;				
			
			prst.setString(1, name );
			
			prst.setString(2, password);			
			
			ResultSet rs =  prst.executeQuery();
			
			while(rs.next()) {
	
				System.out.println("this is into DAO layer the name after fetching the query is : " + rs.getString(2));
				
				
				   user_obj.setId(rs.getInt(1));
			       user_obj.setName(rs.getString(2));
			       user_obj.setEmail(rs.getString(3));	
			       user_obj.setPassword(rs.getString(4));
			       user_obj.setCreatedAt(rs.getString(5));
			       user_obj.setPhone(rs.getInt(6));
			       
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return user_obj ; 
		
	}
	}