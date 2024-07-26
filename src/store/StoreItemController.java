package store;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * @author micha
 *
 */
public class StoreItemController {
	@FXML
	private Text priceText_;

	@FXML
	private HBox storeItemBox_;

	@FXML
	private VBox storeItem_;

	@FXML
	private Region separator_;

	@FXML
	private ImageView gameImage_;

	@FXML
	private VBox leftTextBox_;
	@FXML 
	private Text titleText_;
	@FXML
	private Text genreText_;

	private StoreItem item_;
	private boolean cartItem_ = false;
	private boolean searchItem_ = false;
	private SidePreviewController sideController_;
	private ListPreviewController mainController_;
	private StoreViewController storeController_;
	private CartViewController cartController_;
	
	
	@FXML
	private void initialize() {
		
	}

	@FXML
	private void updatePreview() {
		if(cartItem_ == false && searchItem_ == false) {
		mainController_.emptyPreview();
		mainController_.updatePreview(item_);
		}
	}
	

	public StoreItemController () {
		
	}

	public void setStoreItem(StoreItem item) {
		this.item_ = item;
	}

	public void loadElements() {
		titleText_.setText(item_.getTitle());
		gameImage_.setImage(item_.getGame().getIcon());
		priceText_.setText(Double.toString(item_.getGame().getPrice()));
		genreText_.setText(item_.getGame().getGenre());
		//TODO Load the rest of the elements from database info :)

	}
	
	public void setCartItem() {
		this.cartItem_ = true;
	}
	public void setSearchItem() {
		this.searchItem_ = true;
	}
	
	@FXML
	public void goToStorePage() {
		if(cartItem_ == true) {
			cartController_.loadStorePage(item_);
		}
		else
		storeController_.loadStorePage(item_);
	}

	public void setSideCont(SidePreviewController cont) {
		this.sideController_ = cont;
	}

	public void setMainCont(ListPreviewController cont) {
		this.mainController_ = cont;
	}
	
	public void setStoreViewCont(StoreViewController cont) {
		this.storeController_ = cont;
	}
	
	public void setCartController(CartViewController cart	) {
		this.cartController_ = cart;
	}

	public VBox getContainer() {
		return storeItem_;
	}




}
