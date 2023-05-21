package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;

import controller.SendMessageViewController;
import model.Recipe;

public class SendMessageView {
    private Parent root;

    public SendMessageView(Recipe recipe){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sendMessageWindow.fxml"));
            root = (Parent) loader.load();
            
            SendMessageViewController sendMessageViewController = (SendMessageViewController) loader.getController();
            sendMessageViewController.initialize(recipe);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Parent getRoot() {
        return this.root;
    }
    
}