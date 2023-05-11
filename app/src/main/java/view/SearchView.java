package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class SearchView {

    private Parent root;

    public SearchView() {
        try {
            root = FXMLLoader.load(getClass().getResource("/SearchWindow.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Parent getRoot() {
        return this.root;
    }
}

