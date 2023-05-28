package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;

import model.Message;
import java.util.ArrayList;
import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class MessageViewController {
    private Parent root;
    Controller controller = Controller.getInstance();
    ArrayList<Message> messages = controller.updateMessages();

    @FXML
    private TableView<Message> messageTable;

    @FXML
    private TableColumn<Message, String> textColumn;

    @FXML
    private TableColumn<Message, String> senderColumn;

    @FXML
    private TableColumn<Message, String> recipeColumn;

    public void initialize() {
        ArrayList<Message> reverseMessages = new ArrayList<>(messages);
        for (Message i : reverseMessages){
            i.setUsername(controller.getUserById(i.getSender()).getUsername());
            i.setRecipeName(controller.getRecipeById(i.getRecipe()).getName());
        }
        Collections.reverse(reverseMessages);
        textColumn.setCellValueFactory(new PropertyValueFactory<>("text"));
        senderColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        recipeColumn.setCellValueFactory(new PropertyValueFactory<>("recipeName"));

        ObservableList<Message> observableMessages = FXCollections.observableArrayList(reverseMessages);
        messageTable.setItems(observableMessages);
        senderColumn.prefWidthProperty().bind(messageTable.widthProperty().divide(6));
        recipeColumn.prefWidthProperty().bind(messageTable.widthProperty().divide(6));
        textColumn.prefWidthProperty().bind(messageTable.widthProperty().subtract(senderColumn.widthProperty()).subtract(recipeColumn.widthProperty()).subtract(4));
    }
}