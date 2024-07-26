package common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Controller for the login window
 * @author ma8705
 *
 */
public class LoginController {

	private static final String LOGIN_FILE = "login.txt";//File with saved login info
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button cancelButton_;
	@FXML
	private Text loginError_;
	@FXML
	private Button loginButton_;
	@FXML
	private PasswordField passLogin_;
	@FXML
	private TextField userLogin_;
	@FXML
	private Text loginHeader_;
	@FXML
	private TextField passField_;
	@FXML
	private CheckBox saveBox_;
	@FXML
	private Button newAccountButton_;
	@FXML
	private Button CreateButton_;
	@FXML
	private Text actExistsError_;
	@FXML
	private Button cancelButtonNew_;
	@FXML
	private Text passMatchError_;
	@FXML
	private PasswordField rptPassword_;
	@FXML
	private PasswordField newPass_;
	@FXML
	private TextField newUser_;
	@FXML
	private Button communityButton_;
	@FXML
	private MenuItem exitButton_;
	@FXML
	private Button friendsButton_;
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
	private MenuItem helpButton_;
	@FXML
	private ImageView forYou1_;
	@FXML
	private ImageView forYou2_;
	@FXML
	private ImageView forYou3_;
	@FXML
	private ImageView friendLike1_;
	@FXML
	private ImageView friendLike2_;
	@FXML
	private ImageView friendLike3_;
	@FXML
	private ImageView pop1_;
	@FXML
	private ImageView pop2_;
	@FXML
	private ImageView pop3_;
	@FXML
	private ImageView tryNew1_;
	@FXML
	private ImageView tryNew2_;
	@FXML
	private ImageView tryNew3_;
	@FXML
	private TextField userName_;
	@FXML
	private Text displayName_;
	@FXML
	private VBox StoreList_;
	@FXML
	private Text missingFields_;

	private Stage stage_;//Stage
	private Scene scene_;//scene
	private Parent root_;//root



	@FXML
	void initialize(Stage stage) throws SQLException {
		stage_ = stage;
		SavedLogin temp;
		try {
			temp = LoginController.getSaved();
			if(temp != null) {
			 autoFill(temp.getUsername_(), temp.getPassword_());
			}
		} catch ( NumberFormatException e ) {
			e.printStackTrace();
		} catch ( IOException e ) {
			e.printStackTrace();
		}



	}



	@FXML
	void login(ActionEvent event) throws IOException, SQLException, ClassNotFoundException{
		if(userLogin_.getText().isBlank() || passLogin_.getText().isBlank()) {
			missingFields_.setText("Missing required fields");
			return;
		}
		else {
			missingFields_.setText("");
		}

		try {//Try connection
			DatabaseHandler.openConnection();
		}
		catch(SQLException e) {
			e.printStackTrace();
			Alert connectionAlert = new Alert(AlertType.ERROR);
			connectionAlert.setContentText("Unable to establish a connection to the server.");
			connectionAlert.showAndWait();
		}

		//Try login
		if(DatabaseHandler.loginUser(userLogin_.getText(), passLogin_.getText()) == true) {
			if(saveBox_.isSelected()) {
			saveLogin();
			}
			SteamyApp.currUser_ = DatabaseHandler.getUserIdByUsername(userLogin_.getText());
			SteamyApp.username_ = userLogin_.getText();
			SteamyApp.switchToAppHome(event);
			
			
		}
		else {//login fail
			Alert connectionAlert = new Alert(AlertType.ERROR);
			connectionAlert.setContentText("There was an error signing in. Check your credentials and try again");
			connectionAlert.showAndWait();
		}
	}

	private void saveLogin() {
		PrintWriter writer;
		try {
			writer = new PrintWriter(new FileWriter(LOGIN_FILE), false);
			writer.write("");
			writer.write("saved:1\n" + "username:"+userLogin_.getText()+"\n" + "password:" + 
					passLogin_.getText());
			writer.close();
		} catch ( IOException e ) {
			System.out.println("login file not found");
		}

	}


	private void autoFill(String user, String pass) {
		userLogin_.setText(user);
		passLogin_.setText(pass);
	}


	/**
	 * Checks if there is a saved login and returns it. 
	 * If not returns null
	 * @return
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static SavedLogin getSaved() throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(LOGIN_FILE));
		int isSaved =Integer.parseInt(reader.readLine().split(":")[1]);
		if(isSaved == 1) {//Yes
			String username = reader.readLine().split(":")[1];
			String password = reader.readLine().split(":")[1];
			SavedLogin user = new SavedLogin(username, password);
			reader.close();
			return user;
		}
		reader.close();
		return null;
	}





	@FXML
	void switchToNewAct(ActionEvent event) throws IOException {
		SteamyApp.switchToNewAct(event);
	}

	@FXML
	void switchToAppHome(ActionEvent event) throws IOException {
		SteamyApp.switchToAppHome(event);
	}

	@FXML
	void switchToAppStore(ActionEvent event) throws IOException {
		SteamyApp.switchToAppStore(event);
	}

	@FXML
	void exit(ActionEvent event) {
		SteamyApp.closeApp(event);
	}


}
