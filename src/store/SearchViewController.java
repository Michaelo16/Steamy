package store;

import java.io.IOException;
import java.util.ArrayList;

import common.DatabaseHandler;
import common.Game;
import common.MainLayoutController;
import common.SteamyApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * @author micha
 *
 */
public class SearchViewController {

  @FXML
  private HBox mainContainer_;

  @FXML
  private TextField searchBar_;

  @FXML
  private VBox searchListContainer_;

  @FXML
  private Button searchButton_;
  
	private ArrayList<GameList> gameList_;

	private MainLayoutController mainCont_;
	private StoreViewController storeCont_;
	
	private String userSearch_;
	
	
	public SearchViewController () {
		
	}

	public void loadSearchList(String search) {
		this.userSearch_ = search;
		getNameSearchList();
		for(GameList list: gameList_) {
			try {
				loadListItem(list);
			} catch ( IOException e ) {

				e.printStackTrace();
			}
		}

	}
	
	public void loadGenreList(String genre) {
		getGenreSearchList(genre);
		for(GameList list: gameList_) {
			try {
				loadListItem(list);
			} catch ( IOException e ) {

				e.printStackTrace();
			}
		}

		
	}
	
	public void getNameSearchList() {
		//TODO fake list Eventually from database
		
		ArrayList<Game> list = DatabaseHandler.searchGamesByTitle(userSearch_);
		GameList gameList = new GameList();
		for(Game game: list) {
			
			gameList.addItem(new StoreItem(game));
		}
		ArrayList<GameList> temp = new ArrayList<GameList>();;
		temp.add(gameList);
		
		this.gameList_ = temp;
	}
	
	public void getGenreSearchList(String genre) {
		ArrayList<Game> list = DatabaseHandler.getGamesByGenre(genre);
		GameList gameList = new GameList();
		for(Game game: list) {
			
			gameList.addItem(new StoreItem(game));
		}
		ArrayList<GameList> temp = new ArrayList<GameList>();;
		temp.add(gameList);
		
		this.gameList_ = temp;
		
		
	}
	
	
	
	
	private void loadListItem(  GameList gameList) throws IOException {

		VBox elementContainer;
		for(StoreItem item: gameList.getItems()) {
			FXMLLoader loader5 = new FXMLLoader();
			loader5.setLocation(getClass().getResource("/resources/storePreviews/gameListItem.fxml"));
			elementContainer = loader5.load();//Get the store Item and load its unique elements
			searchListContainer_.getChildren().add(elementContainer);
			StoreItemController itemController = loader5.getController();
			itemController.setStoreViewCont(storeCont_);
			itemController.setSearchItem();
			itemController.setStoreItem(item);
			itemController.loadElements();
		}
		
	}
	
	public void setMainCont(MainLayoutController cont) {
		this.mainCont_ = cont;
	}
	public void setStoreController(StoreViewController cont) {
		this.storeCont_ = cont;
	}
	
	public void loadStorePage(StoreItem item) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/resources/scenes/storePage.fxml"));
			StackPane storePage = loader.load();
			mainCont_.showStorePage(item);


		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}
	
	
	
	
	
	
	
	
}
