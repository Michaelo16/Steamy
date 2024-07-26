package library;

import java.util.Optional;

import common.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * @author micha
 *
 */



public class GamePageController {

	@FXML
	private ImageView banner_;
	@FXML
	private Button deleteButton_;
	@FXML
	private Text title_;
	
	private LibraryViewController mainCont_;
	private Game game_;
	
	
	
	@FXML
	private void initialize() {
		this.banner_.setImage(new Image("/resources/images/testgraphic.png"));	
		
	}
	
	public GamePageController () {
		
	}
	
	@FXML
	private void deleteGame() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Are you sure you want to delete this game?");
		Optional<ButtonType> result = alert.showAndWait();
		 if (result.isPresent() && result.get() == ButtonType.OK) {
	     mainCont_.deleteGame(game_);
	     alert.close();
	 }
		
		
	}

	public void setMainCon(LibraryViewController cont) {
		this.mainCont_ = cont;
	}
	public void setGame(Game game) {
		this.game_ = game;
		this.title_.setText(game.getName());
	}
	
}
