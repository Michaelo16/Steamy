package library;

import common.Game;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.image.Image;

/**
 * @author micha
 *
 */
public class ListItemController {

	@FXML 
	private HBox listItemBox_;
	@FXML
	private AnchorPane mainPane_;
	@FXML
	private Text title_;
	@FXML
	private ImageView gameIcon_;
	
	private LibraryViewController mainController_;
	
	private Game game_;
	
	public void initialize() {
		
	}
	
	
	public void loadListItem(Game game) {
		
		title_.setText(game.getName());
		gameIcon_.setImage(game.getIcon());
		 listItemBox_.setOnMouseEntered(event -> listItemBox_.setStyle("-fx-background-color:#798091; "));
     listItemBox_.setOnMouseExited(event -> listItemBox_.setStyle("-fx-background-color:#1a1c21; "));
		listItemBox_.setOnMouseClicked(event -> mainController_.loadContent(game_));
	}
	
	@FXML
	private void accentuate() {
		listItemBox_.setStyle("-fx-background-color: #1a1c21;");
	}
	@FXML
	private void darken() {
		listItemBox_.setStyle("-fx-background-color: #798091;");
	}
	
	public void setGame(Game game) {
		this.game_ = game;
	}
	
	public void setMainViewController(LibraryViewController cont) {
		mainController_ = cont;
	}
	
}
