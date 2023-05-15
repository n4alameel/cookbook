package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;

public class SendMessageView {
    private Parent root;

    public SendMessageView(){
        try {
            root = FXMLLoader.load(getClass().getResource("/sendMessageWindow.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Parent getRoot() {
        return this.root;
    }
    
}