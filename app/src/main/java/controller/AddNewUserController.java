package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AddNewUserController {
    Controller controller = Controller.getInstance();
    @FXML
    private TextField usernameTF;
    @FXML
    private PasswordField passwordTF;
    @FXML
    private RadioButton yesRB;
    @FXML
    private RadioButton noRB;
    @FXML
    private Button addUser;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label isAdminLabel;
    @FXML
    private Label imageUrlLabel;
    @FXML
    private ImageView image;
    private String imagePath;

    public void initializeToggleGroup() {
        ToggleGroup group = new ToggleGroup();
        yesRB.setToggleGroup(group);
        noRB.setToggleGroup(group);
        addUser.setText("Add user");
        image.setFitHeight(200);
    }

    public void initializeUserChange(int id, String username, String password, boolean isAdmin, String imageUrl) {
        initializeToggleGroup();
        addUser.setText("Change user");
        usernameTF.setText(username);
        passwordTF.setText(password);
        if(isAdmin==false) noRB.setSelected(true);
        else yesRB.setSelected(true);
        addUser.setOnAction(event -> {
            changeUser(id);
        });
        Image img = new Image(imageUrl);
        image.setImage(img);
        imagePath = imageUrl;
    }

    public void newUserEvent(){
        String username = usernameTF.getText();
        String password = passwordTF.getText();
        try {
            //if fields are empty - can't create a user
            if(username=="" || password=="" || (yesRB.isSelected()==false && noRB.isSelected()==false) || imagePath=="");
            else {
                boolean ia;
                if(yesRB.isSelected()) ia=true;
                else ia=false;
                controller.addNewUser(username, password, ia, imagePath);
                controller.displayUsersView();
            }
        } catch (Exception e) {
            usernameTF.setText("error");
        }
    }
    public void changeUser(int id) {
        String username = usernameTF.getText();
        String password = passwordTF.getText();
        try {
            //if fields are empty - can't create a user
            if(username=="" || password=="" || (yesRB.isSelected()==false && noRB.isSelected()==false) || imagePath=="");
            else {
                boolean ia;
                if(yesRB.isSelected()) ia=true;
                else ia=false;
                controller.changeUser(id, username, password, ia, imagePath);
                controller.displayUsersView();
            }
        } catch (Exception e) {
            usernameTF.setText("error");
        }
    }

    public void browsePicture() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(Controller.getInstance().getStage());
        imagePath = file.toURI().toString();
        System.out.println(imagePath);
        Image img = new Image(imagePath);
        image.setImage(img);
    }
}