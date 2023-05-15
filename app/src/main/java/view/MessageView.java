package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;

public class MessageView {
    private Parent root;

    public MessageView(){
        try {
            root = FXMLLoader.load(getClass().getResource("/messageWindow.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Parent getRoot() {
        return this.root;
    }
    
}