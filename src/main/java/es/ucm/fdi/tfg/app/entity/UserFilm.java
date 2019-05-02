package es.ucm.fdi.tfg.app.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "user_film")
@IdClass(UserFilmId.class)
public class UserFilm {

	@Id
	@ManyToOne
	private User User;

	@Id
	@ManyToOne
	private Film film;

	@Temporal(javax.persistence.TemporalType.DATE)
	private Date date;

	private float rating;

	public User getUserApp() {
		return User;
	}

	public void setUserApp(User User) {
		this.User = User;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public float getRating() {
		return rating;
	}

}