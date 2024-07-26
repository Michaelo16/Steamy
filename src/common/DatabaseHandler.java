package common;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Types;
import java.sql.Statement;
import java.util.*;
import java.util.List;
import java.util.logging.Logger;
import java.util.ArrayList;

import javax.sql.DataSource;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import profile.showcases.AchievementShowcase;
import profile.showcases.FavoriteGameShowcase;

/**
 * @author ma8705
 *
 */
public class DatabaseHandler  {

	private static final String DATABASE_ = "postgres";//The name of the database
	private static final String HOST_ = "localhost";//The host name of the database
	private static final String PORT_ = "5432";//Port 
	private static String DBUSER_;
	private static String DBPASS_;
	private static Connection connection_;

	private static String currUser_;


	//private static final String DB_URL = "jdbc:postgresql://34.68.115.98:5432/postgres\"";
	private static final String DB_URL = "jdbc:postgresql://34.68.115.98:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASS = "Summer20#";


	public void initStatements() throws SQLException {
		//Callable statements
		String addReview = "CALL AddReview(?,?,?,?)";
	}


	/**
	 * Opens a connection_ection to the database with 
	 * specified username and password
	 * @param user
	 * @param pass
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void openConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		Properties props = new Properties(); 
		props.setProperty("escapeSyntaxCallMode", "callIfNoReturn");
		props.setProperty("user", USER);
		props.setProperty("password", PASS);
		connection_ = DriverManager.getConnection(DB_URL, props);
	}

	/**
	 * closes an existing connection_ection to the database
	 */
	//	public static void closeConnection() {
	//		try {
	//			connection_.close();
	//		} catch ( SQLException e ) {
	//			e.printStackTrace();
	//		}
	//	}

	
	 public static String addUserWithProfile(String username, String password) {
     String sql = "SELECT add_user_with_profile(?, ?);";
     try (PreparedStatement stmt = connection_.prepareStatement(sql)) {
         stmt.setString(1, username);
         stmt.setString(2, password);
         ResultSet rs = stmt.executeQuery();
         if (rs.next()) {
             return rs.getString(1);
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
     return "Error adding user with profile.";
 }


	// Method to delete a user using the stored procedure
	public static boolean deleteUser(int userId) {
		String sql = "{ CALL delete_user(?) };";
		try (CallableStatement stmt = connection_.prepareCall(sql)) {
			stmt.setInt(1, userId);
			stmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Method to login user
	public static boolean loginUser(String userName, String password) {
		String sql = "SELECT passwordhash FROM users WHERE username = ? AND passwordhash = crypt(?, passwordhash);";
		try (PreparedStatement stmt = connection_.prepareStatement(sql)) {
			stmt.setString(1, userName);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return true; // Password matches
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; // User not found or password doesn't match
	}

	// Method to get User ID by username (needed for session management)
	public static int getUserIdByUsername(String userName) {
		String sql = "SELECT userid FROM users WHERE username = ?;";
		try (PreparedStatement stmt = connection_.prepareStatement(sql)) {
			stmt.setString(1, userName);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("userid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1; // User not found
	}


	/**
	 * returns if a user exists in the data base or not
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public static boolean userExists(String user, String pass) throws SQLException {
		CallableStatement st = connection_.prepareCall("{? = call user_exists( ? )};");
		st.registerOutParameter(1,Types.BOOLEAN);
		st.setString(2,user);
		st.execute();
		st.close();
		return st.getBoolean(1);
	}

	// Method to add a game to the user's library (purchase simulation)
	public static void addToLibrary(int userId, int gameId) {
		String sql = "{ CALL add_to_library(?, ?) };";
		try (CallableStatement stmt = connection_.prepareCall(sql)) {
			stmt.setInt(1, userId);
			stmt.setInt(2, gameId);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Method to add a game to favorites
	public static void addToFavorites(int userId, int gameId) {
		String sql = "{ CALL add_to_favorites(?, ?) };";
		try (CallableStatement stmt = connection_.prepareCall(sql)) {
			stmt.setInt(1, userId);
			stmt.setInt(2, gameId);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Method to remove a game from favorites
	public static void removeFromFavorites(int userId, int gameId) {
		String sql = "{ CALL remove_from_favorites(?, ?) };";
		try (CallableStatement stmt = connection_.prepareCall(sql)) {
			stmt.setInt(1, userId);
			stmt.setInt(2, gameId);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Method to get user's library
	public static ArrayList<Game> getUserLibrary(int userId) {
		String sql = "SELECT * FROM get_user_library(?);";
		ArrayList<Game> games = new ArrayList<>();
		try (PreparedStatement stmt = connection_.prepareStatement(sql)) {
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Game game = new Game(
				                     rs.getInt("gameid"),
				                     rs.getString("title"),
				                     rs.getString("description"),
				                     rs.getDate("releaseDate"),
				                     rs.getString("developer"),
				                     rs.getString("publisher"),
				                     rs.getDouble("price"),
				                     rs.getString("genreName")
						);
				//game.setFavorite(rs.getBoolean("IsFavorite"));
				games.add(game);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return games;
	}

	 // Method to delete a game from the user's library
  public static String deleteFromLibrary(int userId, int gameId) {
      String sql = "SELECT delete_from_library(?, ?);";
      try (PreparedStatement stmt = connection_.prepareStatement(sql)) {
          stmt.setInt(1, userId);
          stmt.setInt(2, gameId);
          ResultSet rs = stmt.executeQuery();
          if (rs.next()) {
              return rs.getString(1);
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return "Error deleting game from library.";
  }
	
	
	///NEW CODE///

	// Method to add or update profile
	public static void addOrUpdateProfile(int userId, String bio, String location, String website, String profilePicture) {
		String sql = "{CALL add_or_update_profile(?, ?, ?, ?, ?)};";
		try (CallableStatement stmt = connection_.prepareCall(sql)) {
			stmt.setInt(1, userId);
			stmt.setString(2, bio);
			stmt.setString(3, location);
			stmt.setString(4, website);
			stmt.setString(5, profilePicture);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Method to add or update favorite game showcase
	public static void addOrUpdateFavoriteGameShowcase(int userId, int gameId, int hoursPlayed, int achievementProgress) {
		String sql = "{CALL add_or_update_favorite_game_showcase(?, ?, ?, ?)};";
		try (CallableStatement stmt = connection_.prepareCall(sql)) {
			stmt.setInt(1, userId);
			stmt.setInt(2, gameId);
			stmt.setInt(3, hoursPlayed);
			stmt.setInt(4, achievementProgress);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Method to add or update achievement showcase
	public static void addOrUpdateAchievementShowcase(int userId, int achievementId, int totalAchievements) {
		String sql = "{CALL add_or_update_achievement_showcase(?, ?, ?)};";
		try (CallableStatement stmt = connection_.prepareCall(sql)) {
			stmt.setInt(1, userId);
			stmt.setInt(2, achievementId);
			stmt.setInt(3, totalAchievements);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Method to remove favorite game showcase
	public static void removeFavoriteGameShowcase(int userId) {
		String sql = "{CALL remove_favorite_game_showcase(?)};";
		try (CallableStatement stmt = connection_.prepareCall(sql)) {
			stmt.setInt(1, userId);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Method to remove achievement showcase
	public static void removeAchievementShowcase(int userId) {
		String sql = "{CALL remove_achievement_showcase(?)};";
		try (CallableStatement stmt = connection_.prepareCall(sql)) {
			stmt.setInt(1, userId);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Method to get profile information
	public static Profile getProfile(String user) {
		String sql = "SELECT * FROM public.get_profile(get_user_id(?));";
		try (PreparedStatement stmt = connection_.prepareStatement(sql)) {
			stmt.setString(1, user);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Profile(
				                   rs.getInt("userid"),
				                   rs.getString("bio"),
				                   rs.getString("location"),
				                   rs.getString("website"),
				                   rs.getString("profilepicture")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

		return null;
	}

	// Method to get favorite game showcase
	public static FavoriteGameShowcase getFavoriteGameShowcase(int userId) {
		String sql = "SELECT * FROM get_favorite_game_showcase(?);";
		try (PreparedStatement stmt = connection_.prepareStatement(sql)) {
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new FavoriteGameShowcase(
				                                rs.getInt("showcaseid"),
				                                rs.getInt("gameid"),
				                                rs.getString("title"),
				                                rs.getInt("hoursplayed"),
				                                rs.getInt("achievementprogress")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method to get achievement showcase
	public static AchievementShowcase getAchievementShowcase(int userId) {
		String sql = "SELECT * FROM get_achievement_showcase(?);";
		try (PreparedStatement stmt = connection_.prepareStatement(sql)) {
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new AchievementShowcase(
				                               rs.getInt("ShowcaseID"),
				                               rs.getInt("AchievementID"),
				                               rs.getString("Name"),
				                               rs.getInt("TotalAchievements")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method to get list of recent games
	public static ArrayList<Game> getRecentGames(int limit) {
		String sql = "SELECT * FROM get_recent_games(?);";
		ArrayList<Game> games = new ArrayList<>();
		try (PreparedStatement stmt = connection_.prepareStatement(sql)) {
			stmt.setInt(1, limit);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				games.add(new Game(
				                   rs.getInt("gameid"),
				                   rs.getString("title"),
				                   rs.getString("description"),
				                   rs.getDate("releaseDate"),
				                   rs.getString("developer"),
				                   rs.getString("publisher"),
				                   rs.getDouble("price"),
				                   rs.getString("genrename")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return games;
	}

	// Method to get top-selling games
	public static ArrayList<Game> getTopSellingGames(int limit) {
		String sql = "SELECT * FROM get_top_selling_games(?);";
		ArrayList<Game> games = new ArrayList<>();
		try (PreparedStatement stmt = connection_.prepareStatement(sql)) {
			stmt.setInt(1, limit);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				games.add(new Game(
				                   rs.getInt("gameid"),
				                   rs.getString("title"),
				                   rs.getString("description"),
				                   rs.getDate("releaseDate"),
				                   rs.getString("developer"),
				                   rs.getString("publisher"),
				                   rs.getInt("salescount"),
				                   rs.getDouble("price"),
				                   rs.getString("genrename")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return games;
	}

	// Method to get games popular with friends
	public static ArrayList<Game> getGamesPopularWithFriends(int userId) {
		String sql = "SELECT * FROM get_games_popular_with_friends(?);";
		ArrayList<Game> games = new ArrayList<>();
		try (PreparedStatement stmt = connection_.prepareStatement(sql)) {
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				games.add(new Game(
				                   rs.getInt("gameid"),
				                   rs.getString("title"),
				                   rs.getString("description"),
				                   rs.getDate("releaseDate"),
				                   rs.getString("developer"),
				                   rs.getString("publisher"),
				                   rs.getInt("friendpurchasecount"),
				                   rs.getDouble("Price"),
				                   rs.getString("genrename")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return games;
	}

	// Method to get recommended games based on purchased genres
	public static ArrayList<Game> getRecommendedGames(int userId) {
		String sql = "SELECT * FROM get_recommended_games(?);";
		ArrayList<Game> games = new ArrayList<>();
		try (PreparedStatement stmt = connection_.prepareStatement(sql)) {
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				games.add(new Game(
				                   rs.getInt("gameid"),
				                   rs.getString("title"),
				                   rs.getString("description"),
				                   rs.getDate("releaseDate"),
				                   rs.getString("developer"),
				                   rs.getString("publisher"),
				                   rs.getDouble("price"),
				                   rs.getString("genrename")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return games;
	}

	// Method to get featured games
	public static ArrayList<Game> getFeaturedGames(int limit) {
		String sql = "SELECT * FROM get_featured_games(?);";
		ArrayList<Game> games = new ArrayList<>();
		try (PreparedStatement stmt = connection_.prepareStatement(sql)) {
			stmt.setInt(1, limit);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				games.add(new Game(
				                   rs.getInt("gameid"),
				                   rs.getString("title"),
				                   rs.getString("description"),
				                   rs.getDate("releaseDate"),
				                   rs.getString("developer"),
				                   rs.getString("publisher"),
				                   rs.getInt("salescount"),
				                   rs.getDouble("price"),
				                   rs.getString("genrename")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return games;
	}

	public static ArrayList<Game> searchGamesByTitle(String title) {
		String sql = "SELECT * FROM search_games_by_title(?);";
		ArrayList<Game> games = new ArrayList<>();
		try (PreparedStatement stmt = connection_.prepareStatement(sql)) {
			stmt.setString(1, title);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				games.add(new Game(
				                   rs.getInt("gameid"),
				                   rs.getString("title"),
				                   rs.getString("description"),
				                   rs.getDate("releaseDate"),
				                   rs.getString("developer"),
				                   rs.getString("publisher"),
				                   rs.getDouble("price"),
				                   rs.getString("genrename")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return games;
	}
	
	 public static ArrayList<Game> getGamesByGenre(String genreName) {
     String sql = "SELECT * FROM get_games_by_genre(?);";
     ArrayList<Game> games = new ArrayList<>();
     try (PreparedStatement stmt = connection_.prepareStatement(sql)) {
         stmt.setString(1, genreName);
         ResultSet rs = stmt.executeQuery();
         while (rs.next()) {
             games.add(new Game(
                 rs.getInt("gameid"),
                 rs.getString("title"),
                 rs.getString("description"),
                 rs.getDate("releaseDate"),
                 rs.getString("developer"),
                 rs.getString("publisher"),
                 rs.getDouble("price"),
                 rs.getString("genreName")
             ));
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
     return games;
 }

	 public static String addToCart(int userId, int gameId) {
     String sql = "SELECT add_to_cart(?, ?);";
     try (PreparedStatement stmt = connection_.prepareStatement(sql)) {
         stmt.setInt(1, userId);
         stmt.setInt(2, gameId);
         ResultSet rs = stmt.executeQuery();
         if (rs.next()) {
             return rs.getString(1);
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
     return "Error adding game to cart.";
 }
	 
	 public static String purchaseCart(int userId) {
     String sql = "SELECT purchase_cart(?);";
     try (PreparedStatement stmt = connection_.prepareStatement(sql)) {
         stmt.setInt(1, userId);
         ResultSet rs = stmt.executeQuery();
         if (rs.next()) {
             return rs.getString(1);
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
     return "Error purchasing cart.";
 }

	// Method to remove game from cart
	public static void removeFromCart(int userId, int gameId) {
		String sql = "{CALL remove_from_cart(?, ?)};";
		try (CallableStatement stmt = connection_.prepareCall(sql)) {
			stmt.setInt(1, userId);
			stmt.setInt(2, gameId);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Method to view cart
	public static ArrayList<Game> viewCart(int userId) {
		String sql = "SELECT * FROM view_cart WHERE UserID = ?;";
		ArrayList<Game> games = new ArrayList<>();
		try (PreparedStatement stmt = connection_.prepareStatement(sql)) {
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				games.add(new Game(
				                   rs.getInt("gameid"),
				                   rs.getString("title"),
				                   rs.getString("description"),
				                   rs.getDate("releasedate"),
				                   rs.getString("developer"),
				                   rs.getString("publisher"),
				                   rs.getTimestamp("dateadded"),
				                   rs.getDouble("price"),
				                   rs.getString("genrename")

						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return games;
	}

	// Method to clear cart
	public static void clearCart(int userId) {
		String sql = "{CALL clear_cart(?)};";
		try (CallableStatement stmt = connection_.prepareCall(sql)) {
			stmt.setInt(1, userId);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Method to add game to wishlist
	public static void addToWishlist(int userId, int gameId) {
		String sql = "{CALL add_to_wishlist(?, ?)};";
		try (CallableStatement stmt = connection_.prepareCall(sql)) {
			stmt.setInt(1, userId);
			stmt.setInt(2, gameId);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Method to remove game from wishlist
	public static void removeFromWishlist(int userId, int gameId) {
		String sql = "{CALL remove_from_wishlist(?, ?)};";
		try (CallableStatement stmt = connection_.prepareCall(sql)) {
			stmt.setInt(1, userId);
			stmt.setInt(2, gameId);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Method to view wishlist
	public static List<Game> viewWishlist(int userId) {
		String sql = "SELECT * FROM view_wishlist WHERE userid = ?;";
		List<Game> games = new ArrayList<>();
		try (PreparedStatement stmt = connection_.prepareStatement(sql)) {
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				games.add(new Game(
				                   rs.getInt("gameid"),
				                   rs.getString("title"),
				                   rs.getString("description"),
				                   rs.getDate("releasedate"),
				                   rs.getString("developer"),
				                   rs.getString("publisher"),
				                   rs.getTimestamp("dateadded"),
				                   rs.getDouble("price"),
				                   rs.getString("genrename")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return games;
	}

	// Method to get list of friends
	public static ArrayList<Friend> getFriends(int userId) {
		String sql = "SELECT * FROM get_user_friends(?) WHERE status = 'accepted';";
		ArrayList<Friend> friends = new ArrayList<>();
		try (PreparedStatement stmt = connection_.prepareStatement(sql)) {
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				friends.add(new Friend(
				                       rs.getInt("friendid"),
				                       rs.getString("friendname"),
				                       rs.getString("status")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return friends;
	}
	
	 // Method to add a friend
  public static String addFriend(int userId1, int userId2) {
      String sql = "SELECT add_friend(?, ?);";
      try (PreparedStatement stmt = connection_.prepareStatement(sql)) {
          stmt.setInt(1, userId1);
          stmt.setInt(2, userId2);
          ResultSet rs = stmt.executeQuery();
          if (rs.next()) {
              return rs.getString(1);
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return "Error sending friend request.";
  }
  
  // Method to accept a friend request
  public static String acceptFriendRequest(int userId1, int userId2) {
      String sql = "SELECT accept_friend_request(?, ?);";
      try (PreparedStatement stmt = connection_.prepareStatement(sql)) {
          stmt.setInt(1, userId1);
          stmt.setInt(2, userId2);
          ResultSet rs = stmt.executeQuery();
          if (rs.next()) {
              return rs.getString(1);
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return "Error accepting friend request.";
  }

	// Close connection_ection method
	public static void closeConnection() {
		try {
			if (connection_ != null && !connection_.isClosed()) {
				connection_.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}






}
