package es.ucm.fdi.tfg.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
@IdClass(FriendshipId.class)
public class Friendship {

    @Id
    @ManyToOne
    UserApp requester;

    @Id
    @ManyToOne
    UserApp friend;

    @Temporal(javax.persistence.TemporalType.DATE)
    Date date;

    @Column(nullable = false)
    boolean active;

	public UserApp getRequester() {
		return requester;
	}

	public void setRequester(UserApp requester) {
		this.requester = requester;
	}

	public UserApp getFriend() {
		return friend;
	}

	public void setFriend(UserApp friend) {
		this.friend = friend;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
