package store;

import java.io.IOException;

import common.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SidePreviewController {

    @FXML
    private GridPane previewGrid_;

    @FXML
    private VBox previewRoot_;

    @FXML
    private ImageView prevImage4_;

    @FXML
    private Text previewTitle_;

    @FXML
    private ImageView prevImage1_;

    @FXML
    private ImageView prevImage2_;

    @FXML
    private HBox previewTagBox_;

    @FXML
    private ImageView prevImage3_;
    @FXML
    private Button genre_;
    
    private StoreItem item_;
    
    
    @FXML
    private void initialize() {
    	
    	
    }
    @FXML
    private void test() {
    	
    }
   
    	public void setItem(StoreItem item) {
    		this.item_ = item;
    	}
    
    public void loadElements(StoreItem item) {
    	previewTitle_.setText(item.getTitle());
    	prevImage1_.setImage(item_.getGame().getIcon());
    	prevImage2_.setImage(item_.getGame().getIcon());
    	prevImage3_.setImage(item_.getGame().getIcon());
    	prevImage4_.setImage(item_.getGame().getIcon());
    	genre_.setText(item_.getGame().getGenre());
    	
    	//TODO get elements from games
    	
    	
    	
    }
    

}

