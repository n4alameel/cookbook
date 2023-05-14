package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Ingredient {
  private int id;
  private int recipeID;
  public String name;
  private int quantity;
  private Unit unit;

  public int getRecipeId() {
    return this.recipeID;
  }

  private enum Unit {
    gram(1), millilitre(2), drop(3), unity(5), pinch(4), grams(6), millilitres(7);

    private final int id;

    private Unit(int id) {
      this.id = id;
    }

    public int getId() {
      return id;
    }

    public static Unit fromId(int id) {
      for (Unit unit : Unit.values()) {
        if (unit.getId() == id) {
          return unit;
        }
      }
      throw new IllegalArgumentException("Invalid unit ID: " + id);
    }
  };

  public Ingredient(int id, String name, int quantity, int unit_id) {
    this.id = id;
    this.name = name;
    this.quantity = quantity;
    this.unit = Unit.fromId(unit_id);
  }

  public Ingredient(int ingredientID, String name, int recipeID) {
    this.id = ingredientID;
    this.name = name;
    this.recipeID = recipeID;
  }

  public int getId() {
    return this.id;
  }



  public int getQuantity() {
    return this.quantity;
  }

  public String getName() {
    return this.name;
  }

  public Unit getUnit() {
    return this.unit;
  }
}
