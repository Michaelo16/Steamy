package common;

/**
 * @author micha
 *
 */
public class SavedLogin {

	private String username_;
	private String password_;
	
	public SavedLogin(String user, String pass) {
		this.username_ = user;
		this.password_ = pass;
	}

	/**
	 * @return the username_
	 */
	public String getUsername_ () {
		return username_;
	}

	/**
	 * @param username_ the username_ to set
	 */
	public void setUsername_ ( String username_ ) {
		this.username_ = username_;
	}

	/**
	 * @return the password_
	 */
	public String getPassword_ () {
		return password_;
	}

	/**
	 * @param password_ the password_ to set
	 */
	public void setPassword_ ( String password_ ) {
		this.password_ = password_;
	}
	
	
	
	
	
}
