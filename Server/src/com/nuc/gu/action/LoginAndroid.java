 package com.nuc.gu.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.tools.Tool;

import org.apache.struts2.ServletActionContext;

import com.daniel.service.impl.LoginServiceImpl;
import com.opensymphony.xwork2.Action;

public class LoginAndroid implements Action{

	private String username;
	private String password;
	private LoginServiceImpl lsi;
	private HttpServletResponse response;


	@Override
	public String execute() throws IOException{

		response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=utf-8");
        response.setHeader("Cache-Control", "no-cache"); 
        PrintWriter out = response.getWriter();
		if(getLsi().vaild(getUsername(), getPassword())){
			out.print("true");
		}else{
			out.print("false");
		}
		out.flush();
		out.close();
        
		return null;
		
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
