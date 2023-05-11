package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;

import com.mysql.cj.exceptions.StreamingNotifiable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Ingredient;
import model.Recipe;
import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

import model.*;
import view.*;

public class Controller {


  /**
   * /!\ TO MODIFY AFTER EVERY GIT PULL /!\
   * The URL used to connect to the database with JDBC.
   */
  private final String dbUrl = "jdbc:mysql://localhost/cookbook?user=root&password=root&useSSL=false";

  /**
   * Used to make this class a singleton
   */
  private static volatile Controller instance;

  private Connection db;
  private model.User activeUser;
  private ArrayList<Recipe> recipeList;
  private Stage stage;
  private model.Recipe recipe;

  public ArrayList<Recipe> getRecipeList() {
    return recipeList;
  }

  protected Controller() {
    this.db = dbconnect();
    this.activeUser = null;
    this.recipeList = generateRecipeListFromDb();
    this.stage = null;
    this.recipe = null;
  }


  /**
   * Get the instance of this class, or create it if it does not exist.
   *
   * @return The Controller singleton
   */
  public static Controller getInstance() {
    if (instance == null) {
      instance = new Controller();
    }
    return instance;
  }

  public Stage getStage() {
    return stage;
  }

  public void setStage(Stage stage) {
    this.stage = stage;
  }

  public model.User getActiveUser() {
    return this.activeUser;
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
      System.out.println("You probably forgot to change the password.");
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
        this.activeUser.setFavouriteList(this.generateFavouriteListFromDb());
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

  /**
   * Takes all the recipes from the database and store them as Recipe objects in a
   * list.
   *
   * @return A list of all existing recipes
   */
  private ArrayList<Recipe> generateRecipeListFromDb() {
    try {
      String query = "select * from recipe";
      Statement stmt = this.db.createStatement();

      ResultSet rs = stmt.executeQuery(query);

      ArrayList<Recipe> recipeList = new ArrayList<Recipe>();

      while (rs.next()) {
        Recipe r = createRecipe(rs);
        recipeList.add(r);
      }

      return recipeList;

    } catch (SQLException e) {
      return null;
    }
  }

  public List<String> selectRecipeFromDatabase() {
    List<String> data = new ArrayList<>();

    try (Connection connection = dbconnect();
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery("SELECT name FROM cookbook.recipe")) {

      while (resultSet.next()) {
        String value = resultSet.getString("name");
        data.add(value);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return data;
  }

  public List<String> selectIngredientsFromDatabase() {


    // Ingredient name
    // Get Ingredient ID

    // Where Ingredient ID is shown, get Recipe ID
    // Get Name from Recipe ID


    List<String> data = new ArrayList<>();

    try (Connection connection = dbconnect();
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery("  SELECT * FROM cookbook.recipe_has_ingredient")) {

      while (resultSet.next()) {
        String value = resultSet.getString("recipe_id");
        data.add(value);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return data;
  }

  /**
   * Generates the list of all favourite recipes (as objects) of the active user
   * that are stored in the database.
   *
   * @return The ArrayList of favourite recipes of the active user
   */
  private ArrayList<Recipe> generateFavouriteListFromDb() {
    try {
      String query = "select * from recipe R join favourite F on R.id = F.recipe_id where F.user_id = ?";
      PreparedStatement stmt = this.db.prepareStatement(query);
      stmt.setInt(1, this.activeUser.getId());

      ResultSet rs = stmt.executeQuery();

      ArrayList<Recipe> favouriteList = new ArrayList<Recipe>();

      while (rs.next()) {
        Recipe r = createRecipe(rs);
        favouriteList.add(r);
      }
      return favouriteList;

    } catch (SQLException e) {
      e.printStackTrace(System.out);
      return null;
    }
  }

  /**
   * Create an Ingredient object from a MySQL query result.
   *
   * @param ingRs a query result
   * @return An Ingredient object
   */
  private Ingredient createIngredient(ResultSet ingRs) {
    try {
      Ingredient i = new Ingredient(Integer.parseInt(ingRs.getString(1)), ingRs.getString(2),
              Integer.parseInt(ingRs.getString(3)), Integer.parseInt(ingRs.getString(4)));
      return i;
    } catch (SQLException e) {
      return null;
    }
  }

  /**
   * Create a Recipe object from a MySQL query result.
   *
   * @param rs The query result
   * @return A Recipe object
   */
  private Recipe createRecipe(ResultSet rs) {
    try {
      int recipeId = Integer.parseInt(rs.getString(1));
      String ingQuery = "select * from ingredient I join recipe_has_ingredient R on I.id = R.ingredient_id where R.recipe_id = ?";
      PreparedStatement ingStmt = this.db.prepareStatement(ingQuery);
      ingStmt.setInt(1, recipeId);
      ResultSet ingRs = ingStmt.executeQuery();

      ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();

      while (ingRs.next()) {
        Ingredient i = createIngredient(ingRs);
        ingredientList.add(i);
      }
      Recipe r = new Recipe(recipeId, rs.getString(2), rs.getString(3), rs.getString(4), ingredientList);
      return r;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Creates and displays the login scene.
   */
  public void displayLoginScene() {
    LoginView loginView = new LoginView();
    Scene loginScene = new Scene(loginView.getRoot());
    stage.setScene(loginScene);
    stage.show();
  }

  /**
   * Creates and displays the recipe browser scene.
   */
  public void displayBrowserView() {
    BrowserView browserView = new BrowserView();
    Scene browserScene = new Scene(browserView.getRoot());
    stage.setScene(browserScene);
    stage.show();
  }

  /**
   * Creates and display the recipe details scene.
   */
  public void displayRecipeView(Recipe recipe) {
    RecipeView recipeView = new RecipeView(recipe);
    Scene recipeScene = new Scene(recipeView.getRoot());
    stage.setScene(recipeScene);
    stage.show();
  }

  /**
   * Creates and displays the home scene.
   */
  public void displayHomeView() {
    HomeView homeView = new HomeView();
    Scene mainScene = new Scene(homeView.getRoot());
    stage.setScene(mainScene);
    stage.show();
  }

  /**
   * Creates and display the favourite scene.
   */
  public void displayFavouriteView() {
    FavouriteView favouriteView = new FavouriteView();
    Scene favouriteScene = new Scene(favouriteView.getRoot());
    stage.setScene(favouriteScene);
    stage.show();
  }

  /**
   * Creates and displays the new recipe prompt scene.
   */
  public void displayNewRecipeView() {
    NewRecipeView newRecipeView = new NewRecipeView();
    Scene newRecipeScene = new Scene(newRecipeView.getRoot());
    Stage secondaryStage = new Stage();
    secondaryStage.setScene(newRecipeScene);
    secondaryStage.show();
  }

  /**
   * Closes the app.
   */
  public void closeApp() {
    this.stage.close();
    this.dbClose();
  }

  /**
   * Add a recipe to the favourite list of the active user, both in the database
   * and in the ArrayList.
   *
   * @param recipe The recipe to add to favourites
   * @return {@code true} if successful
   */
  public boolean addFavourite(Recipe recipe) {
    try {
      this.activeUser.addFavourite(recipe);
      String query = "INSERT INTO favourite (user_id, recipe_id) VALUES (?, ?)";
      PreparedStatement stmt = this.db.prepareStatement(query);
      stmt.setInt(1, this.activeUser.getId());
      stmt.setInt(2, recipe.getId());
      stmt.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace(System.out);
      return false;
    }
  }

  /**
   * Delete a recipe from the favourite list of the active user, both in the
   * database and in the ArrayList.
   *
   * @param recipe The recipe to delete from favourites
   * @return {@code true} if successful
   */
  public boolean removeFavourite(Recipe recipe) {
    try {
      this.activeUser.removeFavourite(recipe);
      String query = "DELETE FROM favourite WHERE user_id = ? AND recipe_id = ?";
      PreparedStatement stmt = this.db.prepareStatement(query);
      stmt.setInt(1, this.activeUser.getId());
      stmt.setInt(2, recipe.getId());
      stmt.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace(System.out);
      return false;
    }
  }

  /**
   * Delete all the favourite recipes of the active user, both in the database
   * and in the ArrayList.
   *
   * @return {@code true} if successful
   */
  public boolean clearFavourite() {
    try {
      this.activeUser.clearFavourite();
      String query = "DELETE FROM favourite WHERE user_id = ?";
      PreparedStatement stmt = this.db.prepareStatement(query);
      stmt.setInt(1, this.activeUser.getId());
      stmt.executeUpdate();
      return true;
    } catch (SQLException e) {
      return false;
    }
  }

  public boolean createEmptyWeeklyList(int weeklyNumber, int isVisible) {
    try {
      String query = "INSERT INTO week_list (weekNumber, isVisible, user_id) VALUES (?, ?, ?)";
      PreparedStatement stmt = this.db.prepareStatement(query);
      stmt.setInt(1, weeklyNumber);
      stmt.setInt(2, isVisible);
      stmt.setInt(3, this.activeUser.getId());
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

  // weekly.get((rs.getInt(2)-1)/7).add(rs.getInt(1));
  /*
   * We need to recover all the recipe_id from day_list_has_recipe who has the
   * right weekNumber. Then we need to sort every dish to fit in a seven days
   * list. Then we recover every ingredient from every meal of a week and add them
   * to a list.
   */
  public ShoppingList generateShoppingList(int weekNumber, int user_id) {
    try {
      ShoppingList shoppingList = new ShoppingList(weekNumber, user_id);
      String query = "SELECT dlr.* FROM day_list_has_recipe dlr INNER JOIN day_list dl ON dlr.day_list_id = dl.id INNER JOIN week_list wl ON dl.week_list_id = wl.id WHERE wl.weekNumber = ? AND wl.user_id = ?";
      PreparedStatement stmt = this.db.prepareStatement(query);
      stmt.setInt(1, weekNumber);
      stmt.setInt(2, user_id);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        query = "SELECT i.* FROM ingredient i INNER JOIN recipe_has_ingredient rhi ON i.id = rhi.ingredient_id INNER JOIN unit u ON i.unit_id = u.id WHERE rhi.recipe_id = ?";
        stmt = this.db.prepareStatement(query);
        stmt.setInt(1, rs.getInt(1));
        ResultSet rs2 = stmt.executeQuery();
        while (rs2.next()) {
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
              Pair<Ingredient, Integer> updatedShoppingListItem = new Pair<>(shoppingListIngredient,
                      shoppingListQuantity);
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
        if (quantity >= shoppingListQuantity) {
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
    if (shoppingList.getWeek() == weekNumber) {
      shoppingList.list.removeAll(shoppingList.list);
      return true;
    }
    return false;
  }

  public boolean newRecipe(String name, String description, String shortDescription,
                           ArrayList<Integer> ingredientList) {
    try {
      int recipe_id;
      String query = "INSERT INTO recipe (name, shortDescription, description) VALUES (?, ?, ?)";
      PreparedStatement stmt = this.db.prepareStatement(query);
      stmt.setString(1, name);
      stmt.setString(2, shortDescription);
      stmt.setString(3, description);
      stmt.executeUpdate();
      query = "SELECT id FROM recipe WHERE name = ?";
      stmt = this.db.prepareStatement(query);
      stmt.setString(1, name);
      ResultSet rs = stmt.executeQuery();
      rs.next();
      recipe_id = rs.getInt(1);
      for (int i : ingredientList) {
        query = "INSERT INTO recipe_has_ingredient (recipe_id, ingredient_id) VALUES (?, ?)";
        stmt = this.db.prepareStatement(query);
        stmt.setInt(1, recipe_id);
        stmt.setInt(2, i);
        stmt.executeUpdate();
      }
      return true;
    } catch (SQLException e) {
      return false;
    }
  }

  /**
   * Find and creates a recipe object from the database using the recipe' id.
   *
   * @param id The id of the recipe to get from the database
   * @return The recipe as an object
   */
  public Recipe getRecipeById(int id) {
    try {
      String query = "select * from recipe where id = ?";
      PreparedStatement stmt = this.db.prepareStatement(query);
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        return this.createRecipe(rs);
      } else {
        return null;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Add or remove a recipe from the favourite list depending if it is already or
   * not.
   *
   * // @param recipeId The id of the recipe to add/delete.
   * @return {@code true} if the recipe was not in the favourite list and was then
   *         added.
   */
  public boolean toggleFavourite(Recipe r) {
    if (r == null) {
      System.out.println("Error when loading recipe");
      return false;
    }
    if (this.activeUser.isFavourite(r)) {
      this.removeFavourite(r);
      return false;
    } else {
      this.addFavourite(r);
      return true;
    }
  }
}

