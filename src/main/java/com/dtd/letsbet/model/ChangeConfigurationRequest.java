package com.dtd.letsbet.model;

import java.util.*;

public class ChangeConfigurationRequest {

	ChangeConfigurationRequestStatus changeConfigurationRequestStatus;
	GameplayConfiguration proposedConfiguration;
	Player creator;
	List<Account> decliners;
	List<Account> accepters;
	Gameplay gameplay;
	private int ID;
	private String reason;

	public void acceptChangeRequest() {
		// TODO - implement ChangeConfigurationRequest.acceptChangeRequest
		throw new UnsupportedOperationException();
	}

	public void declineChangeRequest() {
		// TODO - implement ChangeConfigurationRequest.declineChangeRequest
		throw new UnsupportedOperationException();
	}

}