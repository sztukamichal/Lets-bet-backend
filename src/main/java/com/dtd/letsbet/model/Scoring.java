package com.dtd.letsbet.model;

/**
 * Part of a GameplayConfiguration. It defines how many points player's will gain for MatchBet's. There are three types of events for which player's can get points : exact score - if player correctly predicted score of a match, draw - if player correctly predicted draw but didn't predict exact score, correctWinner - if player correctly predicted winner of the match but didn't predict exact score.
 */
public class Scoring {

	GameplayConfiguration configuration;
	private int ID;
	private int pointsForExactScore = 3;
	private int pointsForDraw = 2;
	private int pointsForCorrectWinner = 1;
	private boolean isEditableByParticipants;

	public void changeScoring() {
		// TODO - implement Scoring.changeScoring
		throw new UnsupportedOperationException();
	}

}