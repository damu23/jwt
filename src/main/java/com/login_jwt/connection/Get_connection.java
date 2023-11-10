package com.login_jwt.connection;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.stereotype.Component;

@Component
public class Get_connection {
		

	Connection connection ;
	
	String url  = "jdbc:mysql://localhost:3306/confirmation_service" ;
	String username = "root" ;
	String password  = "plokij09@A" ;
	
	

	public Connection getconnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection(url,username,password);
			
			System.out.println("MYSQL Connection Established");
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		return connection;
		
	}
	
	
//	Properties props = new Properties();
	
//public Get_connection() {
//		
//		
//		// D:\All Java code\Eclipse - Normal codes\Login_Service_jwt\Login_Service_jwt\src\main\resources\application.properties
//		
//		try {
//		FileInputStream fileinput = new FileInputStream("application.properties");
//		
//		props.load(fileinput);
//	
//		 url  = props.getProperty("spring.datasource.url");
//		 username = props.getProperty("spring.datasource.username");
//		 password = props.getProperty("spring.datasource.password");
//					
//	}
//		catch (Exception e) {
//			
//		}
//
//}

}
