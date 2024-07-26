package common;

import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class SteamyApp extends Application {


	static DatabaseHandler base_;//The dataBase
	public static int currUser_;
	public static String username_;
	
	static String userType_;

	static FXMLLoader loader_;

	static Scene homeScene_;
	static Scene libraryScene_;


	private static Parent primaryRoot_;//FXML root
	private static Scene primaryScene_;//scene
	private static Stage primaryStage_;


	@Override
	public void start(Stage primaryStage) {

		try {
			loader_ = new FXMLLoader();
			openLogin(primaryStage);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	/*****************
	 *SCENE SWITCHING*
	 *****************/

	//Scene Switching

	static void changeScene(ActionEvent event, boolean resize, String scene) {
		primaryStage_ =(Stage)((Node) event.getSource()).getScene().getWindow();
		try {
			primaryRoot_ = FXMLLoader.load(SteamyApp.class.getResource("/resources/scenes/" + scene));
			primaryScene_ = new Scene(primaryRoot_);
			primaryScene_.getStylesheets().add(SteamyApp.class.getResource("/resources/style.css").toExternalForm());
			primaryStage_.setScene(primaryScene_);
			primaryStage_.setResizable(resize);
			if(resize == true) {//Min Size
				primaryStage_.setMinWidth(1100);
				primaryStage_.setMinHeight(800);
				primaryStage_.setHeight(800);
			}
			primaryStage_.show();
			loader_.setLocation(SteamyApp.class.getResource(scene));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public static void openLogin(Stage stage) {
		primaryStage_ = stage;  
		try {
			primaryRoot_ = FXMLLoader.load(SteamyApp.class.getResource("/resources/scenes/loginScene.fxml"));
			System.out.println("FXML loaded successfully");
			primaryScene_ = new Scene(primaryRoot_);
			primaryScene_.getStylesheets().add(SteamyApp.class.getResource("/resources/style.css").toExternalForm());
			primaryStage_.setScene(primaryScene_);
			primaryStage_.setResizable(false);
			primaryStage_.show();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	static void switchToLogin(ActionEvent event) throws IOException {
		SteamyApp.changeScene(event, false, "loginScene.fxml");

	}


	static void switchToNewAct(ActionEvent event) throws IOException {
		SteamyApp.changeScene(event,false,"newaccount.fxml");
	}



	static void switchToAppCommunity(ActionEvent event) throws IOException {
		SteamyApp.changeScene(event, true, "steamyappcommunity.fxml");
	}


	static void switchToAppHome(ActionEvent event) throws IOException {
		SteamyApp.changeScene(event, true, "steamyMainView.fxml");
	}


	static void switchToAppLibrary(ActionEvent event) throws IOException, SQLException {
		SteamyApp.changeScene(event, true, "steamyapplibrary.fxml");
	}


	static void switchToAppStore(ActionEvent event) throws IOException {
		SteamyApp.changeScene(event, true, "steamyappstore.fxml");
	}


	static void switchToAppprofile(ActionEvent event) throws IOException {
		SteamyApp.changeScene(event, true, "steamyappprofile.fxml");
	}

	static void closeApp(ActionEvent event) {
		primaryStage_.close();
	}















}
