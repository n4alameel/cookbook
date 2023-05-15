package controller;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import java.io.IOException;

import model.Message;

public class SendMessageViewController {
    private Parent root;
    Controller controller = Controller.getInstance();

    @FXML
    private TextArea messageTextArea;

    @FXML
    private Button sendButton;

    @FXML
    private ChoiceBox<String> choiceBox;

    private int recipeId;
    private int senderId;
    private int receiverId;

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
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
        System.out.println("Sending message: Recipe ID=" + recipeId + ", Sender ID=" + senderId + ", Receiver ID=" + selectedUserId + ", Message Text=" + messageText);
        //controller.sendMessage(recipeId, messageText, senderId, selectedUserId);
    }

    public void initialize() {
        ArrayList<String> usernames = controller.usersList();
        choiceBox.getItems().addAll(usernames);
        choiceBox.getSelectionModel().selectFirst();
    }
}