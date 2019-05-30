package es.ucm.fdi.tfg.app.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class UserFilmId implements Serializable {

    private static final long serialVersionUID = -3051838202465144461L;
    
    private Long user;
    private Long film;

    /**
     * @return the user
     */
    public Long getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(Long user) {
        this.user = user;
    }

    /**
     * @return the film
     */
    public Long getFilm() {
        return film;
    }

    /**
     * @param film the film to set
     */
    public void setFilm(Long film) {
        this.film = film;
    }
}