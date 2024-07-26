package profile.showcases;

/**
 * @author micha
 *
 */
	public abstract class Showcase {

		String type_;
		String user_;
		
		public Showcase(String type, String user) {
			this.type_ = type;
			this.user_ = user;
		}
		
		abstract void getData();
		
		 public String getType() {
			return this.type_;
		}
		
		 public String getUser() {
			return user_;
		}
		
}
	
