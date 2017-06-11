package com.dtd.letsbet.model.ExternalData;

import javax.persistence.*;
import java.util.*;


@Entity
@Table
public class Team {

	public Team() {
	}

	public Team(int externalId, String name) {
		this.externalId = externalId;
		this.name = name;
	}

	public Team(List<Footballer> footballers, int externalId, String name) {
		this.footballers = footballers;
		this.externalId = externalId;
		this.name = name;
	}

	private List<Competition> competitions;
	private List<Match> homeMatches;
	private List<Match> awayMatches;
	private List<Standing> standings;
	private List<Footballer> footballers;
	private int ID;
	private int externalId;
	private String name;
	private String code;
	private String shortName;
	private String squadMarketValue;
	private String crestUrl;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "teams")
	public List<Competition> getCompetitions() {
		return competitions;
	}

	public void setCompetitions(List<Competition> competitions) {
		this.competitions = competitions;
	}

	@OneToMany(mappedBy = "homeTeam", cascade = CascadeType.ALL)
	public List<Match> getHomeMatches() {
		return homeMatches;
	}

	public void setHomeMatches(List<Match> homeMatches) {
		this.homeMatches = homeMatches;
	}

	@OneToMany(mappedBy = "awayTeam", cascade = CascadeType.ALL)
	public List<Match> getAwayMatches() {
		return awayMatches;
	}

	public void setAwayMatches(List<Match> awayMatches) {
		this.awayMatches = awayMatches;
	}

	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	public List<Standing> getStandings() {
		return standings;
	}

	public void setStandings(List<Standing> standings) {
		this.standings = standings;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "team_footballer", joinColumns = {
			@JoinColumn(name = "teamid", referencedColumnName = "id")},
			inverseJoinColumns = {
					@JoinColumn(name = "footballerid", referencedColumnName = "id")
			})
	public List<Footballer> getFootballers() {
		return footballers;
	}

	public void setFootballers(List<Footballer> footballers) {
		this.footballers = footballers;
	}

	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="bet_id_seq", initialValue=2, allocationSize=12)
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

	@Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "shortname")
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name = "squadmarketvalue")
	public String getSquadMarketValue() {
		return squadMarketValue;
	}

	public void setSquadMarketValue(String squadMarketValue) {
		this.squadMarketValue = squadMarketValue;
	}

	@Column(name = "cresturl")
	public String getCrestUrl() {
		return crestUrl;
	}

	public void setCrestUrl(String crestUrl) {
		this.crestUrl = crestUrl;
	}
}