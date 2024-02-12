package com.epam.pmt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.List;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupDto implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private int id;
	
	@JsonProperty("groupName")
	private String groupName;
	
	@JsonProperty("accounts")
	private List<AccountDto> accounts;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<AccountDto> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountDto> accounts) {
		this.accounts = accounts;
	}
	
	
	
}
