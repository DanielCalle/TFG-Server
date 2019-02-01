package es.ucm.fdi.tfg.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Film {

    @Id
    private String id;

    private String film_name;

    private String id_director;

    private String trailer;

    private String film_description;

    private String genre;

    private int duration;

    private int valoration;

    private String country;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the film_name
     */
    public String getFilm_name() {
        return film_name;
    }

    /**
     * @param film_name the film_name to set
     */
    public void setFilm_name(String film_name) {
        this.film_name = film_name;
    }

    /**
     * @return the id_director
     */
    public String getId_director() {
        return id_director;
    }

    /**
     * @param id_director the id_director to set
     */
    public void setId_director(String id_director) {
        this.id_director = id_director;
    }

    /**
     * @return the trailer
     */
    public String getTrailer() {
        return trailer;
    }

    /**
     * @param trailer the trailer to set
     */
    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    /**
     * @return the film_description
     */
    public String getFilm_description() {
        return film_description;
    }

    /**
     * @param film_description the film_description to set
     */
    public void setFilm_description(String film_description) {
        this.film_description = film_description;
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
     * @return the valoration
     */
    public int getValoration() {
        return valoration;
    }

    /**
     * @param valoration the valoration to set
     */
    public void setValoration(int valoration) {
        this.valoration = valoration;
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

}