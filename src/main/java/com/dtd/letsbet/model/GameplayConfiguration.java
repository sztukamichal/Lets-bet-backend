package com.dtd.letsbet.model;

public class GameplayConfiguration {

	Gameplay gameplay;
	ChangeConfigurationRequest changeConfigurationRequest;
	Scoring basicScoringRules;
	GameplaysType gameplaysType;
	private int ID;
	private boolean isInvitingByParticipantsAllowed;
	private boolean isJoiningAfterStartAllowed;
	private boolean isPossibleToAddBetAfterStart;
	private boolean isAnotherPlayerPredictionVisible;
	private boolean isChangingRulesAllowedAfterStart;
	private boolean isAddingBetsByParticipantsAllowed;
	private int initialContribution;
	private Integer divisionOfPrizes;
	private int maxNumOfParticipants;

	public void changeConfiguration() {
		// TODO - implement GameplayConfiguration.changeConfiguration
		throw new UnsupportedOperationException();
	}

}