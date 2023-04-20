package model;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
  private int id;
  private String username;
  private String password;
  private boolean isAdmin = false;

  public User(int id, String username, String password, boolean isAdmin) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.isAdmin = isAdmin;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isAdmin() {
    return isAdmin;
  }

  public void setAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  }

  /*
   * public boolean newRecipe(String name, String description, String detail, int
   * portion,
   * ArrayList<Integer> ingredientList) {
   * int recipe_id;
   * Connection conn = new App().dbconnect();
   * try {
   * Statement stmt = conn.createStatement();
   * stmt.
   * executeUpdate("INSERT INTO recipe (name, shortDescription, description) VALUES ('"
   * + name + "', '" + detail
   * + "', '" + description + "')");
   * ResultSet rs = stmt.executeQuery("SELECT id FROM recipe WHERE name = '" +
   * name + "'");
   * rs.next();
   * recipe_id = rs.getInt("id");
   * for (int i : ingredientList) {
   * stmt.executeUpdate(
   * "INSERT INTO recipe_has_ingredient (recipe_id, ingredient_id) VALUES ('" +
   * recipe_id + "', '" + i + "')");
   * }
   * conn.close();
   * return true;
   * } catch (SQLException e) {
   * System.out.println(e.getMessage());
   * }
   * return false;
   * }
   */

  public boolean createEmptyWeeklyList() {
    return true;
  }

  public boolean addRecipeToWeeklyList(int recipe) {
    return true;
  }

  /*
   * public boolean addFavourite(int recipe) {
   * Connection conn = new App().dbconnect();
   * try {
   * Statement stmt = conn.createStatement();
   * stmt.executeUpdate("INSERT INTO favourite (user_id, recipe_id) VALUES ('" +
   * this.id + "', '" + recipe + "')");
   * conn.close();
   * return true;
   * } catch (SQLException e) {
   * System.out.println(e.getMessage());
   * }
   * return false;
   * }
   */

  /*
   * public boolean removeFavourite(int recipe) {
   * Connection conn = new App().dbconnect();
   * try {
   * Statement stmt = conn.createStatement();
   * stmt.executeUpdate("DELETE FROM favourite WHERE recipe_id = " + recipe +
   * " AND user_id = " + this.id);
   * conn.close();
   * return true;
   * } catch (SQLException e) {
   * System.out.println(e.getMessage());
   * }
   * return false;
   * }
   */

  public boolean generateShoppingList(int weekNumber) {
    return true;
  }

  public void editShoppingList() {
  }

  public boolean eraseShoppingList(int weekNumber) {
    return true;
  }

  /*
   * public boolean sendMessage(int recipe, int receiverId, String string) {
   * Connection conn = new App().dbconnect();
   * try {
   * Statement stmt = conn.createStatement();
   * stmt.
   * executeUpdate("INSERT INTO message (text, isRead, senderId, receiverId, recipeId) VALUES ('"
   * + string
   * + "', '0', '" + this.id + "', '" + receiverId + "', '" + recipe + "')");
   * conn.close();
   * return true;
   * } catch (SQLException e) {
   * System.out.println(e.getMessage());
   * }
   * return false;
   * }
   */

  /*
   * public boolean commentRecipe(int recipe, String string) {
   * Connection conn = new App().dbconnect();
   * try {
   * Statement stmt = conn.createStatement();
   * stmt.executeUpdate("INSERT INTO comment (text, user_id, recipe_id) VALUES ('"
   * + string + "', '" + this.id + "', '"
   * + recipe + "')");
   * conn.close();
   * return true;
   * } catch (SQLException e) {
   * System.out.println(e.getMessage());
   * }
   * return false;
   * }
   */

  /*
   * public void editComment(int id, String string) {
   * Connection conn = new App().dbconnect();
   * try {
   * Statement stmt = conn.createStatement();
   * stmt.executeUpdate("UPDATE comment SET text = '" + string + "' WHERE id = " +
   * id);
   * conn.close();
   * } catch (SQLException e) {
   * System.out.println(e.getMessage());
   * }
   * }
   */

  /*
   * public boolean removeComment(int id) {
   * Connection conn = new App().dbconnect();
   * try {
   * Statement stmt = conn.createStatement();
   * stmt.executeUpdate("DELETE FROM comment WHERE id = " + id);
   * conn.close();
   * return true;
   * } catch (SQLException e) {
   * System.out.println(e.getMessage());
   * }
   * return false;
   * }
   */

  /*
   * public boolean isAuthorOfComment(int id) {
   * Connection conn = new App().dbconnect();
   * try {
   * Statement stmt = conn.createStatement();
   * ResultSet rs = stmt.executeQuery("SELECT * FROM comment WHERE id = " + id);
   * rs.next();
   * if (this.id == rs.getInt("user_id")) {
   * conn.close();
   * return true;
   * }
   * conn.close();
   * return false;
   * } catch (SQLException e) {
   * System.out.println(e.getMessage());
   * }
   * return false;
   * }
   */

}
