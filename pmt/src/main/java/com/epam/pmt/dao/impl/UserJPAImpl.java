package com.epam.pmt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.stereotype.Repository;

import com.epam.pmt.dao.UserDAO;
import com.epam.pmt.entity.User;
import com.epam.pmt.exception.UserException;
import com.epam.pmt.util.HibernateUtil;

@Repository
public class UserJPAImpl implements UserDAO {
	private EntityManagerFactory factory= HibernateUtil.getEntityManager();

	private EntityManager entityManager= factory.createEntityManager();

	@Override
	public boolean addUser(User user) {
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		return true;
		
	}

	@Override
	public User getUser(String password) throws UserException {
		entityManager.getTransaction().begin();
		List<User> users=entityManager.createQuery("from User where password='"+password+"'").getResultList();
		entityManager.getTransaction().commit();
		if(users.isEmpty())
			throw new UserException("Invalid Credential");
		return users.get(0);
	}

	@Override
	public List<User> getUser() {
		entityManager.getTransaction().begin();
		List<User> users= entityManager.createQuery("from User").getResultList();
		entityManager.getTransaction().commit();
		return users;
	}
    
	
}
