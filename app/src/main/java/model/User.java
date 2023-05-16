package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import model.WeeklyList.WeekDay;

public class User {
  private int id;
  private String username;
  private String password;
  private String imageUrl;
  private boolean isAdmin = false;
  private ArrayList<Recipe> favouriteList = new ArrayList<Recipe>();
  private ArrayList<WeeklyList> weeklyPlanList = new ArrayList<WeeklyList>();

  public User(int id, String username, String password, boolean isAdmin, String imageUrl) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.isAdmin = isAdmin;
    this.imageUrl = imageUrl;
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

  public ArrayList<Recipe> getFavouriteList() {
    return favouriteList;
  }

  public void setFavouriteList(ArrayList<Recipe> favouriteList) {
    this.favouriteList = favouriteList;
  }

  public ArrayList<WeeklyList> getWeeklyPlanList() {
    return weeklyPlanList;
  }

  public void setWeeklyPlanList(ArrayList<WeeklyList> weeklyPlanList) {
    this.weeklyPlanList = weeklyPlanList;
  }

  /*************************************************************************/

  public void createEmptyWeeklyList(int weekNumber, int year, int id) {
    WeeklyList weekly = new WeeklyList(weekNumber, year, id, LocalDate.now());
    this.weeklyPlanList.add(weekly);
    Comparator<WeeklyList> comp = Comparator.comparing(WeeklyList::getWeekNumber);
    Collections.sort(this.weeklyPlanList, comp);
  }
  public String getImageUrl() { return imageUrl;}

  public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

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

  public boolean removeWeeklyList(int listId) {
    for (WeeklyList list : this.weeklyPlanList) {
      if (list.getId() == listId) {
        this.weeklyPlanList.remove(list);
        return true;
      }
    }
    return false;
  }

  public boolean addRecipeToWeeklyList(Recipe recipe, int weekId, WeekDay day) {
    for (WeeklyList list : this.weeklyPlanList) {
      if (list.getId() == weekId) {
        list.addRecipeToDay(recipe, day);
        return true;
      }
    }
    return false;
  }

  public boolean deleteRecipefromWeeklyList(Recipe recipe, int weekId, WeekDay day) {
    for (WeeklyList list : this.weeklyPlanList) {
      if (list.getId() == weekId) {
        list.getList().get(day).remove(recipe);
        return true;
      }
    }
    return false;
  }

  public void addFavourite(Recipe recipe) {
    favouriteList.add(recipe);
  }

  public void removeFavourite(Recipe recipe) {
    for (Recipe r : this.favouriteList) {
      if (r.getId() == recipe.getId()) {
        this.favouriteList.remove(r);
        return;
      }
    }
  }

  public boolean isFavourite(Recipe recipe) {
    for (Recipe r : this.favouriteList) {
      if (r.getId() == recipe.getId()) {
        return true;
      }
    }
    return false;
  }

  public boolean clearFavourite() {
    return favouriteList.removeAll(favouriteList);
  }

  public boolean generateShoppingList(int weekNumber) {
    return true;
  }

  public void editShoppingList() {
  }

  public boolean eraseShoppingList(int weekNumber) {
    return true;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", username=" + username + ", password=" + password + ", isAdmin=" + isAdmin
        + "]";
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
