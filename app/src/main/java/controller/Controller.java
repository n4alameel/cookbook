package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.xdevapi.PreparableStatement;

import model.User;

public class Controller {

  private Connection db;
  private model.User activeUser;

  public Controller() {
    this.db = dbconnect();
    this.activeUser = null;
  }

  public model.User getActiveUser() {
    return activeUser;
  }

  public void setActiveUser(model.User activeUser) {
    this.activeUser = activeUser;
  }

  public Connection dbconnect() {
    Connection conn = null;
    try {
      conn = DriverManager
          .getConnection("jdbc:mysql://localhost/cookbook?user=root&password=ABC&useSSL=false");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return conn;
  }

  public boolean login(String username, String password) {
    try {
      String query = "select id from cookbook.user where username=? and password=?";
      PreparedStatement stmt = this.db.prepareStatement(query);
      stmt.setString(1, username);
      stmt.setString(2, password);

      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        int userId = Integer.parseInt(rs.getString(1));
        this.activeUser = this.createUser(rs, userId);
        if (this.activeUser == null) {
          return false;
        }
        return true;
      } else {
        return false;
      }
    } catch (SQLException e) {
      return false;
    }

  }

  private User createUser(ResultSet rs, int id) {
    try {
      return new User(id, rs.getString(2), rs.getString(3), Boolean.parseBoolean(rs.getString(4)));
    } catch (SQLException e) {
      return null;
    }
  }

}
