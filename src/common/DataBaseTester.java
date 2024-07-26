package common;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import profile.showcases.AchievementShowcase;
import profile.showcases.FavoriteGameShowcase;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DataBaseTester {
    

    @BeforeAll
    public void setup() {
        try {
					DatabaseHandler.openConnection();
				} catch ( ClassNotFoundException e ) {
					
					e.printStackTrace();
				} catch ( SQLException e ) {
				
					e.printStackTrace();
				}
        
    }

    @AfterAll
    public void cleanup() {
        DatabaseHandler.closeConnection();
    }
    
    
    
//    @Test
//    public void testRegisterUser() {
//        // Register a new user
//        int userId = DatabaseHandler.registerUser("bobbuilder", "bobbuilder@example.com", "testPassword");
//        assertNotEquals(-1, userId, "User registration failed");
//
//        // Verify user can be retrieved by username
//        int retrievedUserId = DatabaseHandler.getUserIdByUsername("bobbuilder");
//        assertEquals(userId, retrievedUserId, "User ID mismatch");
//
//        // Verify the user can log in with the correct password
//        boolean loginSuccess = DatabaseHandler.loginUser("bobbuilder", "testPassword");
//        assertTrue(loginSuccess, "Login failed with correct password");
//
//        // Verify the user cannot log in with an incorrect password
//        boolean loginFailure = DatabaseHandler.loginUser("bobbuilder", "wrongPassword");
//        assertFalse(loginFailure, "Login succeeded with incorrect password");
//    }

//    @Test
//    public void testDeleteUser() {
//        // Register a new user to be deleted
//        int userId = DatabaseHandler.registerUser("deleteUser", "deleteUser@example.com", "deletePassword");
//        assertNotEquals(-1, userId, "User registration failed");
//
//        // Verify the user exists
//        int retrievedUserId = DatabaseHandler.getUserIdByUsername("deleteUser");
//        assertEquals(userId, retrievedUserId, "User ID mismatch before deletion");
//
//        // Delete the user
//        boolean deleteSuccess = DatabaseHandler.deleteUser(userId);
//        assertTrue(deleteSuccess, "User deletion failed");
//
//        // Verify the user no longer exists
//        int deletedUserId = DatabaseHandler.getUserIdByUsername("deleteUser");
//        assertEquals(-1, deletedUserId, "User still exists after deletion");
//
//        // Verify the user cannot log in
//        boolean loginFailure = DatabaseHandler.loginUser("deleteUser", "deletePassword");
//        assertFalse(loginFailure, "Login succeeded for deleted user");
//    }

//    @Test
//    public void testAddOrUpdateProfile() {
//        DatabaseHandler.addOrUpdateProfile(1, "Test Bio", "Test Location", "http://test.com", "path/to/test.png");
//        Profile profile = DatabaseHandler.getProfile();
//        assertNotNull(profile);
//        assertEquals("Test Bio", profile.getBio());
//        assertEquals("Test Location", profile.getLocation());
//        assertEquals("http://test.com", profile.getWebsite());
//        assertEquals("path/to/test.png", profile.getProfilePicture());
//    }

    @Test
    public void testAddOrUpdateFavoriteGameShowcase() {
        DatabaseHandler.addOrUpdateFavoriteGameShowcase(1, 1, 50, 80);
        FavoriteGameShowcase showcase = DatabaseHandler.getFavoriteGameShowcase(1);
        assertNotNull(showcase);
        assertEquals(1, showcase.getGameId());
        assertEquals(50, showcase.getHoursPlayed());
        assertEquals(80, showcase.getAchievementProgress());
    }

    @Test
    public void testAddOrUpdateAchievementShowcase() {
        DatabaseHandler.addOrUpdateAchievementShowcase(1, 1, 10);
        AchievementShowcase showcase = DatabaseHandler.getAchievementShowcase(1);
        assertNotNull(showcase);
        assertEquals(1, showcase.getAchievementId());
        assertEquals(10, showcase.getTotalAchievements());
    }

    @Test
    public void testRemoveFavoriteGameShowcase() {
        DatabaseHandler.addOrUpdateFavoriteGameShowcase(1, 1, 50, 80);
        DatabaseHandler.removeFavoriteGameShowcase(1);
        FavoriteGameShowcase showcase = DatabaseHandler.getFavoriteGameShowcase(1);
        assertNull(showcase);
    }

    @Test
    public void testRemoveAchievementShowcase() {
        DatabaseHandler.addOrUpdateAchievementShowcase(1, 1, 10);
        DatabaseHandler.removeAchievementShowcase(1);
        AchievementShowcase showcase = DatabaseHandler.getAchievementShowcase(1);
        assertNull(showcase);
    }

    @Test
    public void testGetRecentGames() {
        List<Game> recentGames = DatabaseHandler.getRecentGames(5);
        assertNotNull(recentGames);
        assertTrue(recentGames.size() <= 5);
    }

    @Test
    public void testGetTopSellingGames() {
        List<Game> topSellingGames = DatabaseHandler.getTopSellingGames(5);
        assertNotNull(topSellingGames);
        assertTrue(topSellingGames.size() <= 5);
    }

    @Test
    public void testGetGamesPopularWithFriends() {
        List<Game> popularGames = DatabaseHandler.getGamesPopularWithFriends(1);
        assertNotNull(popularGames);
    }

    @Test
    public void testGetRecommendedGames() {
        List<Game> recommendedGames = DatabaseHandler.getRecommendedGames(1);
        assertNotNull(recommendedGames);
    }

    @Test
    public void testGetFeaturedGames() {
        List<Game> featuredGames = DatabaseHandler.getFeaturedGames(5);
        assertNotNull(featuredGames);
        assertTrue(featuredGames.size() <= 5);
    }

    @Test
    public void testAddToCart() {
        DatabaseHandler.addToCart(1, 1);
        List<Game> cartGames = DatabaseHandler.viewCart(1);
        assertNotNull(cartGames);
        assertTrue(cartGames.stream().anyMatch(game -> game.getId() == 1));
    }

    @Test
    public void testRemoveFromCart() {
        DatabaseHandler.addToCart(1, 1);
        DatabaseHandler.removeFromCart(1, 1);
        List<Game> cartGames = DatabaseHandler.viewCart(1);
        assertFalse(cartGames.stream().anyMatch(game -> game.getId() == 1));
    }

    @Test
    public void testClearCart() {
        DatabaseHandler.addToCart(1, 1);
        DatabaseHandler.clearCart(1);
        List<Game> cartGames = DatabaseHandler.viewCart(1);
        assertTrue(cartGames.isEmpty());
    }

    @Test
    public void testAddToWishlist() {
        DatabaseHandler.addToWishlist(1, 1);
        List<Game> wishlistGames = DatabaseHandler.viewWishlist(1);
        assertNotNull(wishlistGames);
        assertTrue(wishlistGames.stream().anyMatch(game -> game.getId() == 1));
    }

    @Test
    public void testRemoveFromWishlist() {
        DatabaseHandler.addToWishlist(1, 1);
        DatabaseHandler.removeFromWishlist(1, 1);
        List<Game> wishlistGames = DatabaseHandler.viewWishlist(1);
        assertFalse(wishlistGames.stream().anyMatch(game -> game.getId() == 1));
    }

    @Test
    public void testGetFriends() {
        List<Friend> friends = DatabaseHandler.getFriends(1);
        assertNotNull(friends);
    }
}