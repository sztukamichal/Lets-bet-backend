package com.dtd.letsbet.model.ExternalData;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "matchstatus")
public class MatchStatus {

	public MatchStatus() {
	}

	public MatchStatus(String name) {
		this.name = name;
	}

	private List<Match> match;
	private int ID;
	private String name;

	@OneToMany(mappedBy = "matchStatus", cascade = CascadeType.ALL)
	public List<Match> getMatch() {
		return match;
	}

	public void setMatch(List<Match> match) {
		this.match = match;
	}

	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="matchstatus_id_seq", initialValue=2, allocationSize=12)
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq-gen")
	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}