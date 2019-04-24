package es.ucm.fdi.tfg.app.transfer;

public class TUserFilm {

	private String userUuid;
	private String filmUuid;
	private String date;
	private float rating;

	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	public String getFilmUuid() {
		return filmUuid;
	}

	public void setFilmUuid(String filmUuid) {
		this.filmUuid = filmUuid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public float getRating() {
		return rating;
	}

}
