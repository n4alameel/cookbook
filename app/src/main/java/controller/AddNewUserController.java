package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;

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

    public void initializeToggleGroup() {
        ToggleGroup group = new ToggleGroup();
        yesRB.setToggleGroup(group);
        noRB.setToggleGroup(group);
        addUser.setText("Add user");
        usernameLabel.setFont(new Font(20));
        passwordLabel.setFont(new Font(20));
        isAdminLabel.setFont(new Font(20));
        yesRB.setFont(new Font(20));
        noRB.setFont(new Font(20));
    }

    public void initializeUserChange(int id, String username, String password, boolean isAdmin) {
        ToggleGroup group = new ToggleGroup();
        yesRB.setToggleGroup(group);
        noRB.setToggleGroup(group);
        addUser.setText("Change user");
        usernameLabel.setFont(new Font(20));
        passwordLabel.setFont(new Font(20));
        isAdminLabel.setFont(new Font(20));
        yesRB.setFont(new Font(20));
        noRB.setFont(new Font(20));
        usernameTF.setText(username);
        passwordTF.setText(password);
        System.out.println(Boolean.toString(isAdmin));
        if(isAdmin==false) noRB.setSelected(true);
        else yesRB.setSelected(true);
        addUser.setOnAction(event -> {
            changeUser(id);
        });
    }

    public void newUserEvent(){
        String username = usernameTF.getText();
        String password = passwordTF.getText();
        try {
            //if fields are empty - can't create a user
            if(username=="" || password=="" || (yesRB.isSelected()==false && noRB.isSelected()==false));
            else {
                boolean ia;
                if(yesRB.isSelected()) ia=true;
                else ia=false;
                controller.addNewUser(username, password, ia);
                controller.displayUsersView();
                Stage stage = (Stage) usernameTF.getScene().getWindow();
                stage.close();
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
            if(username=="" || password=="" || (yesRB.isSelected()==false && noRB.isSelected()==false));
            else {
                boolean ia;
                if(yesRB.isSelected()) ia=true;
                else ia=false;
                controller.changeUser(id, username, password, ia);
                controller.displayUsersView();
                Stage stage = (Stage) usernameTF.getScene().getWindow();
                stage.close();
            }
        } catch (Exception e) {
            usernameTF.setText("error");
        }
    }
}