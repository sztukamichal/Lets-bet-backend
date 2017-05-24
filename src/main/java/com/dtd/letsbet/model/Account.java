package com.dtd.letsbet.model;

import java.util.*;

public abstract class Account {

	AccountType accountType;
	List<ChangeConfigurationRequest> declinedChangeConfigurationRequest;
	List<ChangeConfigurationRequest> acceptedChangeConfigurationRequest;
	Person person;
	List<Conversation> conversation;
	private int ID;
	private String login;
	private Date createdDate;
	private String email;
	private String password;
	private Date lastLoginDate;
	private int lockout;
	private Date lockoutDate;

	public void changePassword() {
		// TODO - implement Account.changePassword
		throw new UnsupportedOperationException();
	}

}