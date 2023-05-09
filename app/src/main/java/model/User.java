package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import model.WeeklyList.WeekDay;

public class User {
  private int id;
  private String username;
  private String password;
  private boolean isAdmin = false;
  private ArrayList<Recipe> favouriteList = new ArrayList<Recipe>();
  private ArrayList<WeeklyList> weeklyPlanList = new ArrayList<WeeklyList>();

  public User(
      int id, String username,
      String password,
      boolean isAdmin) {
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

  public ArrayList<Recipe> getFavouriteList() {
    return favouriteList;
  }

  public void setFavouriteList(ArrayList<Recipe> favouriteList) {
    this.favouriteList = favouriteList;
  }

  public ArrayList<WeeklyList> getWeeklyPlanList() {
    return weeklyPlanList;
  }

  /*************************************************************************/

  public void createEmptyWeeklyList(int weekNumber, int year, int id) {
    WeeklyList weekly = new WeeklyList(weekNumber, year, id);
    this.weeklyPlanList.add(weekly);
    Comparator<WeeklyList> comp = Comparator.comparing(WeeklyList::getWeekNumber);
    Collections.sort(this.weeklyPlanList, comp);
  }

  public boolean addRecipeToWeeklyList(Recipe recipe, int weekNumber, WeekDay day) {
    for (WeeklyList list : this.weeklyPlanList) {
      if (list.getWeekNumber() == weekNumber) {
        list.addRecipeToDay(recipe, day);
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

}
