package com.dtd.letsbet.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "person")

public class Person {

	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="bet_id_seq", initialValue=2, allocationSize=12)
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq-gen")
	private int Id;

	@Column(name = "genderid")
	private int genderid;

	@Column(name = "accountid")
	private int accountid;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "lastname")
	private String lastname;

	@Column(name = "birthday")
	private Date birthday;

	@Column(name = "nationality")
	private String nationality;

	@Column(name = "favouriteteam")
	private Integer favouriteteam;

	protected Person(){
		super();

	}

	public Person(int genderid, int accountid, String firstname, String lastname, Date birthday, String nationality, Integer favouriteteam) {
		this.genderid = genderid;
		this.accountid = accountid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthday = birthday;
		this.nationality = nationality;
		this.favouriteteam = favouriteteam;
	}


	@Override
	public String toString() {
		return "Person{" + "ID =" + Id +
				", Name=" + firstname +
				", Surname='" + lastname + '\'' +
				'}';
	}



	public void changeInformation() {
		// TODO - implement Person.changeInformation

		throw new UnsupportedOperationException();
	}

	public int getPersonId() {
		// TODO - implement Person.changeInformation
		int personId = Id;

		return personId;
	}

}