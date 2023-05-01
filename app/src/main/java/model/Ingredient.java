package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Ingredient {
  private SimpleIntegerProperty id;
  public SimpleStringProperty name;
  private SimpleStringProperty quantity;
  private SimpleStringProperty unit_id;
  //TODO: Delete new properties after trying if it works.

  public int getId() {
    return id.get();
  }

  public SimpleIntegerProperty idProperty() {
    return id;
  }

  public void setId(int id) {
    this.id.set(id);
  }

  public String getName() {
    return name.get();
  }

  public SimpleStringProperty nameProperty() {
    return name;
  }

  public void setName(String name) {
    this.name.set(name);
  }

  public String getQuantity() {
    return quantity.get();
  }

  public SimpleStringProperty quantityProperty() {
    return quantity;
  }

  public void setQuantity(String quantity) {
    this.quantity.set(quantity);
  }

  public String getUnit_id() {
    return unit_id.get();
  }

  public SimpleStringProperty unit_idProperty() {
    return unit_id;
  }

  public void setUnit_id(String unit_id) {
    this.unit_id.set(unit_id);
  }
/**
  public Ingredient(int id, String name, int quantity, int unit_id) {
    this.id = new SimpleIntegerProperty(id);
    this.name = new SimpleStringProperty(name);
    this.quantity = new SimpleIntegerProperty(quantity);
    this.unit_id = new SimpleIntegerProperty(unit_id);
  }
 */

  //TODO: delete after trying it out.
  public Ingredient(int id, String name, String quantity, String unit_id) {
    this.id = new SimpleIntegerProperty(id);
    this.name = new SimpleStringProperty(name);
    this.quantity = new SimpleStringProperty(quantity);
    this.unit_id = new SimpleStringProperty(unit_id);
  }
}
