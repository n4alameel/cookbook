package model;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

public class Recipe {
  private int id;
  private String name;
  private String description;
  private String shortDescription;
  private int portions = 2;
  private ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
  private ArrayList<Comment> commentList = new ArrayList<Comment>();
  private ArrayList<Tag> tagList = new ArrayList<Tag>();
  private InputStream inputStream;

  public Recipe(int id, String name, String description, String shortDescription,
      ArrayList<Ingredient> ingredientList, ArrayList<Comment> commentList, ArrayList<Tag> tagList) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.shortDescription = shortDescription;
    this.ingredientList = ingredientList;
    this.commentList = commentList;
    this.tagList = tagList;
  }
  public Recipe(int id, String name, String description, String shortDescription,
                ArrayList<Ingredient> ingredientList, ArrayList<Comment> commentList, ArrayList<Tag> tagList, InputStream inputStream) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.shortDescription = shortDescription;
    this.ingredientList = ingredientList;
    this.commentList = commentList;
    this.tagList = tagList;
    this.inputStream = inputStream;
  }

  // mocking for loading of a new recipe
  public Recipe(String name) {
    this.name = name;
  }


  public int getPortions() {
    return portions;
  }

  public void setPortions(int portions) {
    this.portions = portions;
  }

  public Recipe(String name, String description) {
    this.name = name;
    this.description = description;
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

  public ArrayList<Comment> getCommentList() {
    return commentList;
  }

  public void setCommentList(ArrayList<Comment> commentList) {
    this.commentList = commentList;
  }

  @Override
  public String toString() {
    return "Recipe [id=" + id + ", name=" + name + ", description=" + description + ", shortDescription="
        + shortDescription + "]";
  }

  public InputStream getBlob() throws SQLException {
    return inputStream;
  }
}
