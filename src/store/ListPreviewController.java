package store;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.*;

import common.Game;

/**
 * @author micha
 *
 */
public class ListPreviewController {

	@FXML 
	private TabPane listTab_;
	@FXML
	private Tab newTab_;//Tabs
	@FXML
	private Tab forYouTab_;
	@FXML
	private Tab topSellTab_;
	
	@FXML
	private Tab friendsTab_;
	

	@FXML
	private Text previewTitle_;
	
	@FXML 
	private VBox previewContainer_;

	
	
	private SidePreviewController sideController_;
	private StoreViewController storeController_;
	
	


	public ListPreviewController () {

	}

	//Gets the games that need to be displayed in the current tab
	public ArrayList<Game>getGames() {

		return null;
	}
	@FXML
	public void initialize() {
		//tabs_ = new ArrayList<Tab>();
		//tabs_.add(newTab_);tabs_.add(topSellTab_);tabs_.add(upcomingTab_);tabs_.add(specialsTab_);tabs_.add(forYouTab_);
		
	}

	public void updatePreview(StoreItem item) {
	    	try {
	  			FXMLLoader loader = new FXMLLoader();
	  			loader.setLocation(getClass().getResource("/resources/storePreviews/listSidePreview.fxml"));
	  			VBox previewRoot = loader.load();
	  			SidePreviewController cont = loader.getController();
	  			cont.setItem(item);
	  			cont.loadElements(item);
	  			previewContainer_.getChildren().add(previewRoot);
	  			
	  		} catch ( IOException e ) {
	  			e.printStackTrace();
	  		}
	  	}
	public void emptyPreview() {
		previewContainer_.getChildren().clear();
	}
	

	public void fillLists(ArrayList<GameList> lists) throws IOException {

		StoreItemController itemController = null;
		for(GameList gameList: lists) {
			switch (gameList.getType()) {
			case "new":
				loadListItem(newTab_, itemController, gameList);
			case "top_sell":
				loadListItem(topSellTab_, itemController, gameList);
			case "pop_friend":
				loadListItem(friendsTab_, itemController, gameList);
			case "for_you":
				loadListItem(forYouTab_, itemController, gameList);
			}
		}
	}

	private void loadListItem(Tab container, StoreItemController itemController, GameList gameList) throws IOException {
		VBox listContainer4 = new VBox();
		VBox elementContainer4;
		for(StoreItem item: gameList.getItems()) {
			FXMLLoader loader5 = new FXMLLoader();
			loader5.setLocation(getClass().getResource("/resources/storePreviews/gameListItem.fxml"));
			elementContainer4 = loader5.load();//Get the store Item and load its unique elements
			listContainer4.getChildren().add(elementContainer4);
			itemController = loader5.getController();
			itemController.setStoreItem(item);
			itemController.setMainCont(this);
			itemController.setStoreViewCont(storeController_);
			itemController.loadElements();
		}
		container.setContent(listContainer4);
		
		
	}
	
	
	public void setStoreViewController(StoreViewController cont) {
		this.storeController_ = cont;
	}

	public void setSideController(SidePreviewController cont) {
		this.sideController_ = cont;
	}






}
