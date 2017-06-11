package com.dtd.letsbet.model.ExternalData;

import javax.persistence.*;

@Entity
@Table
public class Standing {

	public Standing() {
	}

	public Standing(Team team, LeagueTable leagueTable) {
		this.team = team;
		this.leagueTable = leagueTable;
	}

	private Team team;
	private LeagueTable leagueTable;
	private int ID;
	private int rank;
	private String teamName;
	private int externalTeamId;
	private int playedGames;
	private String crestUrl;
	private int points;
	private int goals;
	private int goalsAgainst;
	private int goalDifference;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "teamid")
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "leaguetableid")
	public LeagueTable getLeagueTable() {
		return leagueTable;
	}

	public void setLeagueTable(LeagueTable leagueTable) {
		this.leagueTable = leagueTable;
	}

	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="standing_id_seq", initialValue=2, allocationSize=12)
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq-gen")
	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Column(name = "teamname")
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	@Column(name = "externalteamid")
	public int getExternalTeamId() {
		return externalTeamId;
	}

	public void setExternalTeamId(int externalTeamId) {
		this.externalTeamId = externalTeamId;
	}

	@Column(name = "playedgames")
	public int getPlayedGames() {
		return playedGames;
	}

	public void setPlayedGames(int playedGames) {
		this.playedGames = playedGames;
	}

	@Column(name = "cresturl")
	public String getCrestUrl() {
		return crestUrl;
	}

	public void setCrestUrl(String crestUrl) {
		this.crestUrl = crestUrl;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getGoals() {
		return goals;
	}

	public void setGoals(int goals) {
		this.goals = goals;
	}

	@Column(name = "goalsagainst")
	public int getGoalsAgainst() {
		return goalsAgainst;
	}

	public void setGoalsAgainst(int goalsAgainst) {
		this.goalsAgainst = goalsAgainst;
	}

	@Column(name = "goaldifference")
	public int getGoalDifference() {
		return goalDifference;
	}

	public void setGoalDifference(int goalDifference) {
		this.goalDifference = goalDifference;
	}
}