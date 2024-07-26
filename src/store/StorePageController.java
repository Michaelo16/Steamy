package store;

import java.util.Optional;

import common.DatabaseHandler;
import common.MainLayoutController;
import common.SteamyApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * @author micha
 *
 */
public class StorePageController {
	@FXML
	private Text priceText_;

	@FXML
	private HBox dlcBox_;

	@FXML
	private VBox rightArrowButton_;

	@FXML
	private ImageView bannerImg_;

	@FXML
	private HBox buyBox_;

	@FXML
	private VBox leftArrowButton_;

	@FXML
	private Text gameTitle_;

	@FXML
	private ImageView mainDisplayImg_;

	@FXML
	private VBox reviewBox_;
	@FXML
	private Button addToCart_;
	
  

  @FXML
  private ImageView sideDisplay_;
  @FXML
  private Text buyText_;




	MainLayoutController mainLayoutCont_;
	StoreItem item_;

	/**
	 * 
	 */
	public StorePageController () {

	}
	
	@FXML
	private void initilaize() {
		
	}

	public void loadElements() {
		System.out.println(item_.getGame().getName());
		gameTitle_.setText(item_.getGame().getName());
		priceText_.setText( Double.toString(item_.getGame().getPrice()));
		buyText_.setText("Buy: " + item_.getGame().getName());
		mainDisplayImg_.setImage(item_.getGame().getIcon());
		sideDisplay_.setImage(item_.getGame().getIcon());
	}
	
	@FXML
	public void addToCart() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Would you like to add this game to your cart?");
		Optional<ButtonType> result = alert.showAndWait();
		 if (result.isPresent() && result.get() == ButtonType.OK) {
			 if(!(DatabaseHandler.addToCart(SteamyApp.currUser_,item_.getGame().getId()).equals("Error adding game to cart."))) {
				 
			 }
			 else {
				 alert.close();
				 Alert alertError = new Alert(AlertType.ERROR);
				 alertError.setContentText("Error adding game to cart.");
				 alert.showAndWait();
			 }
			 
		 }
	}
	
	public void setMainLayoutCont(MainLayoutController cont) {
		this.mainLayoutCont_ = cont;
	}
	public void setStoreItem(StoreItem item) {
		this.item_ = item;
	}

	

}
