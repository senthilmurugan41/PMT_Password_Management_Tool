package com.epam.pmt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.stereotype.Repository;

import com.epam.pmt.dao.GroupDAO;
import com.epam.pmt.entity.Account;
import com.epam.pmt.entity.Group;
import com.epam.pmt.exception.UserException;
import com.epam.pmt.util.HibernateUtil;

@Repository
public class GroupJPAImpl implements GroupDAO {

	private EntityManagerFactory factory= HibernateUtil.getEntityManager();

	private EntityManager entityManager= factory.createEntityManager();

	
	@Override
	public Group addGroup(Group group) {
		entityManager.getTransaction().begin();
		entityManager.persist(group);
		entityManager.getTransaction().commit();
		return group;
	}

	@Override
	public Group readGroup(String groupName) throws UserException {
		entityManager.getTransaction().begin();
		List<Group> groups=entityManager
				.createQuery("from Group where groupName='"+groupName+"'")
				.getResultList();
		entityManager.getTransaction().commit();
		if(groups.isEmpty())
			return null;
		return groups.get(0);
	}

	@Override
	public boolean updateGroup(String groupName, String newGroupName) throws UserException {
		Group group=readGroup(groupName);
		if(group==null)
			throw new UserException("Group Name Not Found!!!");
		group.setGroupName(newGroupName);
		for(Account account:group.getAccounts())
		{
			account.setAccountGroup(newGroupName);
		}
		entityManager.getTransaction().begin();
		entityManager.persist(group);
		entityManager.getTransaction().commit();
		return true;
	}

	@Override
	public boolean removeGroup(String groupName) throws UserException {
		Group group=readGroup(groupName);
		entityManager.getTransaction().begin();
	    entityManager.remove(group);
	    entityManager.getTransaction().commit();
		return true;
	}

	@Override
	public List<Group> readGroup() {
		entityManager.getTransaction().begin();
		List<Group> groups= entityManager.createQuery("from Group").getResultList();
		entityManager.getTransaction().commit();
		return groups;
	}

}
