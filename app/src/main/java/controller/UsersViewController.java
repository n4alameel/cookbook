package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JButton;
import javafx.scene.shape.Circle;
import javafx.scene.paint.ImagePattern;

import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import model.*;
import view.AddNewUserView;
import javafx.scene.paint.Color;

public class UsersViewController {
  Controller controller = Controller.getInstance();
  @FXML
  private AnchorPane anchorPane;
  private VBox vbox = new VBox();

  public void LoadUsers() {
    ArrayList<User> userList = controller.getUsers();
    vbox.setLayoutY(160);
    vbox.setLayoutX(50);
    for (User user : userList) {
      String username = user.getUsername();
      String password = (String) user.getPassword();
      Boolean isAdmin1 = user.isAdmin();
      String isAdmin;
      if (isAdmin1 == false)
        isAdmin = "Not admin";
      else
        isAdmin = "Admin";
      Button buttonDelete = new Button("Delete");
      Button buttonChange = new Button("Change");
      Label un = new Label(username);
      Label pd = new Label(password);
      Label ia = new Label(isAdmin);
      Circle circle = new Circle(25, 25, 25);
      ImageView img = new ImageView(user.getImageUrl());
      img.setFitWidth(60);
      img.setClip(circle);
      img.setFitHeight(50);
      AnchorPane userAnchorPane = new AnchorPane(img, un, pd, ia, buttonChange, buttonDelete);
      img.setLayoutX(50);
      un.setLayoutX(300);
      pd.setLayoutX(600);
      ia.setLayoutX(700);
      img.setLayoutY(img.getLayoutY() + 5);
      un.setLayoutY(img.getLayoutY() + 5);
      pd.setLayoutY(img.getLayoutY() + 5);
      ia.setLayoutY(img.getLayoutY() + 5);
      userAnchorPane.setMaxHeight(50);
      buttonChange.setLayoutX(900);
      buttonDelete.setLayoutX(1000);
      un.setFont(new Font(30));
      pd.setFont(new Font(30));
      ia.setFont(new Font(30));
      userAnchorPane.setBorder(
          new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
      userAnchorPane.setId(Integer.toString(user.getId()));
      buttonDelete.setOnAction(event -> {
        deleteUserEvent(Integer.valueOf(userAnchorPane.getId()));
      });
      buttonChange.setOnAction(event -> {
        changeUserEvent(Integer.valueOf(userAnchorPane.getId()));
      });
      vbox.getChildren().addAll(userAnchorPane);
    }
    anchorPane.getChildren().add(vbox);
  }

  public void addNewUserEvent() {
    try {
      controller.displayNewUserView();
    } catch (Exception e) {
    }
  }

  public void deleteUserEvent(int id) {
    try {
      controller.deleteUser(id);
      vbox.getChildren().clear();
      LoadUsers();
    } catch (Exception e) {
    }
  }

  public void changeUserEvent(int id) {
    try {
      User user = controller.getUserById(id);
      controller.displayNewUserView(id, user.getUsername(), user.getPassword(), user.isAdmin(), user.getImageUrl());
    } catch (Exception e) {
    }
  }

  public void homeViewEvent() throws IOException {
    controller.displayHomeView(false);
  }
}