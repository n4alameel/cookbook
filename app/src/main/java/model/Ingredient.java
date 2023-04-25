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

}
