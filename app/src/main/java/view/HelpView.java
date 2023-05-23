package view;

import controller.HelpController;
import controller.HomeViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
    public class HelpView {
        private Parent root;

        public HelpView() {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/HelpWindow.fxml"));
                root = (Parent) loader.load();
                HelpController helpController = loader.getController();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public Parent getRoot() {
            return this.root;
        }
    }


