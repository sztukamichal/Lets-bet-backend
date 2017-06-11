package com.dtd.letsbet.model.ExternalData;

import javax.persistence.*;

@Entity
@Table(name = "partialresult")
public class PartialResult {

	public PartialResult() {
	}

	public PartialResult(int goalsHomeTeam, int goalsAwayTeam) {
		this.goalsHomeTeam = goalsHomeTeam;
		this.goalsAwayTeam = goalsAwayTeam;
	}

	private Result finalResult;
	private Result halfTimeResult;
	private Result extraTimeResult;
	private Result penaltyShootoutResult;
	private int ID;
	private int goalsHomeTeam;
	private int goalsAwayTeam;

	@OneToOne(mappedBy = "finalResult")
	public Result getFinalResult() {
		return finalResult;
	}

	public void setFinalResult(Result finalResult) {
		this.finalResult = finalResult;
	}

	@OneToOne(mappedBy = "halfTime")
	public Result getHalfTimeResult() {
		return halfTimeResult;
	}

	public void setHalfTimeResult(Result halfTimeResult) {
		this.halfTimeResult = halfTimeResult;
	}

	@OneToOne(mappedBy = "extraTime")
	public Result getExtraTimeResult() {
		return extraTimeResult;
	}

	public void setExtraTimeResult(Result extraTimeResult) {
		this.extraTimeResult = extraTimeResult;
	}

	@OneToOne(mappedBy = "penaltyShootout")
	public Result getPenaltyShootoutResult() {
		return penaltyShootoutResult;
	}

	public void setPenaltyShootoutResult(Result penaltyShootoutResult) {
		this.penaltyShootoutResult = penaltyShootoutResult;
	}

	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="partialresult_id_seq", initialValue=2, allocationSize=12)
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq-gen")
	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	@Column(name = "goalshometeam")
	public int getGoalsHomeTeam() {
		return goalsHomeTeam;
	}

	public void setGoalsHomeTeam(int goalsHomeTeam) {
		this.goalsHomeTeam = goalsHomeTeam;
	}

	@Column(name = "goalsawayteam")
	public int getGoalsAwayTeam() {
		return goalsAwayTeam;
	}

	public void setGoalsAwayTeam(int goalsAwayTeam) {
		this.goalsAwayTeam = goalsAwayTeam;
	}
}