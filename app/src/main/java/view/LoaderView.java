package view;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoaderView extends Preloader {
    private ProgressBar bar;
    public Stage stage;

    private Scene createPreloaderScene() {
        bar = new ProgressBar();
        BorderPane p = new BorderPane();

        bar.setPrefWidth(400);

        p.setStyle("-fx-background-color: grey;");
        bar.setStyle("-fx-accent: black;");

        Image lightsaberImage = new Image("img/lightsaber.jpg");
        ImageView lightsaberImageView = new ImageView(lightsaberImage);

        lightsaberImageView.setPreserveRatio(true);
        lightsaberImageView.fitWidthProperty().bind(p.widthProperty());

        p.setCenter(lightsaberImageView);
        p.setTop(bar);

        bar.toFront();
        lightsaberImageView.toBack();

        return new Scene(p, 400, 400);
    }

    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.getIcons().add(new Image(LoaderView.class.getResourceAsStream("/img/pictogram.png")));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Loader");
        stage.setScene(createPreloaderScene());
        stage.show();
    }

    @Override
    public void handleProgressNotification(ProgressNotification pn) {
        bar.setProgress(pn.getProgress());
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification evt) {
        if (evt.getType() == StateChangeNotification.Type.BEFORE_START) {
            stage.hide();
        }
    }
}
