package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.ArrayList;
import javafx.util.Pair;

import model.*;

public class Controller {

  /**
   * /!\ TO MODIFY AFTER EVERY GIT PULL /!\
   * The URL used to connect to the database with JDBC.
   */
  private final String dbUrl = "jdbc:mysql://127.0.0.1:3306/cookbook?user=root&password=ABC&useSSL=false";

  private Connection db;
  private model.User activeUser;

  public Controller() {
    this.db = dbconnect();
    this.activeUser = null;
  }

  public model.User getActiveUser() {
    return activeUser;
  }

  public void setActiveUser(model.User activeUser) {
    this.activeUser = activeUser;
  }

  /**
   * Try to connect to the database.
   * 
   * @return The connection
   */
  public Connection dbconnect() {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(dbUrl);
    } catch (SQLException e) {
      e.printStackTrace(System.err);
    }
    return conn;
  }

  public void dbClose() {
    try {
      this.db.close();
    } catch (SQLException e) {
      e.printStackTrace(System.err);
    }
  }

  /**
   * Try to log in a user depending of given credentials.
   * 
   * @param username The user's username
   * @param password The user's password
   * @return true if the connection is done, false if the user doesn't exist in
   *         the database or if any problem occurs.
   */
  public boolean login(String username, String password) {
    try {
      String query = "select * from user where username=? and password=?";
      PreparedStatement stmt = this.db.prepareStatement(query);
      stmt.setString(1, username);
      stmt.setString(2, password);

      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        int userId = Integer.parseInt(rs.getString(1));
        this.activeUser = this.createUser(rs, userId);
        if (this.activeUser == null) {
          return false;
        }
        return true;
      } else {
        return false;
      }
    } catch (SQLException e) {
      return false;
    }
  }

  /**
   * Create a new User object from an existing user in the database.
   * 
   * @param rs The user as a MySQL query result
   * @param id The user's ID
   * @return An object User
   */
  private User createUser(ResultSet rs, int id) {
    try {
      return new User(id, rs.getString(2), rs.getString(3), Boolean.parseBoolean(rs.getString(4)));
    } catch (SQLException e) {
      return null;
    }
  }

  public boolean addFavourite(User user, Recipe recipe){
    try {
      user.addFavourite(recipe);
      String query = "INSERT INTO favourite (user_id, recipe_id) VALUES ('?', '?')";
      PreparedStatement stmt = this.db.prepareStatement(query);
      stmt.setInt(1, user.getId());
      stmt.setInt(2, recipe.getId());
      stmt.executeUpdate();
      return true;
    } catch (SQLException e) {
      return false;
    }
  }

  public boolean removeFavourite(User user, Recipe recipe){
    try {
      user.removeFavourite(recipe);
      String query = "DELETE FROM favourite WHERE user_id = ? AND recipe_id = ?";
      PreparedStatement stmt = this.db.prepareStatement(query);
      stmt.setInt(1, user.getId());
      stmt.setInt(2, recipe.getId());
      stmt.executeUpdate();
      return true;
    } catch (SQLException e) {
      return false;
    }
  }

  public boolean clearFavourite(User user){
    try {
      user.clearFavourite();
      String query = "DELETE FROM favourite WHERE user_id = ?";
      PreparedStatement stmt = this.db.prepareStatement(query);
      stmt.setInt(1, user.getId());
      stmt.executeUpdate();
      return true;
    } catch (SQLException e) {
      return false;
    }
  }

  public boolean createEmptyWeeklyList(User user, int weeklyNumber, int isVisible){
    try {
      String query = "INSERT INTO week_list (weekNumber, isVisible, user_id) VALUES ('?', '?', '?')";
      PreparedStatement stmt = this.db.prepareStatement(query);
      stmt.setInt(1, weeklyNumber);
      stmt.setInt(2, isVisible);
      stmt.setInt(3, user.getId());
      stmt.executeUpdate();
      return true;
    } catch (SQLException e) {
      return false;
    }
  }

  public boolean addRecipeToWeeklyList(int weeklyId, String date, Recipe recipe) {
    try {
      String query = "INSERT INTO day_list (date, week_list_id) VALUES (?, ?)";
      PreparedStatement stmt = this.db.prepareStatement(query);
      stmt.setDate(1, java.sql.Date.valueOf(date));
      stmt.setInt(2, weeklyId);
      stmt.executeUpdate();

      query = "SELECT id FROM day_list WHERE date = ? AND week_list_id = ?";
      stmt = this.db.prepareStatement(query);
      stmt.setDate(1, java.sql.Date.valueOf(date));
      stmt.setInt(2, weeklyId);
      ResultSet rs = stmt.executeQuery();
      rs.next();

      query = "INSERT INTO day_list_has_recipe (recipe_id, day_list_id) VALUES (?, ?)";
      stmt = this.db.prepareStatement(query);
      stmt.setInt(1, recipe.getId());
      stmt.setLong(2, rs.getInt(1));
      stmt.executeUpdate();
      return true;
    } catch (SQLException e) {
      System.out.println(e);
      return false;
    }
  }

  //weekly.get((rs.getInt(2)-1)/7).add(rs.getInt(1));
  /* We need to recover all the recipe_id from day_list_has_recipe who has the right weekNumber. Then we need to sort every dish to fit in a seven days list. Then we recover every ingredient from every meal of a week and add them to a list.*/
  public ShoppingList generateShoppingList(int weekNumber, int user_id) {
    try {
      ShoppingList shoppingList = new ShoppingList(weekNumber, user_id);
      String query = "SELECT dlr.* FROM day_list_has_recipe dlr INNER JOIN day_list dl ON dlr.day_list_id = dl.id INNER JOIN week_list wl ON dl.week_list_id = wl.id WHERE wl.weekNumber = ? AND wl.user_id = ?";
      PreparedStatement stmt = this.db.prepareStatement(query);
      stmt.setInt(1, weekNumber);
      stmt.setInt(2, user_id);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()){
        query = "SELECT i.* FROM ingredient i INNER JOIN recipe_has_ingredient rhi ON i.id = rhi.ingredient_id INNER JOIN unit u ON i.unit_id = u.id WHERE rhi.recipe_id = ?";
        stmt = this.db.prepareStatement(query);
        stmt.setInt(1, rs.getInt(1));
        ResultSet rs2 = stmt.executeQuery();
        while(rs2.next()){
          int ingredientId = rs2.getInt(1);
          String ingredientName = rs2.getString(2);
          int ingredientQuantity = rs2.getInt(3);
          int ingredientUnit = rs2.getInt(4);

          boolean ingredientFound = false;
          
          // Check if the ingredient is already in the shopping list
          for (Pair<Ingredient, Integer> shoppingListItem : shoppingList.list) {
            
            Ingredient shoppingListIngredient = shoppingListItem.getKey();
            int shoppingListQuantity = shoppingListItem.getValue();

            if (shoppingListIngredient.getId() == rs2.getInt("id")) {
              shoppingList.list.remove(new Pair<Ingredient, Integer>(shoppingListIngredient, shoppingListQuantity));
              shoppingListQuantity += ingredientQuantity;
              Pair<Ingredient, Integer> updatedShoppingListItem = new Pair<>(shoppingListIngredient, shoppingListQuantity);
              shoppingList.list.add(updatedShoppingListItem);
              ingredientFound = true;
              break;
            }
          }

          // If the ingredient is not in the shopping list yet, add it
          if (!ingredientFound) {
            Ingredient ingredient = new Ingredient(ingredientId, ingredientName, ingredientQuantity, ingredientUnit);
            Pair<Ingredient, Integer> shoppingListItem = new Pair<>(ingredient, ingredientQuantity);
            shoppingList.list.add(shoppingListItem);
          }
        }      
      }
      return shoppingList;
    } catch (SQLException e) {
      return null;
    }
  }

  public void editShoppingList(ShoppingList shoppingList, Ingredient ingredient, int quantity) {
    for (Pair<Ingredient, Integer> shoppingListItem : shoppingList.list) {
      Ingredient shoppingListIngredient = shoppingListItem.getKey();
      int shoppingListQuantity = shoppingListItem.getValue();
      if (shoppingListIngredient.getId() == ingredient.getId()) {
        if (quantity >= shoppingListQuantity){
          shoppingList.list.remove(new Pair<Ingredient, Integer>(shoppingListIngredient, shoppingListQuantity));
        } else {
          shoppingList.list.remove(new Pair<Ingredient, Integer>(shoppingListIngredient, shoppingListQuantity));
          shoppingListQuantity -= quantity;
          Pair<Ingredient, Integer> updatedShoppingListItem = new Pair<>(shoppingListIngredient, shoppingListQuantity);
          shoppingList.list.add(updatedShoppingListItem);
        }
      break;
      }
    }
  }

  public boolean eraseShoppingList(ShoppingList shoppingList, int weekNumber) {
    if (shoppingList.getWeek() == weekNumber){
      shoppingList.list.removeAll(shoppingList.list);
    return true;
    }
    return false;
  }

}
