package com.dtd.letsbet.model;

import com.dtd.letsbet.model.Account;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "accounttype")
public class AccountType {

	private int ID;
	private String name;
	private List<Account> accounts;

	protected AccountType() {
	}

	public AccountType(String name) {
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

	@OneToMany(mappedBy="accountType", cascade = CascadeType.ALL)
	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}



	@Override
	public String toString() {
		return "AccountType{" +
				"ID=" + ID +
				", name='" + name + '\'' +
				'}';
	}




}