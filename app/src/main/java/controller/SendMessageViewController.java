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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;

import model.Message;
import model.Recipe;

public class SendMessageViewController {
    private Parent root;
    Controller controller = Controller.getInstance();
    Image image = (new Image("img/Default.png"));

    @FXML
    private Label recipeName;

    @FXML
    private Button sendButton;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextArea messageTextArea;

    @FXML
    private ImageView recipePic;

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
        controller.sendMessage(recipe.getId(), messageText, senderId, selectedUserId);
    }

    public void initialize(Recipe recipe) {
        this.recipe = recipe;
        recipeName.setText(recipe.getName());
        ArrayList<String> usernames = controller.usersList();
        for (String username : usernames){
            if ((controller.getActiveUser().getUsername()).equals(username)){
                usernames.remove(username);
                System.out.println(username);
                break;
            }
        }
        System.out.println(usernames);
        choiceBox.getItems().addAll(usernames);
        choiceBox.getSelectionModel().selectFirst();

        double w = 0;
        double h = 0;
        double ratioX = recipePic.getFitWidth() / image.getWidth();
        double ratioY = recipePic.getFitHeight() / image.getHeight();
        double reducCoeff = 0;

        if(ratioX >= ratioY) {
            reducCoeff = ratioY;
        } else {
            reducCoeff = ratioX;
        }

        w = image.getWidth() * reducCoeff;
        h = image.getHeight() * reducCoeff;

        recipePic.setX((recipePic.getFitWidth() - w) / 2);
        recipePic.setY((recipePic.getFitHeight() - h) / 2);

        try {
            if(recipe.getBlob() != null)
                image = new Image(recipe.getBlob());
          recipePic.setImage(image);
        } catch (Exception e) {
          System.out.println(e);
        }
    }
}