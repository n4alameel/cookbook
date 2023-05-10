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

    public void LoadUsers(){
        ArrayList<User> userList = controller.getUsers();
        vbox.setLayoutY(160);
        vbox.setLayoutX(50);
        for (User user: userList) {
            String username = user.getUsername();
            String password = (String) user.getPassword();
            String isAdmin1 = Boolean.toString(user.isAdmin());
            String isAdmin;
            if(isAdmin1=="false") isAdmin="Not admin";
            else isAdmin="Admin";
            //ImageView image = new ImageView(controller.getPicture());
            Button buttonDelete = new Button("Delete");
            Button buttonChange = new Button("Change");
            Label un = new Label(username);
            Label pd = new Label(password);
            Label ia = new Label(isAdmin);
            buttonDelete.setLayoutX(1100);
            System.out.println(Integer.toString((100-username.length())));
            HBox hbox = new HBox(un, pd, ia, buttonChange, buttonDelete);
            un.setFont(new Font(30));
            pd.setFont(new Font(30));
            ia.setFont(new Font(30));
            hbox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            hbox.setMargin(un, new Insets(0, 100, 0, 10));
            hbox.setMargin(pd, new Insets(0, 100, 0, 10));
            hbox.setMargin(pd, new Insets(0, 100, 0, 10));
            hbox.setMargin(buttonChange, new Insets(10, 100, 10, 200));
            hbox.setMargin(buttonDelete, new Insets(10, 10, 10, 10));
            hbox.setId(Integer.toString(user.getId()));
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

    public void addNewUserEvent(){
        try {
            controller.displayNewUserView();
        } catch (Exception e) {
        }
    }

    public void deleteUserEvent(int id){
        try {
            controller.deleteUser(id);
            vbox.getChildren().clear();
            LoadUsers();
        } catch (Exception e) {
        }
    }
    public void changeUserEvent(int id){
        try {
            User user = controller.getUserById(id);
            System.out.println(user.getUsername()+ user.getPassword()+ user.isAdmin());
            controller.displayNewUserView(id, user.getUsername(), user.getPassword(), user.isAdmin());
        } catch (Exception e) {
        }
    }
}