package com.dtd.letsbet.model;


import com.dtd.letsbet.model.Account;
import com.dtd.letsbet.model.AccountType;
import com.dtd.letsbet.model.PlayerStatus;

import javax.persistence.*;
import java.util.Date;

/**
 * Main user type of system, that can take part in gameplays.
 */

@Entity
@DiscriminatorValue("player")
public class Player extends Account {

	public Player() {
	}

	public Player(AccountType accountType, PlayerStatus playerstatus, String login, Date createddate, String email, String password,
				  Date lastlogindate, int lockout, Date lockoutdate, int points, int avatarid, String screenname, int tokens) {
		this.accountType = accountType;
		this.playerstatus = playerstatus;
		this.login = login;
		this.createddate = createddate;
		this.email = email;
		this.password = password;
		this.lastlogindate = lastlogindate;
		this.lockout = lockout;
		this.lockoutdate = lockoutdate;
		this.points  = points;
		this.avatarid = avatarid;
		this.screenname = screenname;
		this.tokens = tokens;
	}

	AccountType accountType;
	PlayerStatus playerstatus;
	String login;
	Date createddate;
	String email;
	String password;
	Date lastlogindate;
	int lockout;
	Date lockoutdate;
	private int points;
	private int avatarid;
	private String screenname;
	private int tokens;

	//List<Position> positions;
	//List<Friends> friends;
	//List<ChangeConfigurationRequest> createdChangeConfigurationRequest;
	//List<SpecialBetPrediction> specialBetPredictions;
	//List<MatchBetPrediction> matchBetPredictions;
	//List<Invitation> receivedInvitations;
	//List<Notification> notifications;
	//List<Post> posts;
	//List<Gameplay> createdGameplays;
	//List<Invitation> sentInvitation;
	//List<RequestForGameplay> requestsForGameplays;
	//PlayerStatus playerStatus;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="accounttypeid")
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}


	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="playerstatusid")
	public PlayerStatus getPlayerstatus() {
		return playerstatus;
	}

	public void setPlayerstatus(PlayerStatus playerstatus) {
		this.playerstatus = playerstatus;
	}

	@Column(name = "login")
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "createddate")
	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}


	@Column(name = "email")
	public String getEmail() {
		return email;

	}


	@Column(name = "points")
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Column(name = "avatarid")
	public int getAvatarid() {
		return avatarid;
	}

	public void setAvatarid(int avatarid) {
		this.avatarid = avatarid;
	}

	@Column(name = "screenname")
	public String getScreenname() {
		return screenname;
	}

	public void setScreenname(String screenname) {
		this.screenname = screenname;
	}

	@Column(name = "tokens")
	public int getTokens() {
		return tokens;
	}

	public void setTokens(int tokens) {
		this.tokens = tokens;
	}



	public void changeAccountType() {
		// TODO - implement Player.changeAccountType
		throw new UnsupportedOperationException();
	}

	public void changeAvatar() {
		// TODO - implement Player.changeAvatar
		throw new UnsupportedOperationException();
	}

	public void changeScreenName() {
		// TODO - implement Player.changeScreenName
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		return "Player{" + "avatar id " + avatarid +
				", points " + points  + '\'' +
				'}';
	}


}