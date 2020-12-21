package br.com.fiap.covidchurch.auth.dtos;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;



public class JwtResponse implements Serializable {



	private static final long serialVersionUID = -8091879091924046844L;

	@SerializedName("token")
	@JsonProperty("token")	
	private String jwttoken;

	@SerializedName("status")
	@JsonProperty("status")	
	private String status;


	public JwtResponse() {}
	public JwtResponse(String jwttoken, String status) {
		this.setJwttoken(jwttoken);
		this.setStatus(status);
	}



	public String getJwttoken() {
		return jwttoken;
	}
	public void setJwttoken(String jwttoken) {
		this.jwttoken = jwttoken;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}




	
}