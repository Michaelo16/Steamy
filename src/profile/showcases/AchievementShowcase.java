package profile.showcases;

/**
 * @author micha
 *
 */
public class AchievementShowcase extends Showcase {

	private int showcaseId;
  private int achievementId;
  private String name;
  private int totalAchievements;
	
	/**
	 * @param type
	 */
	public AchievementShowcase ( String type, String user ) {
		super(type, user);
		
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
	 * @return the achievementId
	 */
	public int getAchievementId () {
		return achievementId;
	}

	/**
	 * @param achievementId the achievementId to set
	 */
	public void setAchievementId ( int achievementId ) {
		this.achievementId = achievementId;
	}

	/**
	 * @return the totalAchievements
	 */
	public int getTotalAchievements () {
		return totalAchievements;
	}

	/**
	 * @param totalAchievements the totalAchievements to set
	 */
	public void setTotalAchievements ( int totalAchievements ) {
		this.totalAchievements = totalAchievements;
	}

	public AchievementShowcase(int showcaseId, int achievementId, String name, int totalAchievements) {
    
		 super("achievement", "");
		 this.showcaseId = showcaseId;
     this.achievementId = achievementId;
     this.name = name;
     this.totalAchievements = totalAchievements;
 }
	
	@Override
	void getData () {
		// TODO Auto-generated method stub
		
	}

}
