package controller;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import java.io.IOException;

import model.Message;
import model.Recipe;

public class SendMessageViewController {
    private Parent root;
    Controller controller = Controller.getInstance();

    @FXML
    private Label recipeName;

    @FXML
    private Button sendButton;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextArea messageTextArea;

    private Recipe recipe;
    private int senderId;
    private int receiverId;

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    @FXML
    private void sendMessage() throws IOException {
        String messageText = messageTextArea.getText();
        String selectedUser = choiceBox.getValue();
        int selectedUserId = controller.getUserIdFromUsername(selectedUser);
        int senderId = controller.getActiveUser().getId();
        System.out.println("Sending message: Recipe ID=" + this.recipe.getId() + ", Sender ID=" + senderId + ", Receiver ID=" + selectedUserId + ", Message Text=" + messageText);
        controller.sendMessage(recipe.getId(), messageText, senderId, selectedUserId);
    }

    public void initialize(Recipe recipe) {
        this.recipe = recipe;
        recipeName.setText(recipe.getName());
        ArrayList<String> usernames = controller.usersList();
        choiceBox.getItems().addAll(usernames);
        choiceBox.getSelectionModel().selectFirst();
    }
}