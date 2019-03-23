package es.ucm.fdi.tfg.app.transfer;

import java.util.Date;

public class TUserFilm {
	
    private String userUuid;
    private String filmUuid;
    private Date date;
    
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
    
}
