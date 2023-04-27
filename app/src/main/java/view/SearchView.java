package view;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SearchView {

    private GridPane root;
    private Controller controller;

    public SearchView(Controller controller, ActiveView searchView) {
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

            for (String value : data) {
                Label label = new Label(value);
                labelBox.getChildren().add(label);

                if (value.contentEquals(search) || value.equalsIgnoreCase(search)){
                    Label printLabel = new Label(value);
                    i.getAndIncrement();
                    printLabels.add(printLabel);
                }
            }

            root.getChildren().removeIf(node -> node instanceof Label);

            // Add the new print labels
            for (Label label : printLabels) {
                root.add(label, 0, 1, 2, 3);
            }
        });
    }


    public Parent getRoot() {
        return this.root;
    }

}
