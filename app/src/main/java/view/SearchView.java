package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;

import java.io.IOException;

public class SearchView {

    public ScrollPane getRoot() {
        return root;
    }

    public ScrollPane root;

    public SearchView() {
        try {
            this.root = FXMLLoader.load(getClass().getResource("/SearchWindow.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

