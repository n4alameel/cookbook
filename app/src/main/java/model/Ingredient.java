package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Ingredient {
  private SimpleIntegerProperty id;
  public SimpleStringProperty name;
  private SimpleIntegerProperty quantity;
  private SimpleIntegerProperty unit_id;

  public Ingredient() {

  }
  public Ingredient(int id, String name, int quantity, int unit_id) {
    this.id = new SimpleIntegerProperty(id);
    this.name = new SimpleStringProperty(name);
    this.quantity = new SimpleIntegerProperty(quantity);
    this.unit_id = new SimpleIntegerProperty(unit_id);
  }
}
