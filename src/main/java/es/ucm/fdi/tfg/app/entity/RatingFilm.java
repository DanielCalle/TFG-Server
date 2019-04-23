package es.ucm.fdi.tfg.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@IdClass(RatingFilmId.class)
public class RatingFilm {
    
    @Id
    @ManyToOne
    private UserApp user;

    @Id
    @ManyToOne
    private Film film;

    private float rating;

    public void setUser(UserApp user) {
        this.user = user;
    }

    public UserApp getUser() {
        return user;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Film getFilm() {
        return film;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getRating() {
        return rating;
    }


}
