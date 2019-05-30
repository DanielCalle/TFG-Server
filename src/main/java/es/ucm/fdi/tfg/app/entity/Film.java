package es.ucm.fdi.tfg.app.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "uuid must not be null")
    @Column(unique = true)
    private String uuid;

    @NotNull(message = "name must not be null")
    private String name;

    private String director;

    private String trailerURL;

    private String synopsis;

    private String imageURL;

    private String genre;
    
    @Value("${some.key:0}")
    private int duration;

    private double rating;

    private String country;

    @NotNull(message = "premiere must not be null")
    private Date premiere;

    @JsonIgnore
    @OneToMany(mappedBy = "film")
    private List<UserFilm> userFilms;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the director
     */
    public String getDirector() {
        return director;
    }

    /**
     * @param director the director to set
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * @return the trailerURL
     */
    public String getTrailerURL() {
        return trailerURL;
    }

    /**
     * @param trailerURL the trailerURL to set
     */
    public void setTrailerURL(String trailerURL) {
        this.trailerURL = trailerURL;
    }

    /**
     * @return the synopsis
     */
    public String getSynopsis() {
        return synopsis;
    }

    /**
     * @param synopsis the synopsis to set
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**
     * @return the imageURL
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * @param imageURL the imageURL to set
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    /**
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @param genre the genre to set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * @return the rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the userFilms
     */
    public List<UserFilm> getUserFilms() {
        return userFilms;
    }

    /**
     * @param userFilms the userFilms to set
     */
    public void setUserFilms(List<UserFilm> userFilms) {
        this.userFilms = userFilms;
    }

    /**
     * @return the premiere
     */
    public Date getPremiere() {
        return premiere;
    }

    /**
     * @param premiere the premiere to set
     */
    public void setPremiere(Date premiere) {
        this.premiere = premiere;
    }
}