package com.dtd.letsbet.model.ExternalData;

import javax.persistence.*;

@Entity
@Table
public class Result {

	public Result() {
	}

	public Result(PartialResult finalResult, PartialResult halfTime, PartialResult extraTime, PartialResult penaltyShootout) {
		this.finalResult = finalResult;
		this.halfTime = halfTime;
		this.extraTime = extraTime;
		this.penaltyShootout = penaltyShootout;
	}

	private PartialResult finalResult;
	private PartialResult halfTime;
	private PartialResult extraTime;
	private PartialResult penaltyShootout;
//	private MatchBetPrediction matchBetPrediction;
	private Match match;
	private int ID;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "finalresultid")
	public PartialResult getFinalResult() {
		return finalResult;
	}

	public void setFinalResult(PartialResult finalResult) {
		this.finalResult = finalResult;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "halftimeresultid")
	public PartialResult getHalfTime() {
		return halfTime;
	}

	public void setHalfTime(PartialResult halfTime) {
		this.halfTime = halfTime;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "extratimeresultid")
	public PartialResult getExtraTime() {
		return extraTime;
	}

	public void setExtraTime(PartialResult extraTime) {
		this.extraTime = extraTime;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "penaltyshootoutresultid")
	public PartialResult getPenaltyShootout() {
		return penaltyShootout;
	}

	public void setPenaltyShootout(PartialResult penaltyShootout) {
		this.penaltyShootout = penaltyShootout;
	}

//	public MatchBetPrediction getMatchBetPrediction() {
//		return matchBetPrediction;
//	}
//
//	public void setMatchBetPrediction(MatchBetPrediction matchBetPrediction) {
//		this.matchBetPrediction = matchBetPrediction;
//	}

	@OneToOne(mappedBy = "result", cascade = CascadeType.ALL)
	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="result_id_seq", initialValue=2, allocationSize=12)
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq-gen")
	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}
}