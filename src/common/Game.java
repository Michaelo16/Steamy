package common;

import java.sql.Date;
import java.sql.Timestamp;

import javafx.scene.image.Image;

public class Game {
	
  private int gameId;
  private String title;
  private String description;
  private Date releaseDate;
  private String developer;
  private String publisher;
  private int salesCount;
  private Timestamp dateAdded;
  private double price_;
  private String genre_;

  
  
  private boolean isFavorite;
  
  private String iconUrl_;

  // Constructors, getters, and setters
  public Game(int gameId, String title, String description, Date releaseDate, String developer, String publisher, double price, String genre) {
    this.gameId = gameId;
    this.title = title;
    this.description = description;
    this.releaseDate = releaseDate;
    this.developer = developer;
    this.publisher = publisher;
    this.price_ = price;
    this.genre_ = genre;
    this.iconUrl_ = "/resources/images/testgraphic.png";
}
  
  public Game(int gameId, String title, String description, Date releaseDate, String developer, String publisher, int salesCount, double price, String genre) {
    this(gameId, title, description, releaseDate, developer, publisher, price,genre );
    this.salesCount = salesCount;
}

public Game(int gameId, String title, String description, Date releaseDate, String developer, String publisher, Timestamp dateAdded, double price, String genre) {
    this(gameId, title, description, releaseDate, developer, publisher, price, genre);
    this.dateAdded = dateAdded;
}
  

  public void setIconUrl(String url) {
  	this.iconUrl_ = "/resources/images/testgraphic.png";
  }
  
  public Image getIcon() {
  	return new Image(iconUrl_);
  }
  
  public int getId() {
      return gameId;
  }

  public void setName(String name) {
  	title =name;
  }
  
  public String getName() {
      return title;
  }

  public boolean isFavorite() {
      return isFavorite;
  }
  public double getPrice() {
  	return price_;
  }
  public String getGenre() {
  	return genre_;
  }
  
	/**
	 * @param boolean1
	 */
	public void setFavorite ( boolean boolean1 ) {
		this.isFavorite = boolean1;
		
	}
}