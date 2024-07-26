package library;

import java.io.IOException;
import java.util.ArrayList;

import common.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * @author micha
 *
 */
public class GameListController {

	private LibraryViewController mainViewController_;

	@FXML
	private VBox listBox_;


	public GameListController () {


	}


	public void loadGames(ArrayList<Game> list) {

		for(Game game: list) {
			try {
				FXMLLoader loader = new FXMLLoader();
				AnchorPane listGameView = null;
				loader.setLocation(getClass().getResource("/resources/library/gameListItem.fxml"));
				listGameView = loader.load();
				ListItemController controller = loader.getController();
				controller.loadListItem(game);
				controller.setGame(game);

				controller.setMainViewController(mainViewController_);
				listBox_.getChildren().add(listGameView);

			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}

	}


	public void loadGames(ArrayList<Game> list, boolean favorites) {

		if(favorites) {
			for(Game game: list) {
				try {
					if(game.isFavorite() ) {
						FXMLLoader loader = new FXMLLoader();
						AnchorPane listGameView = null;
						loader.setLocation(getClass().getResource("/resources/library/gameListItem.fxml"));
						listGameView = loader.load();
						ListItemController controller = loader.getController();
						controller.loadListItem(game);
						controller.setGame(game);
						controller.setMainViewController(mainViewController_);
						listBox_.getChildren().add(listGameView);
					}
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		else {
			for(Game game: list) {
				try {
					if(!game.isFavorite() ) {
						FXMLLoader loader = new FXMLLoader();
						AnchorPane listGameView = null;
						loader.setLocation(getClass().getResource("/resources/library/gameListItem.fxml"));
						listGameView = loader.load();
						ListItemController controller = loader.getController();
						controller.loadListItem(game);
						controller.setGame(game);
						controller.setMainViewController(mainViewController_);
						listBox_.getChildren().add(listGameView);
					}
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
			
			
			
		}

	}

	public void setMainController(LibraryViewController cont) {
		this.mainViewController_ = cont;
	}
	public LibraryViewController getMainCont() {
		return mainViewController_;
	}


}
