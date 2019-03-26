package es.ucm.fdi.tfg.app.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

public class UserFilmId implements Serializable{

    private static final long serialVersionUID = 1L;
    private String userApp;
    private String film;

    /**
     * @return the user
     */
    public String getUser() {
        return userApp;
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
     * @param userApp the user to set
     */
    public void setUser(String user) {
        this.userApp = user;
    }
}