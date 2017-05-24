package com.dtd.letsbet.model;

import java.util.Date;

public abstract class Bet {

	BetStatus betStatus;
	Phase phase;
	private int ID;
	private String name;
	private String description;
	private Date creationDate;
	private Date updatedDate;

	public void changeStatus() {
		// TODO - implement Bet.changeStatus
		throw new UnsupportedOperationException();
	}

	public void calculatePrize() {
		// TODO - implement Bet.calculatePrize
		throw new UnsupportedOperationException();
	}

}