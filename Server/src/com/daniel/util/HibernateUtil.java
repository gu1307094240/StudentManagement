package com.daniel.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil
{
	public static final SessionFactory sessionFactory;
	
	static
	{
		try
		{
			Configuration configuration = new Configuration()
			.configure();
			sessionFactory = configuration.buildSessionFactory();
		}
		catch (Throwable ex)
		{
			System.err.println("��ʼ��sessionFactoryʧ��." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static final ThreadLocal<Session> session = new ThreadLocal<Session>();
	
	public static Session currentSession()
			throws HibernateException
	{
		Session s = session.get();
		if (s == null)
		{
			s = sessionFactory.openSession();
			session.set(s);
		}
		return s;
	}
	
	public static void closeSession()
			throws HibernateException 
	{
		Session s = session.get();
		if (s != null)
			s.close();
		session.set(null);
	}
}