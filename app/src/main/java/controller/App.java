package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {


  @Override
  public void start(Stage primaryStage) throws Exception {
  /*  VBox root = new VBox();
    root.setPadding(new Insets(5));
    Label title = new Label("JavaFX");
    /*Label mysql;

    try {
      Connection conn = DriverManager
        .getConnection("jdbc:mysql://localhost/cookbook?user=root&password=ABC&useSSL=false");
      mysql = new Label("Driver found and connected");
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE id = 1");
      rs.next();
      System.out.println(rs.getString("username")); //Exemple of a select from the database.
    } catch (SQLException e) {
      mysql = new Label("An error has occurred: " + e.getMessage());
    }*/

      //   root.getChildren().addAll(title/*, mysql*/);
      Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("HomeWindow.fxml"));
      Scene scene = new Scene(root);
       primaryStage.setScene(scene);
      //scene.getStylesheets().add(css);

      primaryStage.setTitle("JavaFX");
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
