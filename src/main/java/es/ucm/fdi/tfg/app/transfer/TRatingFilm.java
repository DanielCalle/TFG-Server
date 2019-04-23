package es.ucm.fdi.tfg.app.transfer;

public class TRatingFilm {

	private String userUuid;
	private String filmUuid;
	private float rating;
	
	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	public String getUserUuid() {
		return userUuid;
	}
	
	public void setFilmUuid(String filmUuid) {
		this.filmUuid = filmUuid;
	}

	public String getFilmUuid() {
		return filmUuid;
	}

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getRating() {
        return rating;
    }
	
	
}
