package controller;

import javafx.application.Preloader;
import javafx.scene.Scene;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Loader extends Preloader {
    private ProgressBar bar;
    protected Stage stage;
    private long startTime;

    private Scene createPreloaderScene() {
        bar = new ProgressBar();
        BorderPane p = new BorderPane();
        p.setCenter(bar);
        return new Scene(p, 300, 150);
    }

    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("Loader");
        stage.setScene(createPreloaderScene());
        stage.show();
        stage.toFront();
        startTime = System.currentTimeMillis();
    }

    @Override
    public void handleProgressNotification(ProgressNotification pn) {
        // Slow down the progress bar by pausing for 50 milliseconds on each update
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bar.setProgress(pn.getProgress());
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification evt) {
        if (evt.getType() == StateChangeNotification.Type.BEFORE_START) {
            // Pause for an additional 500 milliseconds to ensure a minimum duration of 1 second
            long elapsedTime = System.currentTimeMillis() - startTime;
            long remainingTime = Math.max(0, 1000 - elapsedTime);
            try {
                Thread.sleep(remainingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Hide the preloader
            stage.hide();
        }
    }
}
