package com.epam.pmt.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="pmt_group")
public class Group {
	
	@Id
	@Column(name="group_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="group_name")
	private String groupName;
	
	@OneToMany(mappedBy = "accountGroup",
			   cascade = CascadeType.ALL)
	private List<Account> accounts;

	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}


	public List<Account> getAccounts() {
		if(accounts==null)
			accounts=new ArrayList<>();
		return accounts;
	}


	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	
}
