package library;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

import common.*;
import javafx.fxml.*;

/**
 * @author micha
 *
 */
public class LibraryViewController {

	@FXML
	private ToggleButton favoriteButton_;
	@FXML
	private ToggleButton regButton_;
	@FXML
	private VBox favListBox_;
	@FXML
	private VBox regListBox_;
	@FXML
	private  AnchorPane gameContent_;
	
	@FXML
	AnchorPane root_;
	MainLayoutController mainCont_;
	
	GameListController favController_;
	GameListController regController_;
	

	private AnchorPane favoritesList;
	private AnchorPane regList;
	private Game selected_;
	
	private String test = "test";
	
	public String getTest() {
		return test;
	}
	
	
	@FXML
	private void initialize() {
		
     loadFavoritesList();
     loadUnorganizedList();
		 favListBox_.setPrefHeight(300);
		 favoriteButton_.requestFocus();
		 regButton_.requestFocus();
		 favoriteButton_.setSelected(true);
		 regButton_.setSelected(true);
	}
	
	public  void loadContent(Game game) {
		try {
			selected_ = game;
			FXMLLoader loader = new FXMLLoader(LibraryViewController.class.getResource("/resources/library/libraryGamePage.fxml"));
			AnchorPane page = loader.load();
			GamePageController controller = loader.getController();
			controller.setMainCon(this);
			controller.setGame(game);
			gameContent_.getChildren().add(page);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	private ArrayList<Game> getGames(){
		return DatabaseHandler.getUserLibrary(SteamyApp.currUser_);
	}
	
	
	 @FXML
   private void toggleFavorites() {
       if (favoriteButton_.isSelected()) {
      	 	 favListBox_.setPrefHeight(300);
           loadFavoritesList();
       } else {
           favListBox_.setVisible(false);
           favListBox_.setManaged(false);
           favListBox_.getChildren().clear();
           favListBox_.setPrefHeight(0);
       }
   }
	
	
	 private void loadFavoritesList() {
     try {
         
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/library/gameList.fxml"));
             favoritesList = loader.load();
             GameListController controller = loader.getController();
             controller.setMainController(this);
             controller.loadGames(getGames(), true);
             
             
             setFavController_(controller);
         
         favListBox_.getChildren().setAll(favoritesList);
         favListBox_.setVisible(true);
         favListBox_.setManaged(true);
     } catch (IOException e) {
         e.printStackTrace();
     }
 }
	 
	 @FXML
   private void toggleRegular() {
       if (regButton_.isSelected()) {
           loadUnorganizedList();
       } else {
           regListBox_.setVisible(false);
           regListBox_.setManaged(false);
           regListBox_.getChildren().clear();
       }
   }
	 
	 private void loadUnorganizedList() {
     try {
         
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/library/gameList.fxml"));
             regList = loader.load();
             GameListController controller = loader.getController();
             controller.setMainController(this);
             controller.loadGames(getGames(), false);
             setRegController_(controller);
         
         regListBox_.getChildren().setAll(regList);
         regListBox_.setVisible(true);
         regListBox_.setManaged(true);
     } catch (IOException e) {
         e.printStackTrace();
     }
 }

	 public void deleteGame(Game game){
		 DatabaseHandler.deleteFromLibrary(SteamyApp.currUser_,game.getId());
		 mainCont_.showLibrary();
		 
	 }
	 
	 
	 
	 /**
		 * @return the favController_
		 */
		public GameListController getFavController_ () {
			return favController_;
		}

		/**
		 * @param favController_ the favController_ to set
		 */
		public void setFavController_ ( GameListController favController_ ) {
			this.favController_ = favController_;
		}

		/**
		 * @return the regController_
		 */
		public GameListController getRegController_ () {
			return regController_;
		}

		/**
		 * @param regController_ the regController_ to set
		 */
		public void setRegController_ ( GameListController regController_ ) {
			this.regController_ = regController_;
		}

	 public void setMainController(MainLayoutController cont) {
		 this.mainCont_ = cont;
	 }
	 
	 
	 
	 
	 
	 
	 
	
	
	/**
	 * 
	 */
	public LibraryViewController () {

	}
	
	

}
