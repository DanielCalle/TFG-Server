package es.ucm.fdi.tfg.app.transfer;

public class TFriendship {

	private Long requesterId;
	private Long friendId;
	private String date;
	private boolean active;

	/**
	 * @return the requesterId
	 */
	public Long getRequesterId() {
		return requesterId;
	}

	/**
	 * @param requesterId the requesterId to set
	 */
	public void setRequesterId(Long requesterId) {
		this.requesterId = requesterId;
	}

	/**
	 * @return the friendId
	 */
	public Long getFriendId() {
		return friendId;
	}

	/**
	 * @param friendId the friendId to set
	 */
	public void setFriendId(Long friendId) {
		this.friendId = friendId;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
}
