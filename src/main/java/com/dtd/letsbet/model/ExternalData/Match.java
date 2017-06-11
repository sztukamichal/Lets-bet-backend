package com.dtd.letsbet.model.ExternalData;

import javax.persistence.*;
import java.util.*;

@Entity
@Table
public class Match {

	public Match() {
	}

	public Match(Competition competition, Result result, MatchStatus matchStatus, Team awayTeam, Team homeTeam, int externalId) {
		this.competition = competition;
		this.result = result;
		this.matchStatus = matchStatus;
		this.awayTeam = awayTeam;
		this.homeTeam = homeTeam;
		this.externalId = externalId;
	}

	private Competition competition;
//	private List<MatchBet> matchBets;
	private Result result;
	private MatchStatus matchStatus;
	private Team awayTeam;
	private Team homeTeam;
	private int ID;
	private int externalId;
	private Date date;
	private int matchday;
	private String homeTeamName;
	private int externalHomeTeamId;
	private String awayTeamName;
	private int externalAwayTeamId;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "competitionid")
	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

//	public List<MatchBet> getMatchBets() {
//		return matchBets;
//	}
//
//	public void setMatchBets(List<MatchBet> matchBets) {
//		this.matchBets = matchBets;
//	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "resultid")
	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "matchstatusid")
	public MatchStatus getMatchStatus() {
		return matchStatus;
	}

	public void setMatchStatus(MatchStatus matchStatus) {
		this.matchStatus = matchStatus;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "awayteamid")
	public Team getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "hometeamid")
	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="match_id_seq", initialValue=2, allocationSize=12)
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getMatchday() {
		return matchday;
	}

	public void setMatchday(int matchday) {
		this.matchday = matchday;
	}

	@Column(name = "hometeamname")
	public String getHomeTeamName() {
		return homeTeamName;
	}

	public void setHomeTeamName(String homeTeamName) {
		this.homeTeamName = homeTeamName;
	}

	@Column(name = "externalhometeamid")
	public int getExternalHomeTeamId() {
		return externalHomeTeamId;
	}

	public void setExternalHomeTeamId(int externalHomeTeamId) {
		this.externalHomeTeamId = externalHomeTeamId;
	}

	@Column(name = "awayteamname")
	public String getAwayTeamName() {
		return awayTeamName;
	}

	public void setAwayTeamName(String awayTeamName) {
		this.awayTeamName = awayTeamName;
	}

	@Column(name = "externalawayteamid")
	public int getExternalAwayTeamId() {
		return externalAwayTeamId;
	}

	public void setExternalAwayTeamId(int externalAwayTeamId) {
		this.externalAwayTeamId = externalAwayTeamId;
	}
}