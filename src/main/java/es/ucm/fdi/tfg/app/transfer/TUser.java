package es.ucm.fdi.tfg.app.transfer;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

public class TUser {
    private String uuid;

    private String name;

    private String email;

	private String password;
	
	private String imageURL;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImageURL(){
		return imageURL;
	}

	public void setImageURL(String imageURL){
		this.imageURL = imageURL;
	}
    
    
}
