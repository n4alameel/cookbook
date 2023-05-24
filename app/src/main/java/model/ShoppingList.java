package model;

import java.util.ArrayList;
import javafx.util.Pair;
import org.checkerframework.checker.units.qual.A;

public class ShoppingList {

  public ArrayList<Pair<Ingredient, Integer>> list = new ArrayList<Pair<Ingredient, Integer>>();
  private ArrayList<Recipe> recipeList = new ArrayList<>();
  private ArrayList<Integer> portionsList = new ArrayList<>();
  private ArrayList<Ingredient> ingredientsList = new ArrayList<>();
  private int week_number;
  private int user_id;

  public ShoppingList() { }

  public void addRecipeToShoppingList(Recipe r) {
    recipeList.add(r);
  }

  public void addPortions(int i) {
    portionsList.add(i);
  }

  public void addIngredients(Ingredient in) {
    ingredientsList.add(in);
  }

  public void editIngredientsList(int i, int q) {
    ingredientsList.get(i).setQuantity(ingredientsList.get(i).getQuantity() + q);
  }

  public ArrayList<Recipe> getRecipeFromShoppingList() { return recipeList; }

  public ArrayList<Integer> getPortionsList() { return portionsList; }

  public ArrayList<Ingredient> getIngredientsList() { return ingredientsList; }

  public int getWeek() {
    return this.week_number;
  }

}
