package com.dtd.letsbet.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "betstatus")
public class BetStatus implements Serializable{

	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="bet_id_seq", initialValue=2, allocationSize=12)
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq-gen")
	private int ID;

	@Column(name = "name")
	private String name;

	protected BetStatus() {
	}

	public BetStatus(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "BetStatus{" +
				"ID=" + ID +
				", name='" + name + '\'' +
				'}';
	}
}