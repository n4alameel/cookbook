package view;

import controller.HomeViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
    public class HelpView {
        private Parent root;

        public HelpView() {
            try {
                root = FXMLLoader.load(getClass().getResource("/HelpWindow.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public Parent getRoot() {
            return this.root;
        }
    }


