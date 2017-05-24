package com.dtd.letsbet.model;

import java.util.Date;

/**
 * Player can invite other player to become friends or to take part into his gameplay.
 */
public abstract class Invitation {

	Player sender;
	InvitationStatus invitationStatus;
	Player receiver;
	private int ID;
	private Date createdDate;
	private Date resolvedDate;

	public void acceptInvitation() {
		// TODO - implement Invitation.acceptInvitation
		throw new UnsupportedOperationException();
	}

	public void declineInvitation() {
		// TODO - implement Invitation.declineInvitation
		throw new UnsupportedOperationException();
	}

}