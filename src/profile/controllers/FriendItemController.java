package profile.controllers;

import common.Friend;
import common.MainLayoutController;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * @author micha
 *
 */
public class FriendItemController {

	@FXML
	private VBox mainContainer_;

	@FXML
	private ImageView profileIcon_;

	@FXML
	private Text username_;

	private Friend friend_;
	private MainLayoutController mainCont_;

	
	
	public FriendItemController () {

	}

	public void loadElements(){
		username_.setText(friend_.getFriendName());
		profileIcon_.setImage(new Image(friend_.getProfileIcon()));
	}
	
	@FXML
	public void goToProfile() {
		mainCont_.showProfile(friend_.getFriendName());
	}
	
	/**
	 * @return the mainContainer_
	 */
	public VBox getMainContainer () {
		return mainContainer_;
	}

	/**
	 * @param mainContainer_ the mainContainer_ to set
	 */
	public void setMainContainer ( VBox mainContainer_ ) {
		this.mainContainer_ = mainContainer_;
	}

	/**
	 * @return the profileIcon_
	 */
	public ImageView getProfileIcon () {
		return profileIcon_;
	}

	/**
	 * @param profileIcon_ the profileIcon_ to set
	 */
	public void setProfileIcon ( ImageView profileIcon_ ) {
		this.profileIcon_ = profileIcon_;
	}

	/**
	 * @return the username_
	 */
	public Text getUsername () {
		return username_;
	}

	/**
	 * @param username_ the username_ to set
	 */
	public void setUsername ( Text username_ ) {
		this.username_ = username_;
	}

	/**
	 * @return the friend_
	 */
	public Friend getFriend () {
		return friend_;
	}

	/**
	 * @param friend_ the friend_ to set
	 */
	public void setFriend ( Friend friend_ ) {
		this.friend_ = friend_;
	}

	public void setMainCont(MainLayoutController cont) {
		this.mainCont_ = cont;
	}	

}
