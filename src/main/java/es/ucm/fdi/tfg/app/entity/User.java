package es.ucm.fdi.tfg.app.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User{
    @Id
    private String uuid;

    private String name;

    private String email;

    private String password;

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
    /*
    @OneToMany(mappedBy = "user")
    private List<UserFilm> userFilms;*/
}