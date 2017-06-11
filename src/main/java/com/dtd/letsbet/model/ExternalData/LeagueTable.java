package com.dtd.letsbet.model.ExternalData;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "leaguetable")
public class LeagueTable {

	public LeagueTable() {
	}

	public LeagueTable(String leagueCaption) {
		this.leagueCaption = leagueCaption;
	}

	private List<Standing> standings;
	private Competition competition;
	private int ID;
	private String leagueCaption;
	private int matchDay;

	@OneToMany(mappedBy = "leagueTable", cascade = CascadeType.ALL)
	public List<Standing> getStandings() {
		return standings;
	}

	public void setStandings(List<Standing> standings) {
		this.standings = standings;
	}

	@OneToOne(mappedBy = "leagueTable", cascade = CascadeType.ALL)
	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="leaguetable_id_seq", initialValue=2, allocationSize=12)
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq-gen")
	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	@Column(name = "leaguecaption")
	public String getLeagueCaption() {
		return leagueCaption;
	}

	public void setLeagueCaption(String leagueCaption) {
		this.leagueCaption = leagueCaption;
	}

	@Column(name = "matchday")
	public int getMatchDay() {
		return matchDay;
	}

	public void setMatchDay(int matchDay) {
		this.matchDay = matchDay;
	}
}