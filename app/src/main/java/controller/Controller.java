package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> 7f5a3b9a8f619903abfdc5609500763208bfe25d

import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Ingredient;
import model.Recipe;
import model.User;
import view.*;

public class Controller {

  /**
   * /!\ TO MODIFY AFTER EVERY GIT PULL /!\
   * The URL used to connect to the database with JDBC.
   */
<<<<<<< HEAD
  private final String dbUrl = "jdbc:mysql://localhost/cookbook?user=root&password=root&useSSL=false";
=======
  private final String dbUrl = "jdbc:mysql://localhost/cookbook?user=root&password=Grogu&useSSL=false";
>>>>>>> 7f5a3b9a8f619903abfdc5609500763208bfe25d

  /**
   * Used to make this class a singleton
   */
  private static volatile Controller instance;

  private Connection db;
  private model.User activeUser;
  private ArrayList<Recipe> recipeList;
  private Stage stage;

  public ArrayList<Recipe> getRecipeList() {
    return recipeList;
  }

  private Controller() {
    this.db = dbconnect();
    this.activeUser = null;
    this.recipeList = generateRecipeListFromDb();
    this.stage = null;
  }

  /**
   * Get the instance of this class, or create it if it does not exist.
<<<<<<< HEAD
   *
=======
   * 
>>>>>>> 7f5a3b9a8f619903abfdc5609500763208bfe25d
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
    return activeUser;
  }

  public void setActiveUser(model.User activeUser) {
    this.activeUser = activeUser;
  }

  /**
   * Try to connect to the database.
<<<<<<< HEAD
   *
=======
   * 
>>>>>>> 7f5a3b9a8f619903abfdc5609500763208bfe25d
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
<<<<<<< HEAD
   *
=======
   * 
>>>>>>> 7f5a3b9a8f619903abfdc5609500763208bfe25d
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
<<<<<<< HEAD
   *
=======
   * 
>>>>>>> 7f5a3b9a8f619903abfdc5609500763208bfe25d
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

<<<<<<< HEAD
  // public void loadDBContent() throws SQLException {
  // String query = "SELECT * FROM cookbook.recipe";
  // }

  public List<String> selectDataFromDatabase() {
    List<String> data = new ArrayList<>();

    try {
      String query = "select * from cookbook.recipe";
      PreparedStatement statement = dbconnect().prepareStatement(query);
      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        String value = resultSet.getString("name");
        data.add(value);
      }

      resultSet.close();
      statement.close();
      dbClose();

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return data;
  }

  /**
   * Takes all the recipes from the database and store them as Recipe objects in a
   * list.
   *
=======
  /**
   * Takes all the recipes from the database and store them as Recipe objects in a
   * list.
   * 
>>>>>>> 7f5a3b9a8f619903abfdc5609500763208bfe25d
   * @return A list of all existing recipes
   */
  private ArrayList<Recipe> generateRecipeListFromDb() {
    try {
      String query = "select * from recipe";
      Statement stmt = this.db.createStatement();

      ResultSet rs = stmt.executeQuery(query);

      ArrayList<Recipe> recipeList = new ArrayList<Recipe>();

      while (rs.next()) {
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

        Recipe r = createRecipe(recipeId, rs, ingredientList);
        recipeList.add(r);
      }

      return recipeList;

    } catch (SQLException e) {
      return null;
    }
  }

  /**
   * Create an Ingredient object from a MySQL query result.
<<<<<<< HEAD
   *
=======
   * 
>>>>>>> 7f5a3b9a8f619903abfdc5609500763208bfe25d
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
<<<<<<< HEAD
   *
=======
   * 
>>>>>>> 7f5a3b9a8f619903abfdc5609500763208bfe25d
   * @param recipeId       The recipe ID
   * @param rs             The query result
   * @param ingredientList The list of all ingredients used in the recipe
   * @return A Recipe object
   */
  private Recipe createRecipe(int recipeId, ResultSet rs, ArrayList<Ingredient> ingredientList) {
    try {
      Recipe r = new Recipe(recipeId, rs.getString(2), rs.getString(3), rs.getString(4), ingredientList);
      return r;
    } catch (SQLException e) {
      System.out.println("nope");
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
  public void displayRecipeView(Recipe r) {
    RecipeView recipeView = new RecipeView();
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

}
