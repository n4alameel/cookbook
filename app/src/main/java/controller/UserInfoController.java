package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import model.Comment;
import model.User;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class UserInfoController {
    @FXML
    public Button buttonChange;
    @FXML
    public Button buttonDelete;
    Controller controller = Controller.getInstance();
    @FXML
    public Label pd;
    @FXML
    public Label ia;
    @FXML
    public Pane imgBox;
    @FXML
    private AnchorPane userAnchorPane;
    @FXML
    public Label un;
    public AnchorPane setUser(User currentUser){
        User user = currentUser;
        String username = user.getUsername();
        String password = (String) user.getPassword();
        Boolean isAdmin1 = user.isAdmin();
        String isAdmin;
        if (isAdmin1 == false)
            isAdmin = "Not admin";
        else
            isAdmin = "Admin";
        un.setText(username);
        pd.setText(password);
        ia.setText(isAdmin);
        Circle circle = new Circle(25, 25, 25);
        Image image = new Image(user.getImageUrl());

        // Create the ImageView
        ImageView img = new ImageView(image);
        img.setFitWidth(60);
        img.setClip(circle);
        img.setFitHeight(50);
        imgBox.getChildren().add(img);
        img.setLayoutY(img.getLayoutY() + 5);
        un.setLayoutY(img.getLayoutY() + 5);
        pd.setLayoutY(img.getLayoutY() + 5);
        ia.setLayoutY(img.getLayoutY() + 5);
        userAnchorPane.setMaxHeight(50);
        userAnchorPane.setId(Integer.toString(user.getId()));
        System.out.println(userAnchorPane.getId());
        buttonDelete.setOnAction(event -> {
            deleteUserEvent(Integer.valueOf(userAnchorPane.getId()));
        });
        buttonChange.setOnAction(event -> {
            changeUserEvent(Integer.valueOf(userAnchorPane.getId()));
        });
        return userAnchorPane;
    }

    public void deleteUserEvent(int id) {
        try {
            controller.deleteUser(id);
//            vbox.getChildren().clear();
            controller.displayUsersView();
        } catch (Exception e) {
        }
    }

    public void changeUserEvent(int id) {
        try {
            User user = this.controller.getUserById(id);
            this.controller.displayNewUserView(id, user.getUsername(), user.getPassword(), user.isAdmin(), user.getImageUrl());
        } catch (Exception e) {
        }
    }
}
