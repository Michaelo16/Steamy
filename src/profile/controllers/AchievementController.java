package profile.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.image.*;

/**
 * @author micha
 *
 */
public class AchievementController {

  @FXML
  private GridPane display_;

  @FXML
  private Text numAchivements_;

  @FXML
  private Text otherAchivements_;
	
	
	
  
  public void loadElements() {
  	
  	for(Node node: display_.getChildren()) {
  		ImageView temp = (ImageView)node;
  		temp.setImage(new Image("/resources/images/testgraphic"));
  	}
  }
  
  
	
}
