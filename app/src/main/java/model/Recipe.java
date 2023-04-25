package model;

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
  private ArrayList<Tag> tagList = new ArrayList<Tag>();

  public Recipe(int id, String name, String description, String detail, ArrayList<Ingredient> ingredientList) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.detail = detail;
    this.ingredientList = ingredientList;
  }

  public boolean addTag(Tag tag) {
    return true;
  }
}
