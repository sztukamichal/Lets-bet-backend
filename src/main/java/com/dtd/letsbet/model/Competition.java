package com.dtd.letsbet.model;

import java.util.*;

public class Competition {

	LeagueTable leagueTable;
	List<Team> teams;
	List<Match> matches;
	private int ID;
	private int externalId;
	private String caption;
	private String leagueCode;
	private int year;
	private int numberOfTeams;
	private int numberOfGames;
	private Date lastUpdated;
	private int currentMatchday;
	private int numberOfMatchdays;

}