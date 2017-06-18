package com.dtd.letsbet.model;

import java.util.Date;

		import com.dtd.letsbet.model.AccountType;
		import com.dtd.letsbet.model.PlayerStatus;

		import javax.persistence.*;
		import java.util.Date;


@Entity
@Table(name="account")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="discriminator")
public class Account {

	public Account() {
	}

	private int ID;
	private AccountType accountType;
	private PlayerStatus playerstatus;
	private String login;
	private Date createddate;
	private String email;
	private String password;
	private Date lastlogindate;
	private int lockout;
	private Date lockoutdate;
	//List<ChangeConfigurationRequest> declinedChangeConfigurationRequest;
	//List<ChangeConfigurationRequest> acceptedChangeConfigurationRequest;
	//List<Conversation> conversation;

	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="account_id_seq", allocationSize=12)
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq-gen")
	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}


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

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "lastlogindate")
	public Date getLastlogindate() {
		return lastlogindate;
	}

	public void setLastlogindate(Date lastlogindate) {
		this.lastlogindate = lastlogindate;
	}

	@Column(name = "lockout")
	public int getLockout() {
		return lockout;
	}

	public void setLockout(int lockout) {
		this.lockout = lockout;
	}

	@Column(name = "lockoutdate")
	public Date getLockoutdate() {
		return lockoutdate;
	}

	public void setLockoutdate(Date lockoutdate) {
		this.lockoutdate = lockoutdate;
	}



	public void changePassword() {
		// TODO - implement Account.changePassword
		throw new UnsupportedOperationException();
	}

}