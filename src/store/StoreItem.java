package store;

import common.Game;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * @author micha
 *
 */
public class StoreItem {
	

	private Game game_;
	private Text title_;
	
	
	
	/**
	 * 
	 */
	public StoreItem ( Game game) {
		this.game_ = game;
	}

	public String getTitle() {
		return game_.getName();
	}
	
	public Game getGame() {
		return game_;
	}
	
	public void loadElements() {
		
		}
		
		
	}
	
	

