package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import view.RecipeWindow;
public class App  extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {


    RecipeWindow recipeWindow = new RecipeWindow();
    recipeWindow.show();
  }

  public Connection dbconnect(){
    Connection conn = null;
    try {
      conn = DriverManager
              .getConnection("jdbc:mysql://localhost/cookbook?user=root&password=Ira.ko03&useSSL=false");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return conn;
  }
  public static void main(String[] args) {
    launch(args);
  }

}
