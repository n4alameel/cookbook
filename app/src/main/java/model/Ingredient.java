package model;

import controller.App;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class Ingredient {
    private int id;
    public String name;
    private int quantity;
    private enum unit {
        gram(1), millilitre(2), drop(3), unity(5), pinch(4), grams(6), millilitres(7);
        private final int id;
        private unit(int id){
            this.id = id;
        }
        public int getId(){
            return id;
        }
    };
    private unit unity;
    private int unit_id;

    public Ingredient(int id){
        this.id = id;
        Connection conn = new App().dbconnect();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ingredient WHERE id = "+id);
            rs.next();
            this.name = rs.getString("name");
            this.quantity = rs.getInt("quantity");
            this.unit_id = rs.getInt("unit_id");
            this.unity = Arrays.stream(unit.values())
                   .filter(unit -> unit.getId() == unit_id)
                   .findFirst()
                   .orElseThrow(() -> new IllegalArgumentException("Invalid unit ID: " + unit_id));
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
