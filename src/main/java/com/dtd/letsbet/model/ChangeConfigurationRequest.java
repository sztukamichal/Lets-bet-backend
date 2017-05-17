package com.dtd.letsbet.model;

import java.util.*;

public class ChangeConfigurationRequest {

	GameplayConfiguration proposedConfiguration;
	Player requester;
	Collection<Player> decliners;
	Collection<Player> accepters;
	private String reason;
	private RequestStatus status;

	public void acceptChangeRequest() {
		// TODO - implement ChangeConfigurationRequest.acceptChangeRequest
		throw new UnsupportedOperationException();
	}

	public void declineChangeRequest() {
		// TODO - implement ChangeConfigurationRequest.declineChangeRequest
		throw new UnsupportedOperationException();
	}

}