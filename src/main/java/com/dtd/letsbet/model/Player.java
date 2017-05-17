package com.dtd.letsbet.model;

import java.util.*;

/**
 * Main user type of system, that can take part in gameplays.
 */
public class Player extends Account {

	Collection<Position> position;
	Player player;
	ChangeConfigurationRequest changeConfigurationRequest;
	SpecialBetPrediction specialBetPrediction;
	MatchBetPrediction matchBetPrediction;
	Invitation invitation;
	Collection<Notification> notifications;
	Post post;
	Collection<Player> friends;
	Gameplay gameplay;
	RequestForGameplay request;
	private int points;
	private PlayerStatus status;
	private int avatarId;
	private AccountType accountType;
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