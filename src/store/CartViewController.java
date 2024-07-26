package store;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import common.DatabaseHandler;
import common.Game;
import common.MainLayoutController;
import common.SteamyApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * @author micha
 *
 */
public class CartViewController {
	@FXML
	private HBox mainContainer_;
	@FXML
	private VBox cartListContainer_;
	@FXML
	private Text priceText_;
	private double price_;
	@FXML
	private Button checkoutButton_;

	private ArrayList<GameList> gameLists_;

	private MainLayoutController mainCont_;

	@FXML
	private void intiialize()	{
		loadCartList();
	}

	public CartViewController () {


	}

	public void loadCartList() {
		getGameLists();
		for(GameList list: gameLists_) {
			try {
				loadListItem(list);
			} catch ( IOException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public void checkout() {
		if(gameLists_.isEmpty() == false) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("Would you like to checkout?");
			Optional<ButtonType> result = alert.showAndWait();
			 if (result.isPresent() && result.get() == ButtonType.OK) {
				 DatabaseHandler.purchaseCart(SteamyApp.currUser_);
				 mainCont_.switchToCart();
			 }
			
		}
		
	}


	private void loadListItem(  GameList gameList) throws IOException {

		VBox elementContainer;
		for(StoreItem item: gameList.getItems()) {
			FXMLLoader loader5 = new FXMLLoader();
			loader5.setLocation(getClass().getResource("/resources/storePreviews/gameListItem.fxml"));
			elementContainer = loader5.load();//Get the store Item and load its unique elements
			cartListContainer_.getChildren().add(elementContainer);
			StoreItemController itemController = loader5.getController();
			itemController.setCartController(this);
			itemController.setStoreItem(item);
			itemController.setCartItem();
			itemController.loadElements();
		}

		priceText_.setText(Double.toString(price_));
		
	}

	public void getGameLists() {
		//TODO fake list Eventually from database
		price_ = 0;
		ArrayList<Game> list = DatabaseHandler.viewCart(DatabaseHandler.getUserIdByUsername(SteamyApp.username_));
		GameList gameList = new GameList();
		for(Game game: list) {
			gameList.addItem(new StoreItem(game));
			price_ = price_ + game.getPrice();
		}
		ArrayList<GameList> temp = new ArrayList<GameList>();;
		temp.add(gameList);
		
		this.gameLists_ = temp;
	}

	public void setMainCont(MainLayoutController cont) {
		this.mainCont_ = cont;
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
