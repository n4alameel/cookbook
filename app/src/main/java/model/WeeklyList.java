package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumMap;

public class WeeklyList {
  public enum WeekDay {
    Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
  }

  private int id;
  private int weekNumber;
  private int year;
  private Date startDate;
  private Date endDate;
  private LocalDate creationDate;

  private EnumMap<WeekDay, ArrayList<Recipe>> list = new EnumMap<WeekDay, ArrayList<Recipe>>(WeekDay.class);

  public WeeklyList(int weekNumber, int year, int id, LocalDate creationDate) {
    this.weekNumber = weekNumber;
    this.year = year;
    this.id = id;
    for (WeekDay d : WeekDay.values()) {
      this.list.put(d, new ArrayList<Recipe>());
    }
    this.creationDate = creationDate;
    Time time = new Time();
    this.startDate = time.getMondayFromWeekNumber(weekNumber, year);
    this.endDate = time.getSundayFromWeekNumber(weekNumber, year);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getWeekNumber() {
    return weekNumber;
  }

  public void setWeekNumber(int weekNumber) {
    this.weekNumber = weekNumber;
  }

  public EnumMap<WeekDay, ArrayList<Recipe>> getList() {
    return list;
  }

  public int getYear() {
    return year;
  }

  public Date getStartDate() {
    return startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public LocalDate getCreationDate() {
    return creationDate;
  }

  public void addRecipeToDay(Recipe recipe, WeekDay day) {
    this.list.get(day).add(recipe);
  }

  public void deleteRecipeFromDay(Recipe recipe, WeekDay day) {
    this.list.get(day).remove(recipe);
  }

  public int computeTotalMeal() {
    int res = 0;
    for (ArrayList<Recipe> dayPlan : this.list.values()) {
      res += dayPlan.size();
    }
    return res;
  }

  public int computeDayswithMeals() {
    int res = 0;
    for (ArrayList<Recipe> dayPlan : this.list.values()) {
      if (dayPlan.size() != 0) {
        res++;
      }
    }
    return res;
  }

}
