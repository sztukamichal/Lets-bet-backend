package com.dtd.letsbet.model;

import java.util.Date;

public abstract class Account {

	Conversation conversation;
	Person person;
	private int id;
	private String login;
	private Date createdDate;
	private String email;
	private String password;

	public void changePassword() {
		// TODO - implement Account.changePassword
		throw new UnsupportedOperationException();
	}

}