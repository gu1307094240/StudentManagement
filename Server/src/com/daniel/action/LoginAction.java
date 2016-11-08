package com.daniel.action;

import com.daniel.service.impl.LoginServiceImpl;
import com.opensymphony.xwork2.Action;

public class LoginAction implements Action{

	private String username;
	private String password;
	private LoginServiceImpl lsi;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if(getLsi().vaild(getUsername(), getPassword())){
			return "success";
		}
		return "fail";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginServiceImpl getLsi() {
		return lsi;
	}

	public void setLsi(LoginServiceImpl lsi) {
		this.lsi = lsi;
	}

}
