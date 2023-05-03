package model;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Search {

    private GridPane root;
    private Controller controller;

    public Search(Controller controller) {
        this.root = new GridPane();
        this.controller = controller;

        root.setPadding(new Insets(11.5, 12.5, 13.5, 14.4));
        root.setHgap(5.5);
        root.setVgap(5.5);

        root.add(new Label("Search:"), 0, 0);
        final TextField query = new TextField();
        root.add(query, 1, 0);

        Button searchBtn = new Button("Search");
        root.add(searchBtn, 2, 0);

        AtomicInteger i = new AtomicInteger();
        VBox labelBox = new VBox();

        searchBtn.setOnAction(e -> {
            List<String> data = controller.selectDataFromDatabase();
            String search = query.getText();

            List<Label> printLabels = new ArrayList<>();

            // Add the new print labels
            for (String value : data) {
                if (value.contentEquals(search) || value.equalsIgnoreCase(search)){
                    Label printLabel = new Label(value);
                    printLabels.add(printLabel);
                }
            }

            // Remove the previous search results from labelBox
            labelBox.getChildren().removeIf(node -> node instanceof Label);


            // Add the new print labels to labelBox
            labelBox.getChildren().addAll(printLabels);

            // Add the new print labels to root
            if (root.getChildren().contains(labelBox)) {
                root.getChildren().remove(labelBox);
            }

            root.add(labelBox, 0, 1, 2, 3);
        });

    }


    public Parent getRoot() {
        return this.root;
    }

}
