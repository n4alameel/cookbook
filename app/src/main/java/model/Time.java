package model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;

public class Time {
  private GregorianCalendar calendar = new GregorianCalendar();

  public Time() {
    calendar.setFirstDayOfWeek(GregorianCalendar.MONDAY);
    calendar.setMinimalDaysInFirstWeek(4);
  }

  public LocalDate getMondayFromWeekNumber(int weeknumber, int year) {
    calendar.setWeekDate(year, weeknumber, GregorianCalendar.MONDAY);
    return calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
  }

  public LocalDate getSundayFromWeekNumber(int weeknumber, int year) {
    calendar.setWeekDate(year, weeknumber, GregorianCalendar.SUNDAY);
    return calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
  }

  public int getWeekNumberFromDate(Date date) {
    calendar.setTime(date);
    return calendar.get(GregorianCalendar.WEEK_OF_YEAR);
  }

  public LocalDate getCurrentDate() {
    return LocalDate.now();
  }

}
