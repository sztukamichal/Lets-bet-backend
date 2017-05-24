package com.dtd.letsbet.model;

import java.util.*;

/**
 * Competition created by player (becomes a organizer) involving other player's as participants. Consists of set of phases. Defined by rules.
 */
public class Gameplay {

	Player organizer;
	List<GameplayInvitation> gameplayInvitations;
	List<RequestForGameplay> requestsForGameplay;
	List<ChangeConfigurationRequest> changeRequests;
	GameplayStatus gameplayStatus;
	List<Position> positions;
	Shoutbox shoutbox;
	GameplayConfiguration gameplayConfiguration;
	GameplayTemplate gameplayTemplate;
	private int ID;
	private String name;
	private String description;
	private Date startDate;

	public void invitePlayer() {
		// TODO - implement Gameplay.invitePlayer
		throw new UnsupportedOperationException();
	}

	public void changeInformation() {
		// TODO - implement Gameplay.changeInformation
		throw new UnsupportedOperationException();
	}

	public void acceptRequest() {
		// TODO - implement Gameplay.acceptRequest
		throw new UnsupportedOperationException();
	}

	public void declineRequest() {
		// TODO - implement Gameplay.declineRequest
		throw new UnsupportedOperationException();
	}

}