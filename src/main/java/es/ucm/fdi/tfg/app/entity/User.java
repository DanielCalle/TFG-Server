package es.ucm.fdi.tfg.app.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_app")
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
    private String name;

	@Column(unique = true)
    private String email;

	private String password;
	
	private String imageURL;
    
    @JsonIgnore
    @OneToMany(mappedBy = "creator", fetch=FetchType.LAZY) 
    private List<Plan> createdPlans;
    
    @JsonIgnore
    @ManyToMany
    private List<Plan> joinedPlans;
    
    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch=FetchType.LAZY) 
    private List<UserFilm> userFilms;
    
    @JsonIgnore
    @OneToMany(mappedBy = "requester", fetch=FetchType.LAZY)
    private List<Friendship> friendRequests;

    @JsonIgnore
    @OneToMany(mappedBy = "friend", fetch=FetchType.LAZY) 
    private List<Friendship> friends;

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
	 * @param email the email to set
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
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the createdPlans
	 */
	public List<Plan> getCreatedPlans() {
		return createdPlans;
	}

	/**
	 * @param createdPlans the createdPlans to set
	 */
	public void setCreatedPlans(List<Plan> createdPlans) {
		this.createdPlans = createdPlans;
	}

	/**
	 * @return the joinedPlans
	 */
	public List<Plan> getJoinedPlans() {
		return joinedPlans;
	}

	/**
	 * @param joinedPlans the joinedPlans to set
	 */
	public void setJoinedPlans(List<Plan> joinedPlans) {
		this.joinedPlans = joinedPlans;
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
	 * @return the friendRequests
	 */
	public List<Friendship> getFriendRequests() {
		return friendRequests;
	}

	/**
	 * @param friendRequests the friendRequests to set
	 */
	public void setFriendRequests(List<Friendship> friendRequests) {
		this.friendRequests = friendRequests;
	}

	/**
	 * @return the friends
	 */
	public List<Friendship> getFriends() {
		return friends;
	}

	/**
	 * @param friends the friends to set
	 */
	public void setFriends(List<Friendship> friends) {
		this.friends = friends;
	}
}