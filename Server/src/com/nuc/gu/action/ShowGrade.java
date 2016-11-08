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
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.daniel.util.ResultUtils;
import com.daniel.util.HibernateUtil;
import com.opensymphony.xwork2.Action;

public class ShowGrade implements Action{

	private String snumber;
	private HttpServletResponse response;

	@Override
	public String execute() throws IOException{

		response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=utf-8");
        response.setHeader("Cache-Control", "no-cache"); 
        PrintWriter out = response.getWriter();
        Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		String hql = "FROM Achievement WHERE snumber='" + snumber + "' ";// 
		List list = session.createQuery(hql).list();
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			map.put("grade", list);
			ResultUtils.toJson(ServletActionContext.getResponse(), map);
		}catch (IOException e) {
			e.printStackTrace();
		}
        
		return null;
		
	}

	public String getSnumber() {
		return snumber;
	}

	public void setSnumber(String snumber) {
		this.snumber = snumber;
	}

}
