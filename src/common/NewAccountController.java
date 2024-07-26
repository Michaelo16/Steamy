package common;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

public class NewAccountController {

    @FXML
    private Button CreateButton_;

    @FXML
    private Text actExistsError_;

    @FXML
    private Button cancelButtonNew_;

    @FXML
    private PasswordField newPass_;

    @FXML
    private TextField newUser_;

    @FXML
    private Text passMatchError_;

    @FXML
    private PasswordField rptPassword_;

  	private Stage stage_;//Stage
  	private Scene scene_;//scene
  	private Parent root_;//root
  	
    @FXML
  	void createNewAccount(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
  		String user = newUser_.getText();//Get info 
  		String pass = newPass_.getText();
  		String rpt = rptPassword_.getText();
  		
  		try {//Connect
  			DatabaseHandler.openConnection();
  		}
  		catch(SQLException e) {
  			Alert connectionAlert = new Alert(AlertType.ERROR);
  			connectionAlert.setContentText("Unable to establish a connection to the server.");
  			connectionAlert.showAndWait();
  		}
  		
  		
//  		SteamyApp.base_ = new DatabaseHandler();
//  		SteamyApp.base_.openConnection("guest","guest");
//  		SteamyApp.base_.initStatements();
  		//Make sure fields are not empty
  		if(user.isBlank() || pass.isBlank()) {
  			passMatchError_.setText("Missing required fields");
  			return;
  		}
  		//Make sure passwords match
  		if(!pass.equals(rpt)) {
  			passMatchError_.setText("Passwords do not match");
  			return;
  		}
  		//Attempt to add new user
  		if(!(DatabaseHandler.addUserWithProfile(user,pass).equals("Error adding user with profile."))) {
  			switchToLogin(event);
  			//All things are right
  			passMatchError_.setText("");
  			actExistsError_.setText("");
  		}
  		else {
  			actExistsError_.setText("Error, Account already exists");
  		}
  		DatabaseHandler.closeConnection();
  	}

    @FXML
    void switchToLogin(ActionEvent event) throws IOException {
    	SteamyApp.switchToLogin(event);
  	}
    

}

