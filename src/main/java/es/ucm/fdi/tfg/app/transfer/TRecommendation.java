package es.ucm.fdi.tfg.app.transfer;

public class TRecommendation {

	private Long id;
	private Long userId;
	private Long filmId;
	private float rating;

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
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the filmId
	 */
	public Long getFilmId() {
		return filmId;
	}

	/**
	 * @param filmId the filmId to set
	 */
	public void setFilmId(Long filmId) {
		this.filmId = filmId;
	}

	/**
	 * @return the rating
	 */
	public float getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(float rating) {
		this.rating = rating;
	}
}
