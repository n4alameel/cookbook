package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * needed for the tableView, to mock the unit_id as a String
 * other changes are welcome
 */
public class IngredientMock {
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

  public String getUnit_id() {
    return unit_id.get();
  }

  public SimpleStringProperty unit_idProperty() {
    return unit_id;
  }

  private SimpleIntegerProperty id;
  public SimpleStringProperty name;
  private SimpleIntegerProperty quantity;
  private SimpleStringProperty unit_id;

  public IngredientMock(String name, int quantity, String unit_id) {
    this.name = new SimpleStringProperty(name);
    this.quantity = new SimpleIntegerProperty(quantity);
    this.unit_id = new SimpleStringProperty(unit_id);
  }
}
