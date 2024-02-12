package com.epam.pmt.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pmt_account")
public class Account {
	
	@Id
	@Column(name="account_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="account_name")
	private String accountName;
	
	@Column(name="account_url")
	private String url;
	
	@Column(name="account_password")
	private String password;
	
	private String accountGroup;
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,
				CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="group_id")
	private Group group;

	
	
	
	public Account()
	{}

	public Account(String accountName, String url, String password, String accountGroup) {
		super();
		this.accountName = accountName;
		this.url = url;
		this.password = password;
		this.accountGroup = accountGroup;
	}




	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	

	public String getAccountGroup() {
		return accountGroup;
	}


	public void setAccountGroup(String accountGroup) {
		this.accountGroup = accountGroup;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Group getGroups() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
	
	


	
	
	

}
