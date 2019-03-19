package es.ucm.fdi.tfg.app.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UserApp{
    @Id
    private String uuid;

    private String name;

    private String email;

    private String password;
    
    @JsonIgnore
    @OneToMany(mappedBy = "creator", fetch=FetchType.LAZY) 
    private List<Plan> createdPlans;
    
    @JsonIgnore
    @ManyToMany
    private List<Plan> joinedPlans;
    
    @JsonIgnore
    @OneToMany(mappedBy = "userApp", fetch=FetchType.LAZY) 
    private List<UserFilm> userFilms;
    
    @JsonIgnore
    @OneToMany(mappedBy = "requester", fetch=FetchType.LAZY)
    private List<Friendship> friendRequests;

    @JsonIgnore
    @OneToMany(mappedBy = "friend", fetch=FetchType.LAZY) 
    private List<Friendship> friends;

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
     * @return the email
     */
	public String getEmail() {
		return email;
	}

    /**
     * @param name the email to set
     */
	public void setEmail(String email) {
		this.email = email;
	}

    /**
     * @return the password
     */
	public String getPassword() {
		return password;
	}

    /**
     * @param name the password to set
     */
	public void setPassword(String password) {
		this.password = password;
    }

	public List<UserFilm> getUserFilms() {
		return userFilms;
	}

	public List<Friendship> getFriendRequests() {
		return friendRequests;
	}

	public List<Friendship> getFriends() {
		return friends;
	}

	public List<Plan> getCreatedPlans() {
		return createdPlans;
	}

	public void setCreatedPlans(List<Plan> createdPlans) {
		this.createdPlans = createdPlans;
	}

	public List<Plan> getJoinedPlans() {
		return joinedPlans;
	}

	public void setJoinedPlans(List<Plan> joinedPlans) {
		this.joinedPlans = joinedPlans;
	}

	public void setUserFilms(List<UserFilm> userFilms) {
		this.userFilms = userFilms;
	}

	public void setFriendRequests(List<Friendship> friendRequests) {
		this.friendRequests = friendRequests;
	}

	public void setFriends(List<Friendship> friends) {
		this.friends = friends;
	}
	
	
    
}