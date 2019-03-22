package es.ucm.fdi.tfg.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "friendship")
@IdClass(FriendshipId.class)
public class Friendship {

    @Id
    @ManyToOne
    private UserApp requester;

    @Id
    @ManyToOne
    private UserApp friend;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;

    @Column(nullable = false)
    private boolean active;

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