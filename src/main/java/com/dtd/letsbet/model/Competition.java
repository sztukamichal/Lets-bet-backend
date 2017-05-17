package com.dtd.letsbet.model;

import java.util.*;

public class Competition {

	LeagueTable leagueTable;
	Collection<Team> teams;
	Collection<Match> matchs;
	private int id;
	private String caption;
	private String leagueCode;
	private int year;
	private int numberOfTeams;
	private int numberOfGames;
	private Date lastUpdated;
	private int currentMatchday;
	private int numberOfMatchdays;

}