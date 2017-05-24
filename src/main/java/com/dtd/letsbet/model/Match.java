package com.dtd.letsbet.model;

import java.util.*;

public class Match {

	Competition competition;
	List<MatchBet> matchBets;
	Result result;
	MatchStatus matchStatus;
	List<TeamMatch> teamMatch;
	private int ID;
	private int externalId;
	private Date date;
	private int matchday;
	private String homeTeamName;
	private int externalHomeTeamId;
	private String awayTeamName;
	private int externalAwayTeamId;

}