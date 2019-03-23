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
public class UserFilm{

    @Id
    @ManyToOne
    private UserApp userApp;

    @Id
    @ManyToOne
    private Film film;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;

	public UserApp getUserApp() {
		return userApp;
	}

	public void setUserApp(UserApp userApp) {
		this.userApp = userApp;
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

    
}