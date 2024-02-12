package com.epam.pmt.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
	
	private static EntityManagerFactory factory;
	
	private HibernateUtil()
	{
		
	}

	public static EntityManagerFactory getEntityManager()
	{
		if(factory==null)
		{
			factory=Persistence.createEntityManagerFactory("PMTJPADB");
		}
		return factory;
	}
}
