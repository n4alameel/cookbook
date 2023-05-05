package controller;

import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.List;

import com.sun.javafx.binding.StringFormatter;
import com.sun.javafx.print.Units;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import org.w3c.dom.Text;

public class Controller {

  /**
   * /!\ TO MODIFY AFTER EVERY GIT PULL /!\
   * The URL used to connect to the database with JDBC.
   */
  private final String dbUrl = "jdbc:mysql://localhost/cookbook?user=root&password=0000&useSSL=false&allowPublicKeyRetrieval=true";

  private Connection db;
  private model.User activeUser;
  private ArrayList<Recipe> recipeList;

  public ArrayList<Recipe> getRecipeList() {
    return recipeList;
  }

  public Controller() {
    this.db = dbconnect();
    this.activeUser = null;
    //this.recipeList = generateRecipeListFromDb();
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
  //TODO: need to add an Tag as well
  public boolean newRecipe(String name, String description, String shortDescription, ObservableList<Integer> ingredientListInt, ObservableList<Integer> tagList, ObservableList<Ingredient> ingredientObservableList) {
    try {
      int recipe_id;
      int ingredientIterator = 0;
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
      ObservableList<Ingredient> ingredients = generateIngredient();
      for (Ingredient ingredient : ingredients) {
        if(ingredientObservableList.get(ingredientIterator).getName().equals(ingredient.getName())) {
          query = "INSERT INTO recipe_has_ingredient (recipe_id, ingredient_id) VALUES (?, ?)";
          stmt = this.db.prepareStatement(query);
          stmt.setInt(1, recipe_id);
          stmt.setInt(2, ingredient.getId());
          stmt.executeUpdate();
          ingredientIterator++;
          System.out.println(ingredientIterator + recipe_id + ingredient.getName() + ingredient.getId() );
        }
        /*System.out.println(ingredientIterator + recipe_id + "name: " + ingredient.getName() + " id: " + ingredient.getId() );
        System.out.println( "ObservableList" + ingredientObservableList.get(ingredientIterator).getName());
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
      String query = "SELECT id, name FROM tag";
      Statement stmt = this.db.createStatement();

      ResultSet rs = stmt.executeQuery(query);
      ObservableList<Tag> tags = FXCollections.observableArrayList();

      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        tags.add(new Tag(id, name));
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
    }
    catch (Exception e){
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
}
