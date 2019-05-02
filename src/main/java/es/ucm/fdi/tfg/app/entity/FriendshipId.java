package es.ucm.fdi.tfg.app.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class FriendshipId implements Serializable {

	private static final long serialVersionUID = 410853427679353546L;

	private Long requester;
	private Long friend;

	/**
	 * @return the requester
	 */
	public Long getRequester() {
		return requester;
	}

	/**
	 * @param requester the requester to set
	 */
	public void setRequester(Long requester) {
		this.requester = requester;
	}

	/**
	 * @return the friend
	 */
	public Long getFriend() {
		return friend;
	}

	/**
	 * @param friend the friend to set
	 */
	public void setFriend(Long friend) {
		this.friend = friend;
	}
}
