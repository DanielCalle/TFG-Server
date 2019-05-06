package es.ucm.fdi.tfg.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_film", indexes = {
	@Index(name = "user_film_user_index", columnList = "user_id"),
	@Index(name = "user_film_film_index", columnList = "user_id")
})
@IdClass(UserFilmId.class)
public class UserFilm {

	@Id
	@ManyToOne
	private User user;

	@Id
	@ManyToOne
	private Film film;

	private float rating;

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the film
	 */
	public Film getFilm() {
		return film;
	}

	/**
	 * @param film the film to set
	 */
	public void setFilm(Film film) {
		this.film = film;
	}

	/**
	 * @return the rating
	 */
	public float getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(float rating) {
		this.rating = rating;
	}
}