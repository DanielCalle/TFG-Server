package es.ucm.fdi.tfg.app.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class RatingFilmId implements Serializable {

    private static final long serialVersionUID = 1L;
    private String user;
	private String film;
	
	public void setUser(String user) {
		this.user = user;
	}

	public String getUser() {
		return user;
	}
	
	public void setFilm(String film) {
		this.film = film;
	}

	public String getFilm() {
		return film;
	}
    
}
