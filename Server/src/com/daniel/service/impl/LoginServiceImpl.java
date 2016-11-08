package com.daniel.service.impl;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.daniel.service.MyService;
import com.daniel.util.HibernateUtil;

public class LoginServiceImpl implements MyService{

	@Override
	public boolean vaild(String snumber, String password) {
		// TODO Auto-generated method stub
		return findUser(snumber, password);
	}

	private boolean findUser(String snumber, String password) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		String hql = "SELECT snumber FROM Student WHERE snumber ='" + snumber 
				+ "' and password ='" + password + "'";
		List list = session.createQuery(hql).list();
		if(list.isEmpty()){
			return false;
		}
		return true;
	}

}
