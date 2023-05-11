package model;

import java.util.ArrayList;
import javafx.util.Pair;

public class ShoppingList {

  public ArrayList<Pair<Ingredient, Integer>> list = new ArrayList<Pair<Ingredient, Integer>>();
  private int week_number;
  private int user_id;

  public ShoppingList(int week_number, int user_id) {
    this.week_number = week_number;
    this.user_id = user_id;
  }

  public int getWeek() {
    return this.week_number;
  }

}
