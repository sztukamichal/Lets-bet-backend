package com.dtd.letsbet.model;

import java.util.*;

/**
 * Main user type of system, that can take part in gameplays.
 */
public class Player extends Account {

	List<Position> positions;
	List<Friends> friends;
	List<ChangeConfigurationRequest> createdChangeConfigurationRequest;
	List<SpecialBetPrediction> specialBetPredictions;
	List<MatchBetPrediction> matchBetPredictions;
	List<Invitation> receivedInvitations;
	List<Notification> notifications;
	List<Post> posts;
	List<Gameplay> createdGameplays;
	List<Invitation> sentInvitation;
	List<RequestForGameplay> requestsForGameplays;
	PlayerStatus playerStatus;
	private int points;
	private int avatarId;
	private String screenName;
	private int tokens;

	public void changeAccountType() {
		// TODO - implement Player.changeAccountType
		throw new UnsupportedOperationException();
	}

	public void changeAvatar() {
		// TODO - implement Player.changeAvatar
		throw new UnsupportedOperationException();
	}

	public void changeScreenName() {
		// TODO - implement Player.changeScreenName
		throw new UnsupportedOperationException();
	}

}