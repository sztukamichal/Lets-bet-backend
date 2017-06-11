package com.dtd.letsbet.model;

import com.dtd.letsbet.model.ExternalData.Result;

import java.util.Date;

public class MatchBetPrediction {

	Result predictedResult;
	PredictionStatus predictionStatus;
	MatchBet matchBet;
	Player player;
	private int ID;
	private int gainedPoints;
	private Date updateDate;

}