package com.dtd.letsbet.model;

import com.dtd.letsbet.model.Account;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "playerstatus")
public class PlayerStatus {

	List<Player> player;
	private int ID;
	private String name;
	private List<Account> accounts;


	protected PlayerStatus() {
	}

	public PlayerStatus(String name) {
		this.name = name;
	}

	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="accounttype_id_seq", allocationSize=12)
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq-gen")
	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}


	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy="playerStatus", cascade = CascadeType.ALL)
	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}



}