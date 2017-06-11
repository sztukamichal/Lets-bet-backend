package com.dtd.letsbet.model.ExternalData;

import javax.persistence.*;
import java.util.*;
@Entity
@Table
public class Competition {

	public Competition() {
	}

	public Competition(LeagueTable leagueTable, List<Team> teams, int externalId, String caption) {
		this.leagueTable = leagueTable;
		this.teams = teams;
		this.externalId = externalId;
		this.caption = caption;
	}

	private LeagueTable leagueTable;
	private List<Team> teams;
	private List<Match> matches;
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

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "leaguetableid")
	public LeagueTable getLeagueTable() {
		return leagueTable;
	}

	public void setLeagueTable(LeagueTable leagueTable) {
		this.leagueTable = leagueTable;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "competition_team", joinColumns = {
			@JoinColumn(name = "competitionid", referencedColumnName = "id")},
			inverseJoinColumns = {
					@JoinColumn(name = "teamid", referencedColumnName = "id")
			})
	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	@OneToMany(mappedBy = "competition", cascade = CascadeType.ALL)
	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}

	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="competition_id_seq", initialValue=2, allocationSize=12)
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq-gen")
	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	@Column(name = "externalid")
	public int getExternalId() {
		return externalId;
	}

	public void setExternalId(int externalId) {
		this.externalId = externalId;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	@Column(name = "leaguecode")
	public String getLeagueCode() {
		return leagueCode;
	}

	public void setLeagueCode(String leagueCode) {
		this.leagueCode = leagueCode;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Column(name = "numberofteams")
	public int getNumberOfTeams() {
		return numberOfTeams;
	}

	public void setNumberOfTeams(int numberOfTeams) {
		this.numberOfTeams = numberOfTeams;
	}

	@Column(name = "numberofgames")
	public int getNumberOfGames() {
		return numberOfGames;
	}

	public void setNumberOfGames(int numberOfGames) {
		this.numberOfGames = numberOfGames;
	}

	@Column(name = "lastupdated")
	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Column(name = "currentmatchday")
	public int getCurrentMatchday() {
		return currentMatchday;
	}

	public void setCurrentMatchday(int currentMatchday) {
		this.currentMatchday = currentMatchday;
	}

	@Column(name = "numberofmatchdays")
	public int getNumberOfMatchdays() {
		return numberOfMatchdays;
	}

	public void setNumberOfMatchdays(int numberOfMatchdays) {
		this.numberOfMatchdays = numberOfMatchdays;
	}
}