package es.ucm.fdi.tfg.app.transfer;

import java.util.Date;
import java.util.List;

public class TPlan {
	private Long id;
	private String creatorUuid;
	private String filmUuid;
	private String title;
	private Date date;
	private String location;
	private String description;

	public String getCreatorUuid() {
		return creatorUuid;
	}

	public void setCreatorUuid(String creatorUuid) {
		this.creatorUuid = creatorUuid;
	}

	public String getFilmUuid() {
		return filmUuid;
	}

	public void setFilmUuid(String filmUuid) {
		this.filmUuid = filmUuid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
