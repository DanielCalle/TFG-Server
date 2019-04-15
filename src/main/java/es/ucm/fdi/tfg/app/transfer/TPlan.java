package es.ucm.fdi.tfg.app.transfer;

import java.util.Date;
import java.util.List;

public class TPlan {
	private Long id;
	private String creatorUuid;
	private String filmUuid;
	private Date date;
	private List<String> joinedUsers;

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
	public List<String> getJoinedUsers(){
		return joinedUsers;
	}
	public void setJoinedUsers(List<String> joinedUsers){
		this.joinedUsers = joinedUsers;
	}
	
	
	
}
