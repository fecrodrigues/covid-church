package br.com.fiap.covidchurch.auth.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "credenciais")
public class Credentials {


	@Id
	private String id;
	@Field("userId")
	private String userId;
	@Field("pwd")
	private String pwd;
	@Field("pwdTemp")
	private String pwdTemp;
	@Field("status")
	private String status;
	
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPwdTemp() {
		return pwdTemp;
	}
	public void setPwdTemp(String pwdTemp) {
		this.pwdTemp = pwdTemp;
	}
	
	
	
	
}
