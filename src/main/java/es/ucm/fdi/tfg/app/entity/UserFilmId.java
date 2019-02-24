package es.ucm.fdi.tfg.app.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class UserFilmId implements Serializable{

    private static final long serialVersionUID = 1L;
    private String user;
    private String film;

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @return the film
     */
    public String getFilm() {
        return film;
    }

    /**
     * @param film the film to set
     */
    public void setFilm(String film) {
        this.film = film;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }
}