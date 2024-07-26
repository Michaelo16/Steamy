package common;

/**
 * @author micha
 *
 */
public class Friend {

	 private int friendId;
   private String friendName;
   private String profileIcon;
   private String status_;

   public Friend(int friendId, String friendName, String status) {
       this.friendId = friendId;
       this.friendName = friendName;
       this.status_ = status;
       this.profileIcon = "/resources/images/testgraphic.png";
   }

	/**
	 * @return the friendId
	 */
	public int getFriendId () {
		return friendId;
	}

	/**
	 * @param friendId the friendId to set
	 */
	public void setFriendId ( int friendId ) {
		this.friendId = friendId;
	}

	/**
	 * @return the friendName
	 */
	public String getFriendName () {
		return friendName;
	}

	/**
	 * @param friendName the friendName to set
	 */
	public void setFriendName ( String friendName ) {
		this.friendName = friendName;
	}

	/**
	 * @return the profileIcon
	 */
	public String getProfileIcon () {
		return profileIcon;
	}

	/**
	 * @param profileIcon the profileIcon to set
	 */
	public void setProfileIcon ( String profileIcon ) {
		this.profileIcon = profileIcon;
	}
	
   
   

}
