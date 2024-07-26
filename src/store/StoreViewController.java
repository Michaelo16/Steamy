package store;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import common.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.*;

import javafx.scene.layout.VBox;

public class StoreViewController {
	@FXML
	private ScrollPane scrollPane_;
	@FXML
	private AnchorPane anchorPane_;
	@FXML
	private HBox mainCarouselBox_;
	@FXML
	private HBox listViewBox_;

	@FXML
	private Tab newTab_;//Tabs
	@FXML
	private Tab forYouTab_;
	@FXML
	private Tab topSellTab_;
	@FXML
	private Tab upcomingTab_;
	@FXML
	private Tab specialsTab_;
	@FXML
	private TextField searchBar_;
	@FXML
	private Button searchButton_;
	@FXML
	private MenuItem Action;
	@FXML
	private MenuItem RPG;
	@FXML
	private MenuItem Adventure;

	private ArrayList<Tab> tabs_;

	private ListPreviewController mainPreviewController_;
	private MainLayoutController mainLayoutController_;


	private TabPane gameListTab_;

	private ArrayList<GameList> gameLists_;
	private ArrayList<Game> featuredList_;

	@FXML
	private void initialize() {
		scrollPane_.viewportBoundsProperty().addListener((observable, oldValue, newValue) -> {
			anchorPane_.setPrefWidth(newValue.getWidth());
		});

		//Main Display View
		getGameLists();
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/resources/storePreviews/MainCarouselPreview.fxml"));
			AnchorPane carousel = loader.load();
			MainCarouselController cont = loader.getController();
			cont.setMainCont(this);
			cont.setGames(featuredList_);
			mainCarouselBox_.getChildren().add(carousel);	
		}
		catch(IOException e) {
			e.printStackTrace();
		}

		//Load different game lists
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/resources/storePreviews/gameListPreview.fxml"));
			AnchorPane list = loader.load();
			mainPreviewController_ = loader.getController();
			mainPreviewController_.setStoreViewController(this);
			mainPreviewController_.fillLists(gameLists_);
			listViewBox_.getChildren().add(list);	
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}


	@FXML
	public void searchByGenre(ActionEvent event) {
		MenuItem genre = (MenuItem) event.getSource();
		String genreType = genre.getText();
		showGenreSearch(genreType);
	}

	public void showGenreSearch(String genre){
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/resources/scenes/storeSearch.fxml"));
			mainLayoutController_.showGenreSearch(this, genre);
	}
	
	
	public void showSearchList() {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/resources/scenes/storeSearch.fxml"));
			mainLayoutController_.showSearchList(this);
	}


	public void loadStorePage(StoreItem item) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/resources/scenes/storePage.fxml"));
			StackPane storePage = loader.load();
			StorePageController cont = loader.getController();
			cont.setStoreItem(item);
			cont.loadElements();
			mainLayoutController_.showStorePage(item);

		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}


	public String getSearch() {
		return searchBar_.getText(); 
	}

	public void getGameLists() {
		ArrayList<GameList> lists = new ArrayList<>();

		//For You games
		ArrayList<Game> youGames = DatabaseHandler.getRecommendedGames(5);
		GameList forYou = new GameList();
		forYou.setType("for_you");
		for(Game game: youGames) {
			StoreItem temp = new StoreItem(game);
			forYou.addItem(temp);
		}
		lists.add(forYou);

		//New Games
		ArrayList<Game> newGames = DatabaseHandler.getRecentGames(5);
		GameList newList = new GameList();
		newList.setType("new");
		for(Game game: newGames) {
			StoreItem temp = new StoreItem(game);
			newList.addItem(temp);
		}
		lists.add(newList);

		//Popular Games
		ArrayList<Game> popGames = DatabaseHandler.getTopSellingGames(5);
		GameList popList = new GameList();
		popList.setType("top_sell");
		for(Game game: popGames) {
			StoreItem temp = new StoreItem(game);
			popList.addItem(temp);
		}
		lists.add(popList);

		//Friend Games
		ArrayList<Game> friendGames = DatabaseHandler.getGamesPopularWithFriends(5);
		GameList friendList = new GameList();
		friendList.setType("pop_friend");
		for(Game game: friendGames) {
			StoreItem temp = new StoreItem(game);
			friendList.addItem(temp);
		}
		lists.add(friendList);
		this.gameLists_ = lists;

		ArrayList<Game> featuredList = DatabaseHandler.getFeaturedGames(5);
		System.out.println(featuredList.size());
		this.featuredList_ = featuredList;

	}



	public void setMainLayoutCont(MainLayoutController cont) {
		this.mainLayoutController_ = cont;
	}

}