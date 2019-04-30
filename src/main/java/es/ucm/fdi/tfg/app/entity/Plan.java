package es.ucm.fdi.tfg.app.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "plan")
public class Plan {

	@Id
	@GeneratedValue
	private Long id;

	private String title;

	@ManyToOne
	private UserApp creator;

	@ManyToOne
	private Film film;

	@ManyToMany
	private List<UserApp> joinedUsers;

	@Temporal(javax.persistence.TemporalType.DATE)
	private Date date;

	private String location;

	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public UserApp getCreator() {
		return creator;
	}

	public void setCreator(UserApp creator) {
		this.creator = creator;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public List<UserApp> getJoinedUsers() {
		return joinedUsers;
	}

	public void setJoinedUsers(List<UserApp> joinedUsers) {
		this.joinedUsers = joinedUsers;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
