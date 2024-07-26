package profile.controllers;
import java.io.IOException;
import java.util.List;

import common.DatabaseHandler;
import common.MainLayoutController;
import common.Profile;
import profile.showcases.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

/**
 * @author micha
 *
 */



public class ProfileViewController {

	@FXML
	private ImageView profilePic_;
	@FXML
	private TextArea bioArea_;

	private StackPane friends_;
	@FXML
	private AnchorPane anchorPane_;
	@FXML
	private Text username_;
	@FXML 
	private Text memberSince_;
	@FXML
	private ScrollPane scrollPane_;
	@FXML
	private VBox showcasesContainer_;
	@FXML
	private HBox friendBox_;
	private Profile profile_;
	private String user_;
	private MainLayoutController mainCont_;

	@FXML
	private void initialize() {
		bioArea_.setStyle("-fx-control-inner-background: #1b2838;");
		scrollPane_.viewportBoundsProperty().addListener((observable, oldValue, newValue) -> {
			anchorPane_.setPrefWidth(newValue.getWidth());
		});

	}

	public void initProfile(String user) {
		this.user_ = user;
		profile_ = DatabaseHandler.getProfile(user);
		bioArea_.setText(profile_.getBio());
		memberSince_.setText(profile_.getWebsite());
		username_.setText(user);
		profilePic_.setImage(new Image(profile_.getProfilePicture()));
		loadShowcases(getShowcases());
	}

	public ArrayList<Showcase> getShowcases(){
		ArrayList<Showcase> list = new ArrayList<>();
		FavoriteGameShowcase showcase = new FavoriteGameShowcase("fav_game", "mike");
		FavoriteGameShowcase showcase1 = new FavoriteGameShowcase("achieve_display", "mike");
		list.add(showcase);
		list.add(showcase1);
		return list;
	}



	public void loadShowcases(ArrayList<Showcase> showcases) {
		for (Showcase showcase : showcases) {
			try {
				FXMLLoader loader = new FXMLLoader();
				AnchorPane showcaseView = null;

				switch (showcase.getType()) {
				case "fav_game":
					loader.setLocation(getClass().getResource("/resources/profileShowcases/favoritegame.fxml"));
					showcaseView = loader.load();
					FavoriteGameController gameController = loader.getController();
					break;
				case "achieve_display":
					loader.setLocation(getClass().getResource("/resources/profileShowcases/achievement.fxml"));
					showcaseView = loader.load();
					AchievementController achievementController = loader.getController();
					break;
					//case "favoriteReview":
					//					loader.setLocation(getClass().getResource("FavoriteReviewShowcase.fxml"));
					//					showcaseView = loader.load();
					//					ReviewController reviewController = loader.getController();
					//					reviewController.setReviewText(((FavoriteReviewShowcase) showcase).getReviewText());
					//					break;
					//				case "favoriteScreenshot":
					//					loader.setLocation(getClass().getResource("FavoriteScreenshotShowcase.fxml"));
					//					showcaseView = loader.load();
					//					ScreenshotController screenshotController = loader.getController();
					//					screenshotController.setScreenshotUrl(((FavoriteScreenshot) showcase).getScreenshotUrl());
					//					break;
					//				case "recentActivity":
					//					loader.setLocation(getClass().getResource("RecentActivityShowcase.fxml"));
					//					showcaseView = loader.load();
					//					RecentActivityController activityController = loader.getController();
					//					activityController.setActivityDescription(((RecentActivity) showcase).getActivityDescription());
					//					activityController.setTimestamp(((RecentActivity) showcase).getTimestamp());
					//					break;
				}
				if (showcaseView != null) {
					showcasesContainer_.getChildren().add(showcaseView);
					Region sep = new Region();
					sep.setPrefHeight(20);
					showcasesContainer_.getChildren().add(sep);
				}
			}

			catch (IOException e) {
				e.printStackTrace();
			}
		}
		//Add Friend Shwocase
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/resources/profileShowcases/friends.fxml"));
			AnchorPane friend = loader.load();
			friendBox_.getChildren().add(friend);
			FriendsController cont = loader.getController();
			cont.setMainCont(mainCont_);
			cont.setUser(user_);
			cont.initFriends();
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}
	public void setMainCont(MainLayoutController cont) {
		this.mainCont_ = cont;
	}	

}



