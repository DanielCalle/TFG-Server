package es.ucm.fdi.tfg.app.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class UserFilmId implements Serializable {

    private static final long serialVersionUID = -3051838202465144461L;
    
    private Long User;
    private Long film;

    /**
     * @return the User
     */
    public Long getUserApp() {
        return User;
    }

    /**
     * @param User the User to set
     */
    public void setUserApp(Long User) {
        this.User = User;
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