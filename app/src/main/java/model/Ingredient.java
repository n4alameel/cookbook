package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Ingredient {
  public int getId() {
    return id.get();
  }

  public SimpleIntegerProperty idProperty() {
    return id;
  }

  public String getName() {
    return name.get();
  }

  public SimpleStringProperty nameProperty() {
    return name;
  }

  public int getQuantity() {
    return quantity.get();
  }

  public SimpleIntegerProperty quantityProperty() {
    return quantity;
  }

  public int getUnit_id() {
    return unit_id.get();
  }

  public SimpleIntegerProperty unit_idProperty() {
    return unit_id;
  }

  private SimpleIntegerProperty id;
  public SimpleStringProperty name;
  private SimpleIntegerProperty quantity;
  private SimpleIntegerProperty unit_id;
  private SimpleStringProperty unitName;

  private int ingredientID;

  public String getIngredientName() {
    return ingredientName;
  }

  private String ingredientName;
  private int recipeID;

  public Ingredient(int id, String name, int quantity, String unitName) {
    this.id = new SimpleIntegerProperty(id);
    this.name = new SimpleStringProperty(name);
    this.quantity = new SimpleIntegerProperty(quantity);
    this.unitName = new SimpleStringProperty(unitName);
  }

  public Ingredient(int id, String name, int quantity, int unit_id) {
    this.id = new SimpleIntegerProperty(id);
    this.name = new SimpleStringProperty(name);
    this.quantity = new SimpleIntegerProperty(quantity);
    this.unit_id = new SimpleIntegerProperty(unit_id);
  }

  public Ingredient(String name, int quantity, int unit_id) {
    this.name = new SimpleStringProperty(name);
    this.quantity = new SimpleIntegerProperty(quantity);
    this.unit_id = new SimpleIntegerProperty(unit_id);
  }

  public int getRecipeID() {
    return recipeID;
  }

  public Ingredient(int ingredientID, String name, int recipeID) {
    this.ingredientID = ingredientID;
    this.ingredientName = name;
    this.recipeID = recipeID;
  }


  public String getUnitName() {
    return unitName.get();
  }

  public SimpleStringProperty unitNameProperty() {
    return unitName;
  }

  public void setUnitName(String unitName) {
    this.unitName.set(unitName);
  }
}
