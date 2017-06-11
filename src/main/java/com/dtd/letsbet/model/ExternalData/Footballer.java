package com.dtd.letsbet.model.ExternalData;

import javax.persistence.*;
import java.util.*;

@Entity
@Table()
public class Footballer {

	public Footballer() {
	}

	public Footballer(String name) {
		this.name = name;
	}

	public Footballer(int externalId, String name) {
		this.externalId = externalId;
		this.name = name;
	}

	private List<Team> teams;
	private int ID;
	private int externalId;
	private String name;
	private String position;
	private int jerseyNumber;
	private Date dateOfBirth;
	private String nationality;
	private Date contractUntil;
	private String marketValue;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "footballers")
	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "jerseynumber")
	public int getJerseyNumber() {
		return jerseyNumber;
	}

	public void setJerseyNumber(int jerseyNumber) {
		this.jerseyNumber = jerseyNumber;
	}

	@Column(name = "dateofbirth")
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Column(name = "contractuntil")
	public Date getContractUntil() {
		return contractUntil;
	}

	public void setContractUntil(Date contractUntil) {
		this.contractUntil = contractUntil;
	}

	@Column(name = "marketvalue")
	public String getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(String marketValue) {
		this.marketValue = marketValue;
	}
}