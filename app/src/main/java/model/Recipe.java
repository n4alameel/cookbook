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
  private String shortDescription;
  private int portions = 2;
  private ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
  private ArrayList<Tag> tagList = new ArrayList<Tag>();

  public Recipe(int id, String name, String description, String shortDescription,
      ArrayList<Ingredient> ingredientList) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.shortDescription = shortDescription;
    this.ingredientList = ingredientList;
  }

  public int getPortions() {
    return portions;
  }

  public void setPortions(int portions) {
    this.portions = portions;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getShortDescription() {
    return shortDescription;
  }

  public void setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
  }

  public ArrayList<Ingredient> getIngredientList() {
    return ingredientList;
  }

  public ArrayList<Tag> getTagList() {
    return tagList;
  }

  public boolean addTag(Tag tag) {
    return true;
  }

  @Override
  public String toString() {
    return "Recipe [id=" + id + ", name=" + name + ", description=" + description + ", shortDescription="
        + shortDescription + "]";
  }

}
