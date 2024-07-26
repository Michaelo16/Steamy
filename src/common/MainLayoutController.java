package common;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import library.LibraryViewController;
import profile.controllers.ProfileViewController;
import store.StoreItem;
import store.StoreViewController;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;
import store.*;

import java.io.IOException;

/**
 * @author micha
 *
 */
public class MainLayoutController {

	@FXML
	private VBox StoreList_;
	@FXML
	private Button communityButton_;
	@FXML
	private MenuItem exitButton_;
	@FXML
	private Button friendsButton_;
	@FXML
	private MenuItem helpButton_;
	@FXML
	private Button homeButton_;
	@FXML
	private Button libraryButton_;
	@FXML
	private MenuItem loginMenuButton_;
	@FXML
	private MenuItem logoutButton_;
	@FXML
	private ButtonBar navigationBar_;
	@FXML
	private Button profileButton_;
	@FXML
	private MenuItem settingsButton_;
	@FXML
	private Menu steamyMenu_;
	@FXML
	private Button storeButton_;
	@FXML
	private MenuItem switchAccountsButton_;
	@FXML
	private Button cartButton_;

	@FXML
	private AnchorPane mainContent_;
	@FXML
	private BorderPane root_;

	private String currView_ = "";

	private StoreViewController storeController_;
	private LibraryViewController libraryController_;
	private ProfileViewController profileController_;

	@FXML
	private void initialize() {
		// Optionally load an initial view
		showStore();

	}

	@FXML
	public void showStore() {
		if(!currView_.equals("storeView.fxml")) {
			FXMLLoader loader = loadView("storeView.fxml");
			StoreViewController cont = loader.getController();
			this.storeController_ = cont;
			cont.setMainLayoutCont(this);
		}
	}

	@FXML
	public void showProfile() {
		if(!currView_.equals("profileView.fxml"))
			loadView("profileView.fxml");
			profileController_.setMainCont(this);
			profileController_.initProfile(SteamyApp.username_);
	}
	
	public void showProfile(String user) {
		loadView("profileView.fxml");
		profileController_.setMainCont(this);
		profileController_.initProfile(user);
	}

	@FXML
	public void showLibrary() {
		if(!currView_.equals("libraryView1.fxml")) {
			loadView("libraryView.fxml");
			this.libraryController_.setMainController(this);
		}
		
	}

	@FXML
	public void showCommunity() {
		loadView("CommunityView.fxml");
	}


	public void showSearchList(StoreViewController controller) {
		FXMLLoader loader = loadView("storeSearch.fxml");
		SearchViewController cont = loader.getController();
		cont.setMainCont(this);
		cont.setStoreController(controller);
		cont.loadSearchList(storeController_.getSearch());
		
	}
	
	public void showGenreSearch(StoreViewController controller,String type) {
		FXMLLoader loader = loadView("storeSearch.fxml");
		SearchViewController cont = loader.getController();
		cont.setMainCont(this);
		cont.setStoreController(controller);
		cont.loadGenreList(type);
		
		
	}
	
	public void showStorePage(StoreItem item) {
		FXMLLoader loader = loadView("storePage.fxml");

		StorePageController cont = loader.getController();
		cont.setMainLayoutCont(this);
		cont.setStoreItem(item);
		cont.loadElements();

	}
	@FXML
	public void switchToCart() {
		FXMLLoader loader = loadView("cartView.fxml");
		
		CartViewController cont = loader.getController();
		cont.setMainCont(this);
		cont.loadCartList();

	}
	private FXMLLoader loadView(String fxmlFile) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/scenes/" + fxmlFile));
 
			Pane view = loader.load();
			currView_ = fxmlFile;
			if(loader.getController() instanceof LibraryViewController) {//This is a messy solution but it works and I dont see it causing problems
				this.libraryController_ = loader.getController();
			}
			else if(loader.getController() instanceof ProfileViewController) {
				this.profileController_ = loader.getController();
			}
			
			
			AnchorPane.setTopAnchor(view, 0.0);
			AnchorPane.setBottomAnchor(view, 0.0);
			AnchorPane.setLeftAnchor(view, 0.0);
			AnchorPane.setRightAnchor(view, 0.0);
			root_.setMinHeight(700);
			root_.setMinWidth(1100);

			mainContent_.getChildren().setAll(view);
			
			return loader;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

 
	
}

