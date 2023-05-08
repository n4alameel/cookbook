package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class AddNewUserController {
    Controller controller = Controller.getInstance();
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private RadioButton yesRB;
    @FXML
    private RadioButton noRB;

    public void addNewUserEvent() throws IOException {
        try {
            ToggleGroup group = new ToggleGroup();
            yesRB.setToggleGroup(group);
            noRB.setToggleGroup(group);
            //if fields are empty - can't create a user
            if(usernameField.getText()=="" || passwordField.getText()=="" || (yesRB.isSelected()==false && noRB.isSelected()==false));
            else {
                boolean ia;
                if(yesRB.isSelected()) ia=true;
                else ia=false;
                controller.addNewUser(usernameField.getText(), passwordField.getText(), ia);
            }
        } catch (Exception e) {
            usernameField.setText("error");
        }
    }
}