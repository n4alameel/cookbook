package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
  private VBox usersBox;

  @FXML
  private VBox vbox;

  public void LoadUsers() {
    ArrayList<User> userList = controller.getUsers();
    vbox.setLayoutY(160);
    vbox.setLayoutX(50);
    userList.forEach(user -> {
      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserInfo.fxml"));
        AnchorPane root = loader.load();
        UserInfoController userInfoController = loader.getController();
        userInfoController.setUser(user);
//        userInfoController.updateUser();
//        commentBox.getChildren().add(root);
        vbox.getChildren().add(root);
      } catch (IOException e) {
      }
    });
//    usersBox.getChildren().add(vbox);
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

  public void addNewUserEvent() {
    try {
      controller.displayNewUserView();
    } catch (Exception e) {
    }
  }
}