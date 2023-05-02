package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Ingredient {
  private int id;
  public String name;
  private int quantity;
  private Unit unit;

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

  public Ingredient(int id, String name, int quantity, int unit_id){
    this.id = id;
    this.name = name;
    this.quantity = quantity;
    this.unit = Unit.fromId(unit_id);
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
  /*
   * public Ingredient(int id){
   * this.id = id;
   * Connection conn = new App().dbconnect();
   * try {
   * Statement stmt = conn.createStatement();
   * ResultSet rs = stmt.executeQuery("SELECT * FROM ingredient WHERE id = "+id);
   * rs.next();
   * this.name = rs.getString("name");
   * this.quantity = rs.getInt("quantity");
   * this.unit_id = rs.getInt("unit_id");
   * this.unity = Arrays.stream(unit.values())
   * .filter(unit -> unit.getId() == unit_id)
   * .findFirst()
   * .orElseThrow(() -> new IllegalArgumentException("Invalid unit ID: " +
   * unit_id));
   * conn.close();
   * } catch (SQLException e) {
   * System.out.println(e.getMessage());
   * }
   * }
   */
}
