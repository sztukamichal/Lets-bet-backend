package com.dtd.letsbet.model;

import java.util.*;

public class Match {

	Competition competition;
	MatchBet matchBet;
	Collection<Team> teams;
	Result result;
	private int id;
	private int competitionId;
	private String date;
	private int matchday;
	private String homeTeamName;
	private int homeTeamId;
	private String awayTeamName;
	private int awayTeamId;
	private MatchStatus status;

}