package model;

import java.util.ArrayList;

public class Recipe {
  private int id;
  private String name;
  private String description;
  private String shortDescription;
  private String imageUrl;
  private ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
  private ArrayList<Comment> commentList = new ArrayList<Comment>();
  private ArrayList<Tag> tagList = new ArrayList<Tag>();

  public Recipe(int id, String name, String description, String shortDescription, String imageUrl,
      ArrayList<Ingredient> ingredientList, ArrayList<Comment> commentList, ArrayList<Tag> tagList) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.shortDescription = shortDescription;
    this.imageUrl = imageUrl;
    this.ingredientList = ingredientList;
    this.commentList = commentList;
    this.tagList = tagList;
  }

  // mocking for loading of a new recipe
  public Recipe(String name) {
    this.name = name;
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

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
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

}
