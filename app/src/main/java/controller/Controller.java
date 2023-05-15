package controller;

import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.sql.ResultSet;
import java.util.List;

import com.sun.javafx.binding.StringFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Ingredient;
import model.Recipe;
import model.WeeklyList.WeekDay;

import java.util.ArrayList;
import javafx.util.Pair;
import model.*;
import org.w3c.dom.Text;
import view.*;

public class Controller {

  /**
   * /!\ TO MODIFY AFTER EVERY GIT PULL /!\
   * The URL used to connect to the database with JDBC.
   */
  private final String dbUrl = "jdbc:mysql://localhost/cookbook?user=root&password=0000&useSSL=false";

  private static volatile Controller instance;

  private Connection db;
  private Time time;
  private model.User activeUser;
  private ArrayList<Recipe> recipeList;
  private Stage stage;
  private model.Recipe recipe;

  public ArrayList<Recipe> getRecipeList() {
    return recipeList;
  }

  private Controller() {
    this.db = dbconnect();
    this.time = new Time();
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

  public Time getTime() {
    return time;
  }

  public void displaySearchView() {
    SearchView searchView = new SearchView();
    Scene searchScene = new Scene(searchView.getRoot());
    stage.setScene(searchScene);
    stage.show();
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
   * Try to log in a user depending of given credentials. It also loads all the
   * necessary data from the database that are linked to that user.
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
        this.activeUser.setWeeklyPlanList(this.generateWeeklyPlansFromDb());
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

  private ArrayList<Comment> generateCommentListFromDb(int recipeId) {
    try {
      String query = "select * from comment where recipe_id==recipeId";
      Statement stmt = this.db.createStatement();

      ResultSet rs = stmt.executeQuery(query);

      ArrayList<Comment> commentList = new ArrayList<>();

      // while (rs.next()) {
      // Comment comment = createRecipe(rs);
      // commentList.add(comment);
      // }

      return commentList;

    } catch (SQLException e) {
      return null;
    }
  }

  public List<String> selectDataFromDatabase() {
    List<String> data = new ArrayList<>();

    try {
      String query = "SELECT * FROM cookbook.recipe";
      PreparedStatement statement = dbconnect().prepareStatement(query);
      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        String value = resultSet.getString("name");
        data.add(value);
      }

      resultSet.close();
      statement.close();

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
   * Generates the list of all weekly lists of active user from the database.
   * 
   * @return An ArrayList of WeeklyList objects
   */
  public ArrayList<WeeklyList> generateWeeklyPlansFromDb() {
    try {
      String query = "select * from week_list where user_id = ?";
      PreparedStatement stmt = this.db.prepareStatement(query);
      stmt.setInt(1, this.activeUser.getId());
      ResultSet rs = stmt.executeQuery();

      ArrayList<WeeklyList> weeklyPlanList = new ArrayList<WeeklyList>();
      while (rs.next()) {
        weeklyPlanList.add(createWeeklyList(rs));
      }
      Comparator<WeeklyList> comp = Comparator.comparing(WeeklyList::getWeekNumber);
      Collections.sort(weeklyPlanList, comp);
      return weeklyPlanList;
    } catch (SQLException e) {
      e.printStackTrace(System.out);
      return null;
    }
  }

  /**
   * Creates a WeeklyList object from a SQL query result.
   * 
   * @param rs A query result. Must contain the values {@code id},
   *           {@code weekNumber}, {@code year} and {@code creation_date}.
   * @return A {@code WeeklyList} object
   */
  private WeeklyList createWeeklyList(ResultSet rs) {
    try {
      int id = rs.getInt("id");
      int weekNumber = rs.getInt("weekNumber");
      int year = rs.getInt("year");
      LocalDate creationDate = rs.getDate("creation_date").toLocalDate();
      WeeklyList newWeekly = new WeeklyList(weekNumber, year, id, creationDate);

      EnumMap<WeeklyList.WeekDay, ArrayList<Recipe>> list = newWeekly.getList();

      String query = "select * from recipe R join day_list D on R.id = D.recipe_id where D.week_list_id = ?";
      PreparedStatement stmt = this.db.prepareStatement(query);
      stmt.setInt(1, id);
      ResultSet rs_ = stmt.executeQuery();

      while (rs_.next()) {
        WeekDay day = WeekDay.valueOf(rs_.getString("day"));
        Recipe r = createRecipe(rs_);
        list.get(day).add(r);
      }
      return newWeekly;
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
          Integer.parseInt(ingRs.getString(3)), ingRs.getString(4));
      return i;
    } catch (SQLException e) {
      return null;
    }
  }

  private Comment createComment(ResultSet comRs) {
    try {
      Comment c = new Comment(Integer.parseInt(comRs.getString(1)), Integer.parseInt(comRs.getString(2)),
          Integer.parseInt(comRs.getString(3)), comRs.getString(4), comRs.getString(5));
      return c;
    } catch (SQLException e) {
      return null;
    }
  }
  private Tag createTag(ResultSet tagRs) {
    try {
      Tag t = new Tag(Integer.parseInt(tagRs.getString(1)), tagRs.getString(2), Integer.parseInt(tagRs.getString(3)));
      return t;
    } catch (SQLException e) {
      return null;
    }
  }
  public ArrayList<Comment> getCommentListByRecipeID(int recipeId) {
    try {
      String query = "select C.id, C.user_id, C.recipe_id, C.text, U.username from comment C join user U on C.user_id = U.id where C.recipe_id = ?";
      PreparedStatement stmt = this.db.prepareStatement(query);
      stmt.setInt(1, recipeId);
      ResultSet rs = stmt.executeQuery();

      ArrayList<Comment> commentList = new ArrayList<Comment>();

      while (rs.next()) {
        Comment comment = createComment(rs);
        commentList.add(comment);
      }
      return commentList;

    } catch (SQLException e) {
      return null;
    }
  }

  public ArrayList<Ingredient> getIngListByRecipeID(int recipeId) {
    try {
      String query = "select I.id, I.name, I.quantity, U.name from ingredient I join recipe_has_ingredient R on I.id = R.ingredient_id join unit U on I.unit_id = U.id where R.recipe_id = ?";
      PreparedStatement stmt = this.db.prepareStatement(query);
      stmt.setInt(1, recipeId);
      ResultSet rs = stmt.executeQuery();

      ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();


      while (rs.next()) {
        Ingredient ingredient = createIngredient(rs);
        ingredientList.add(ingredient);
      }
      return ingredientList;

    } catch (SQLException e) {
      return null;
    }
  }
  public ArrayList<Tag> getTagListByRecipeID(int recipeId) {
    try {
      String query = "select T.id, T.name, T.user_id from recipe R join recipe_has_tag RT on R.id = RT.recipe_id join tag T on T.id = RT.tag_id where R.id = ?";
      PreparedStatement stmt = this.db.prepareStatement(query);
      stmt.setInt(1, recipeId);
      ResultSet rs = stmt.executeQuery();

      ArrayList<Tag> tagList = new ArrayList<Tag>();


      while (rs.next()) {
        Tag tag = createTag(rs);
        tagList.add(tag);
      }
      return tagList;

    } catch (SQLException e) {
      return null;
    }
  }

  /**
   * Create a Recipe object from a MySQL query result.
   * 
   * @recipeId The recipe ID
   * @param rs The query result
   * @ingredientList The list of all ingredients used in the recipe
   * @return A Recipe object
   */
  private Recipe createRecipe(ResultSet rs) {
    try {
      int recipeId = Integer.parseInt(rs.getString(1));
      ArrayList<Ingredient> ingredientList = getIngListByRecipeID(recipeId);
      ArrayList<Comment> commentList = getCommentListByRecipeID(recipeId);
      ArrayList<Tag> tagList = getTagListByRecipeID(recipeId);

      Recipe recipe = new Recipe(recipeId, rs.getString(2), rs.getString(3), rs.getString(4), ingredientList,
          commentList, tagList);
      return recipe;
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
  public void displayRecipeView(int recipeId) {
    RecipeView recipeView = new RecipeView(recipeId);
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

  public void displayWeeklyPlanListView() {
    WeeklyPlanListView weeklyPlanListView = new WeeklyPlanListView();
    Scene weeklyPlanListScene = new Scene(weeklyPlanListView.getRoot());
    stage.setScene(weeklyPlanListScene);
    stage.show();
  }

  public void displayWeeklyPlanView(WeeklyList weeklyList) {
    WeeklyPlanView weeklyPlanView = new WeeklyPlanView(weeklyList);
    Scene weeklyScene = new Scene(weeklyPlanView.getRoot());
    stage.setScene(weeklyScene);
    stage.show();
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

  public boolean createEmptyWeeklyList(int weekNumber, int year) {
    try {
      int userId = this.activeUser.getId();
      String query = "select id from week_list where weekNumber=? and user_id=? and year=?";
      PreparedStatement stmt = this.db.prepareStatement(query);
      stmt.setInt(1, weekNumber);
      stmt.setInt(2, userId);
      stmt.setInt(3, year);
      ResultSet rs = stmt.executeQuery();

      if (!rs.next()) {
        String query2 = "INSERT INTO week_list (weekNumber, user_id, year, creation_date) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt2 = this.db.prepareStatement(query2);
        stmt2.setInt(1, weekNumber);
        stmt2.setInt(2, userId);
        stmt2.setInt(3, year);
        java.sql.Date sqlDate = java.sql.Date.valueOf(LocalDate.now());
        stmt2.setDate(4, sqlDate);
        stmt2.executeUpdate();

        rs = stmt.executeQuery();

        if (rs.next()) {
          this.activeUser.createEmptyWeeklyList(weekNumber, year, rs.getInt(1));
          return true;
        }
      }
      return false;
    } catch (SQLException e) {
      return false;
    }
  }

  /**
   * Deletes a Weekly list from the database and the user list. It also deletes
   * the corresponding day lists from the database.
   * 
   * @param listId The id of the list to delete
   * @return true if successful
   */
  public boolean deleteWeeklyList(int listId) {
    try {
      String query = "delete from day_list where week_list_id = ?";
      PreparedStatement stmt = this.db.prepareStatement(query);
      stmt.setInt(1, listId);
      stmt.executeUpdate();

      query = "delete from week_list where id = ?";
      stmt = this.db.prepareStatement(query);
      stmt.setInt(1, listId);
      stmt.executeUpdate();

      this.activeUser.removeWeeklyList(listId);

      return true;

    } catch (SQLException e) {
      System.out.println(e);
      return false;
    }
  }

  /**
   * Adds a recipe to a weekly list in the database and in the user's list.
   * 
   * @param weekId The id of the weekly list in which we add the recipe.
   * @param day    The day for which we add the recipe.
   * @param recipe the recipe to add.
   * @return true if successful.
   */
  public boolean addRecipeToWeeklyList(int weekId, WeekDay day, Recipe recipe) {
    try {
      String query = "INSERT INTO day_list (week_list_id, day, recipe_id) VALUES (?, ?, ?)";
      PreparedStatement stmt = this.db.prepareStatement(query);
      stmt.setInt(1, weekId);
      stmt.setString(2, day.toString());
      stmt.setInt(3, recipe.getId());
      stmt.executeUpdate();

      this.activeUser.addRecipeToWeeklyList(recipe, weekId, day);

      return true;
    } catch (SQLException e) {
      System.out.println(e);
      return false;
    }
  }

  public boolean deleteRecipeFromWeeklyList(int weekId, WeekDay day, Recipe recipe) {
    try {
      String query = "delete from day_list where week_list_id = ? and day = ? and recipe_id = ?";
      PreparedStatement stmt = this.db.prepareStatement(query);
      stmt.setInt(1, weekId);
      stmt.setString(2, day.toString());
      stmt.setInt(3, recipe.getId());
      stmt.executeUpdate();

      this.activeUser.deleteRecipefromWeeklyList(recipe, weekId, day);

      return true;
    } catch (SQLException e) {
      e.printStackTrace();
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

  // TODO: need to add an Tag as well
  public boolean newRecipe(String name, String description, String shortDescription, ObservableList<Integer> tagList,
      ObservableList<Ingredient> ingredientObservableList, String imageURL) {
    try {
      int recipe_id;
      int ingredientIterator = 0;
      String query = "INSERT INTO recipe (name, shortDescription, description, imageURL) VALUES (?, ?, ?, ?)";
      PreparedStatement stmt = this.db.prepareStatement(query);
      stmt.setString(1, name);
      stmt.setString(2, shortDescription);
      stmt.setString(3, description);
      stmt.setString(4, imageURL);
      stmt.executeUpdate();
      query = "SELECT id FROM recipe WHERE name = ?";
      stmt = this.db.prepareStatement(query);
      stmt.setString(1, name);
      ResultSet rs = stmt.executeQuery();
      rs.next();
      recipe_id = rs.getInt(1);
      ObservableList<Ingredient> ingredients = generateIngredient();
      for (Ingredient ingredient : ingredients) {
        if (ingredientObservableList.get(ingredientIterator).getName().equals(ingredient.getName())) {
          query = "INSERT INTO recipe_has_ingredient (recipe_id, ingredient_id) VALUES (?, ?)";
          stmt = this.db.prepareStatement(query);
          stmt.setInt(1, recipe_id);
          stmt.setInt(2, ingredient.getId());
          stmt.executeUpdate();
          ingredientIterator++;
          //System.out.println(ingredientIterator + recipe_id + ingredient.getName() + ingredient.getId());
        }
        /*
         * System.out.println(ingredientIterator + recipe_id + "name: " +
         * ingredient.getName() + " id: " + ingredient.getId() );
         * System.out.println( "ObservableList" +
         * ingredientObservableList.get(ingredientIterator).getName());
         */}
      for (int i : tagList) {
        query = "INSERT INTO recipe_has_tag (recipe_id, tag_id) VALUES (?, ?)";
        stmt = this.db.prepareStatement(query);
        stmt.setInt(1, recipe_id);
        stmt.setInt(2, i);
        stmt.executeUpdate();
      }
      return true;
    } catch (SQLException e) {
      System.out.println(e);
      return false;
    }
  }

  public ObservableList<Tag> generateTag() {
    try {
      String query = "SELECT id, name, user_id FROM tag";
      Statement stmt = this.db.createStatement();

      ResultSet rs = stmt.executeQuery(query);
      ObservableList<Tag> tags = FXCollections.observableArrayList();

      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        int user_id = rs.getInt("user_id");
        tags.add(new Tag(id, name, user_id));
      }

      return tags;
    } catch (SQLException e) {
      return null;
    }
  }

  public ObservableList<Unit> generateUnit() {
    try {
      String query = "SELECT id, name FROM unit";
      Statement stmt = this.db.createStatement();

      ResultSet rs = stmt.executeQuery(query);
      ObservableList<Unit> units = FXCollections.observableArrayList();

      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        units.add(new Unit(id, name));
      }

      return units;
    } catch (SQLException e) {
      return null;
    }
  }

  public boolean newIngredient(ObservableList<Ingredient> ingredientList) throws SQLException {
    try {
      String query = "INSERT INTO ingredient (name, quantity, unit_id) VALUES (?, ?, ?)";
      PreparedStatement stmt = this.db.prepareStatement(query);

      for (Ingredient ingredient : ingredientList) {
        stmt.setString(1, ingredient.getName());
        stmt.setInt(2, ingredient.getQuantity());
        stmt.setInt(3, ingredient.getUnit_id());
        stmt.executeUpdate();
      }
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public ObservableList<Ingredient> generateIngredient() {
    try {
      String query = "SELECT * FROM ingredient";
      Statement stmt = this.db.createStatement();

      ResultSet rs = stmt.executeQuery(query);
      ObservableList<Ingredient> ingredients = FXCollections.observableArrayList();

      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        int quantity = rs.getInt("quantity");
        int unitId = rs.getInt("unit_id");
        ingredients.add(new Ingredient(id, name, quantity, unitId));
      }

      return ingredients;
    } catch (SQLException e) {
      System.out.println(e);
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
   * Find and creates a recipe object from the database using the recipe id.
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
   * Add or remove a recipe from the favourite list depending on if it is already
   * or
   * not.
   *
   * // @param recipeId The id of the recipe to add/delete.
   * 
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

  public void postComment(String commentText, int recipeId) {
    try {

      String query = "INSERT INTO comment (text, user_id, recipe_id) VALUES  (?, ?, ?)";
      PreparedStatement stmt = this.db.prepareStatement(query);
      stmt.setString(1, commentText);
      stmt.setInt(2, this.getActiveUser().getId());
      stmt.setInt(3, recipeId);
      stmt.executeUpdate();
    } catch (SQLException sqlExcept) {
      sqlExcept.printStackTrace();
    }
  }

  public void deleteCommentById(int commentId) {
    try {
      String query = "DELETE FROM comment WHERE id = ?";
      PreparedStatement stmt = this.db.prepareStatement(query);
      stmt.setInt(1, commentId);
      stmt.executeUpdate();
    } catch (SQLException sqlExcept) {
      sqlExcept.printStackTrace();
    }
  }

  public void updateComment(Comment changedComment) {
    try {
      String query = "UPDATE comment set text = ? WHERE id = ?";
      PreparedStatement stmt = this.db.prepareStatement(query);
      stmt.setString(1, changedComment.getText());
      stmt.setInt(2, changedComment.getId());
      stmt.executeUpdate();
      System.out.println(changedComment);
      this.displayRecipeView(changedComment.getRecipeId());
    } catch (SQLException sqlExcept) {
      sqlExcept.printStackTrace();
    }
  }

  /**
   *
   * @param name name of the Tag
   * @user_id is fetched from active user so careful to not use this method if you
   *          don't want the tag to be set to the active User
   * @throws SQLException
   */
  public boolean newTag(String name) throws SQLException {
    try {
      int user_id = activeUser.getId();
      String query = "INSERT INTO tag (name, user_id) VALUES (?, ?)";
      PreparedStatement stmt = this.db.prepareStatement(query);
      stmt.setString(1, name);
      stmt.setInt(2, user_id);
      stmt.executeUpdate();
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
