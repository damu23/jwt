package com.login_jwt.common;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class APIResponse {
	
	 private Integer status;
	    private Object data;
	    

	    public APIResponse() {
	        this.status = HttpStatus.OK.value();
	        this.data = data;	
	      
	    }

	    public Integer getStatus() {
	        return status;
	    }

	    public void setStatus(Integer status) {
	        this.status = status;
	    }

	    public Object getData() {
	        return data;
	    }

	    public void setData(Object data) {
	        this.data = data;
	    }

}


