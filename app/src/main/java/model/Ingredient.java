package model;

public class Ingredient {
  private int id;
  public String name;
  private int quantity;
  private int unit_id;

  public Ingredient(int id, String name, int quantity, int unit_id) {
    this.id = id;
    this.name = name;
    this.quantity = quantity;
    this.unit_id = unit_id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public int getUnit_id() {
    return unit_id;
  }

  public void setUnit_id(int unit_id) {
    this.unit_id = unit_id;
  }
}
