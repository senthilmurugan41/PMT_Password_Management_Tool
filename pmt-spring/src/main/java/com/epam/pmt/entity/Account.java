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
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,
			CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="group_id")
	private Group accountGroup;
	
	
	
	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Group getAccountGroup() {
		if(accountGroup==null)
			accountGroup=new Group();
		return accountGroup;
	}

	public void setAccountGroup(Group accountGroup) {
		this.accountGroup = accountGroup;
	}

	
	
	
	


	
	
	

}
