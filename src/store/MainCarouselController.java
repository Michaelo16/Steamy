package store;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import java.beans.*;
import java.util.ArrayList;

import common.Game;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * @author micha
 *
 */
public class MainCarouselController {

	@FXML
	private VBox leftArrowButton_;
	@FXML
	private VBox rightArrowButton_;
	@FXML
	private ImageView smallImg4_;
	@FXML
	private ImageView smallImg1_;

	@FXML
	private ImageView smallImg2_;

	@FXML
	private ImageView mainImg_;

	@FXML
	private ImageView smallImg3_;
	@FXML
	private Text titleText_;
	@FXML
	private Text genre_;
	private StoreViewController cont_;

	ArrayList<Game> games_;
	private Game currentGame_;
	private int currGame_ = 0;

	@FXML
	private void initialize() {



	}
	@FXML
	private void nextGame() {
		int max = games_.size() -1;
		if(currGame_ < max) {
			currGame_ = currGame_ + 1;
			currentGame_ = games_.get(currGame_);
		}
		else {
			currGame_ = 0;
			currentGame_ = games_.get(currGame_);
		}
		loadElements();
	}
	@FXML
	private void prevGame() {
		int max = games_.size() -1;
		if(currGame_ > 0) {
			currGame_ = currGame_ + -1;
			currentGame_ = games_.get(currGame_);
		}
		else {
			currGame_ = 4;
			currentGame_ = games_.get(currGame_);
		}
		loadElements();

	}

	@FXML
	public void loadStorePage() {
		StoreItem temp = new StoreItem(currentGame_);
		cont_.loadStorePage(temp);
	}

	public void loadElements() {
		smallImg1_.setImage(games_.get(currGame_).getIcon());
		smallImg2_.setImage(games_.get(currGame_).getIcon());
		smallImg3_.setImage(games_.get(currGame_).getIcon());
		smallImg4_.setImage(games_.get(currGame_).getIcon());
		mainImg_.setImage(games_.get(currGame_).getIcon());
		titleText_.setText(games_.get(currGame_).getName());
		genre_.setText(games_.get(currGame_).getGenre());

	}

	public void setGames(ArrayList<Game> gaem) {
		this.games_ = gaem;
		currentGame_ = games_.get(0);
		loadElements();

	}
	
	public void setMainCont(StoreViewController cont) {
		this.cont_ = cont;
	}

}
