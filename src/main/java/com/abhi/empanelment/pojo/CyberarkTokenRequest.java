package com.abhi.empanelment.pojo;

public class CyberarkTokenRequest {
	
		public String redirect_uri;
		public String code;
		public String grant_type;
		public String client_id;
		public String client_secret;
		public String state;
		
		public CyberarkTokenRequest(String redirect_uri, String code, String grant_type, String client_id,
				String client_secret, String state) {
			super();
			this.redirect_uri = redirect_uri;
			this.code = code;
			this.grant_type = grant_type;
			this.client_id = client_id;
			this.client_secret = client_secret;
			this.state = state;
		}
}
