package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumMap;

/**
 * A meal plan for a specific week that will contain several recipes for the 7
 * days of the week.
 */
public class WeeklyList {
  public enum WeekDay {
    Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
  }

  private int id;
  private int weekNumber;
  private int year;
  private LocalDate startDate;
  private LocalDate endDate;
  private LocalDate creationDate;

  /**
   * A map where the keys are the days of the week (from {@code enum WeekDay}) and
   * the values are ArrayLists of recipes.
   */
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

  public LocalDate getStartDate() {
    return startDate;
  }

  public LocalDate getEndDate() {
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
