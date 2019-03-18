package es.ucm.fdi.tfg.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_film")
@IdClass(UserFilmId.class)
public class UserFilm{

    /*public Tiene(){

    }
    public Tiene(User user, Film film){
        this.film = film;
        this.user = user;
    }*/

    @Id
    @ManyToOne
    private UserApp userApp;

    @Id
    @ManyToOne
    private Film film;

}