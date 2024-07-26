package common;

/**
 * @author micha
 *
 */
public class Profile {
	private int userId;
	private String bio;
	private String location;
	private String website;
	private String profilePicture;

	public Profile(int userId, String bio, String location, String website, String profilePicture) {
		this.userId = userId;
		this.bio = bio;
		this.location = location;
		this.website = website;
		this.profilePicture = "/resources/images/testgraphic.png";
	}

	/**
	 * @return the userId
	 */
	public int getUserId () {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId ( int userId ) {
		this.userId = userId;
	}

	/**
	 * @return the bio
	 */
	public String getBio () {
		return bio;
	}

	/**
	 * @param bio the bio to set
	 */
	public void setBio ( String bio ) {
		this.bio = bio;
	}

	/**
	 * @return the location
	 */
	public String getLocation () {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation ( String location ) {
		this.location = location;
	}

	/**
	 * @return the website
	 */
	public String getWebsite () {
		return website;
	}

	/**
	 * @param website the website to set
	 */
	public void setWebsite ( String website ) {
		this.website = website;
	}

	/**
	 * @return the profilePicture
	 */
	public String getProfilePicture () {
		return profilePicture;
	}

	/**
	 * @param profilePicture the profilePicture to set
	 */
	public void setProfilePicture ( String profilePicture ) {
		this.profilePicture = profilePicture;
	}
	
	
	
	
}
