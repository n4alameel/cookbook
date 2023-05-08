package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.*;

public class UsersViewController {
  Controller controller = Controller.getInstance();
  @FXML
  private AnchorPane anchorPane;

  // add admin panel to header
  // what should we do to change pop-ups
  // how to show unfilled field

  public void LoadUsers() {
    ArrayList<User> userList = controller.getUsers();
    System.out.println(userList.toString());
    VBox vbox = new VBox();
    for (User user : userList) {
      String username = user.getUsername();
      String password = (String) user.getPassword();
      Button buttonDelete = new Button("Delete");
      Button buttonChange = new Button("Change");
      HBox hbox = new HBox(new Label(username), new Label(password), buttonChange, buttonDelete);
      hbox.setId(Integer.toString(user.getId() - 1));
      buttonDelete.setOnAction(event -> {
        deleteUserEvent(Integer.valueOf(hbox.getId()));
      });
      buttonChange.setOnAction(event -> {
        changeUserEvent(Integer.valueOf(hbox.getId()));
      });
      vbox.getChildren().addAll(hbox);
    }
    anchorPane.getChildren().add(vbox);
  }

  public void addNewUserEvent() {
    try {

    } catch (Exception e) {
    }
  }

  public void deleteUserEvent(int id) {
    try {
      controller.deleteUser(id);
      LoadUsers();
    } catch (Exception e) {
    }
  }

  public void changeUserEvent(int id) {
    try {

    } catch (Exception e) {
    }
  }
}