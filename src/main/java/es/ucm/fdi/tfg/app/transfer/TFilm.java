package es.ucm.fdi.tfg.app.transfer;

public class TFilm {

	private String uuid;
	private String name;
	private String director;
	private String trailerURL;
	private String infoURL;
    private String synopsis;
	private String imageURL;
	private String genre;
	private int duration;
	private double rating;
	private String country;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getTrailerURL() {
		return trailerURL;
	}

	public void setTrailerURL(String trailerURL) {
		this.trailerURL = trailerURL;
	}

	public String getInfoURL() {
		return infoURL;
	}

	public void setInfoURL(String infoURL) {
		this.infoURL = infoURL;
	}

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
    
    
}
