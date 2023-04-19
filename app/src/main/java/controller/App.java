package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {


  @Override
  public void start(Stage primaryStage) throws Exception {
    //Group -> Scene -> Stage
    //Stage stage = new Stage();
    Group root = new Group();
    Scene scene = new Scene(root, Color.BLACK);

    primaryStage.setTitle("Login Page");

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public Connection dbconnect(){
    Connection conn = null;
    try {
      conn = DriverManager
        .getConnection("jdbc:mysql://localhost/cookbook?user=root&password=ABC&useSSL=false");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return conn;
  }

  public static void main(String[] args) {
    launch();
  }
}
