package com.epam.pmt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.stereotype.Repository;

import com.epam.pmt.dao.AccountDAO;
import com.epam.pmt.entity.Account;
import com.epam.pmt.exception.UserException;
import com.epam.pmt.util.HibernateUtil;

@NamedQueries(
@NamedQuery(name="findAccountByName",
			query="select acc from Account acc where acc.accountName=:accountName")
)
@Repository
public class AccountJPAImpl implements AccountDAO {

	private EntityManagerFactory factory= HibernateUtil.getEntityManager();

	private EntityManager entityManager=factory.createEntityManager();

	@Override
	public Account addAccount(Account account) {
		entityManager.getTransaction().begin();
		entityManager.persist(account);
		entityManager.getTransaction().commit();
		return account;
	}

	@Override
	public boolean removeAccount(String accountName) throws UserException {
		boolean isDeleted = false;
		try {
			entityManager.getTransaction().begin();
			Account account = entityManager
					.createQuery("from Account where accountName='"+accountName+"'", Account.class)
					.getSingleResult();
			entityManager.remove(account);
			entityManager.getTransaction().commit();
			isDeleted = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDeleted;
	}

	@Override
	public boolean removeAccountByGroup(String groupName) throws UserException {
		boolean isDeleted=true;
		entityManager.getTransaction().begin();
		List<Account> accounts=entityManager.createQuery("from Account where accountGroup='"+groupName+"'").getResultList();
		if(accounts.isEmpty())
		{
			throw new UserException("No Account Found!!!");
		}
		for(Account account:accounts)
		{
			entityManager.remove(account);
		}
		entityManager.getTransaction().commit();
		return isDeleted;
	}

	@Override
	public List<Account> getAccount() {
		entityManager.getTransaction().begin();
		List<Account> accounts= entityManager.createQuery("from Account").getResultList();
		entityManager.getTransaction().commit();
		return accounts;
	}

	@Override
	public Account updateAccount(String accountName, String password) throws UserException {
		Account account=null;
		try {
		entityManager.getTransaction().begin();
		account = entityManager
				.createQuery("from Account where accountName='" + accountName + "'", Account.class)
				.getSingleResult();
		
		account.setPassword(password);
		entityManager.persist(account);
		entityManager.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return account;
	}

	@Override
	public Account findAccountByName(String accountName) throws UserException {
		entityManager.getTransaction().begin();
		List<Account> accounts=entityManager.createQuery("from Account where accountName='"+accountName+"'").getResultList();
		entityManager.getTransaction().commit();
		if(accounts.isEmpty())
			throw new UserException("Account Not Found!!!");
		entityManager.clear();
		return accounts.get(0);
	}

	@Override
	public List<Account> findAccountByGroupName(String groupName) throws UserException {
		entityManager.getTransaction().begin();
		List<Account> accounts=entityManager.createQuery("from Account where accountGroup='"+groupName+"'").getResultList();
		entityManager.getTransaction().commit();
		entityManager.clear();
		if(accounts.isEmpty())
			throw new UserException("No Account Found!!!");
		return accounts;
	}

}
