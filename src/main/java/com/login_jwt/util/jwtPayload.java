package com.login_jwt.util;

import org.springframework.stereotype.Component;

@Component
public class jwtPayload {

	
	 private String iss;
	    private String sub;
	    private String aud;
	    private long exp;
	    private String iat;

	    public jwtPayload() {
	    }

	    public jwtPayload(String iss, String sub, String aud, long exp, String iat) {
	        this.iss = iss;
	        this.sub = sub;
	        this.aud = aud;
	        this.exp = exp;
	        this.iat = iat;
	    }

		public String getIss() {
			return iss;
		}

		public void setIss(String iss) {
			this.iss = iss;
		}

		public String getSub() {
			return sub;
		}

		public void setSub(String sub) {
			this.sub = sub;
		}

		public String getAud() {
			return aud;
		}

		public void setAud(String aud) {
			this.aud = aud;
		}

		public long getExp() {
			return exp;
		}

		public void setExp(long exp) {
			this.exp = exp;
		}

		public String getIat() {
			return iat;
		}

		public void setIat(String iat) {
			this.iat = iat;
		}

	
	    
	
	
	
}
