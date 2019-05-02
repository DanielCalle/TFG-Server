package es.ucm.fdi.tfg.app.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class UserFilmId implements Serializable {

    private static final long serialVersionUID = -3051838202465144461L;
    
    private Long userApp;
    private Long film;

    /**
     * @return the userApp
     */
    public Long getUserApp() {
        return userApp;
    }

    /**
     * @param userApp the userApp to set
     */
    public void setUserApp(Long userApp) {
        this.userApp = userApp;
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