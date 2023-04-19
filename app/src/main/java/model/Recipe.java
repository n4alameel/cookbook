package model;

import controller.App;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Ingredient;

public class Recipe {
    private int id;
    private String name;
    private String description;
    private String detail;
    private int portions = 2;
    private ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();

    public Recipe(int id){
        this.id = id;
        Connection conn = new App().dbconnect();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM recipe WHERE id = "+id);
            rs.next();
            this.name = rs.getString("name");
            this.description = rs.getString("description");
            this.detail = rs.getString("shortDescription");
            rs = stmt.executeQuery("SELECT * FROM recipe_has_ingredient WHERE recipe_id = "+id);
            while (rs.next()) {
                Ingredient ingredient = new Ingredient(rs.getInt("ingredient_id"));
                this.ingredientList.add(ingredient);
                System.out.println(ingredient.name);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean addTag(Tag tag){
        return true;
    }
}
