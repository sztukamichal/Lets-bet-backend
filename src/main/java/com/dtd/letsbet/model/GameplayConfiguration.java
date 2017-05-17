package com.dtd.letsbet.model;

import java.util.Map;

public class GameplayConfiguration {

	Gameplay gameplay;
	ChangeConfigurationRequest changeConfigurationRequest;
	Scoring basicScoringRules;
	private BooleanRule isInvitingByParticipantsAllowed;
	private BooleanRule isJoiningAfterStartAllowed;
	private BooleanRule isPossibleToAddBetAfterStart;
	private BooleanRule isAnotherPlayerPredictionVisible;
	private BooleanRule isChangingRulesAllowedAfterStart;
	private BooleanRule isAddingBetsByParticipantsAllowed;
	private IntegerRule initialContribution;
	private Map divisionOfPrizes;
	private IntegerRule maxNumOfParticipants;
	private GameplayType type;

	public void changeConfiguration() {
		// TODO - implement GameplayConfiguration.changeConfiguration
		throw new UnsupportedOperationException();
	}

}