package model;

import java.util.ArrayList;

public class WeeklyList {
  private int id;
  private int weeklyNumber;
  private int isVisible;
  private ArrayList<ArrayList<Recipe>> weekly = new ArrayList<ArrayList<Recipe>>(7);

  public WeeklyList(int weeklyNumber, int isVisible) {
    this.weeklyNumber = weeklyNumber;
    this.isVisible = isVisible;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

}
