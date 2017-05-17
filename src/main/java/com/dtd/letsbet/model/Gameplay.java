package com.dtd.letsbet.model;

import java.util.*;

/**
 * Competition created by player (becomes a organizer) involving other player's as participants. Consists of set of phases. Defined by rules.
 */
public class Gameplay extends GameplayTemplate {

	Player organizer;
	GameplayInvitation invitation;
	RequestForGameplay request;
	Collection<Position> position;
	Shoutbox shoutbox;
	GameplayConfiguration gameplayConfiguration;
	private String name;
	private String description;
	private GameplayStatus status;
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