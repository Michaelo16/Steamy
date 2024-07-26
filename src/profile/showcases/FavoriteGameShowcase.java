package profile.showcases;

/**
 * @author micha
 *
 */
public class FavoriteGameShowcase extends Showcase {
	
	 private String title;
	 private int gameId;
	private int showcaseId;
	
	
	
	
	private int hoursPlayed;
	private int achievementProgress;

	/**
	 * @param type
	 */
	public FavoriteGameShowcase ( String type, String user ) {
		super(type,user);

	}

	 public FavoriteGameShowcase(int showcaseId, int gameId, String title, int hoursPlayed, int achievementProgress) {
    
		 super("fav_game", "");
		 this.showcaseId = showcaseId;
     this.gameId = gameId;
     this.title = title;
     this.hoursPlayed = hoursPlayed;
     this.achievementProgress = achievementProgress;
 }
	
	void getData() {
		//TODO Get the data from the dataBase
	}

	/**
	 * @return the gameName_
	 */
	public String getGameName() {
		return title;
	}

	/**
	 * @param gameName_ the gameName_ to set
	 */
	public void setGameName( String gameName_ ) {
		this.title = gameName_;
	}

	/**
	 * @return the hoursPlayed_
	 */
	public int getHoursPlayed() {
		return hoursPlayed;
	}

	/**
	 * @param hoursPlayed_ the hoursPlayed_ to set
	 */
	public void setHoursPlayed ( int hoursPlayed_ ) {
		this.hoursPlayed = hoursPlayed_;
	}

	/**
	 * @return the achievements_
	 */
	public int getAchievements () {
		return achievementProgress;
	}

	/**
	 * @param achievements_ the achievements_ to set
	 */
	public void setAchievements ( int achievements_ ) {
		this.achievementProgress = achievements_;
	}

	/**
	 * @return the title
	 */
	public String getTitle () {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle ( String title ) {
		this.title = title;
	}

	/**
	 * @return the gameId
	 */
	public int getGameId () {
		return gameId;
	}

	/**
	 * @param gameId the gameId to set
	 */
	public void setGameId ( int gameId ) {
		this.gameId = gameId;
	}

	/**
	 * @return the showcaseId
	 */
	public int getShowcaseId () {
		return showcaseId;
	}

	/**
	 * @param showcaseId the showcaseId to set
	 */
	public void setShowcaseId ( int showcaseId ) {
		this.showcaseId = showcaseId;
	}

	/**
	 * @return the achievementProgress
	 */
	public int getAchievementProgress () {
		return achievementProgress;
	}

	/**
	 * @param achievementProgress the achievementProgress to set
	 */
	public void setAchievementProgress ( int achievementProgress ) {
		this.achievementProgress = achievementProgress;
	}






}
