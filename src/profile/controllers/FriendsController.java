package profile.controllers;

import java.io.IOException;
import java.util.ArrayList;

import common.DatabaseHandler;
import common.Friend;
import common.MainLayoutController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * @author micha
 *
 */
public class FriendsController {

	@FXML
	private Text numFriends_;

	@FXML
	private VBox friendContainer_;

	private String user_;
	private ArrayList<Friend> friends_;
	private MainLayoutController mainCont_;

	public void initFriends() throws IOException {
		friends_ = DatabaseHandler.getFriends(DatabaseHandler.getUserIdByUsername(user_));
		//loads the users first 4 friends
		for(int i = 0; i < 4; i++) {
			if(i > friends_.size()- 1)
				return;
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/resources/profileShowcases/frienditem.fxml"));
			VBox friendItem = loader.load();
			FriendItemController cont = loader.getController();
			cont.setMainCont(mainCont_);
			cont.setFriend(friends_.get(i));
			cont.loadElements();
			friendContainer_.getChildren().add(friendItem);
			if(i > friends_.size()-1)
				return;
		}




	}

	public void setUser(String user) {
		this.user_ = user;
	}
	public void setMainCont(MainLayoutController cont) {
		this.mainCont_ = cont;
	}	

}
