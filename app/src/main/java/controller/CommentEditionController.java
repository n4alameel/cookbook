package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import model.Comment;

import java.io.IOException;

public class CommentEditionController {
    private Controller controller = Controller.getInstance();
    private Comment comment;
    @FXML
    private javafx.scene.control.TextArea commentEditionTextArea;

    @FXML
    private VBox box;
    public void saveChangings(ActionEvent actionEvent) {
        this.comment.setText(commentEditionTextArea.getText());
        this.controller.updateComment(this.comment);
    }

    public void cancelChangings(ActionEvent actionEvent) throws IOException {
        box.setStyle("visibility: false");
        this.controller.displayRecipeView(this.comment.getRecipeId());
    }

    public void showFieldToChange(Comment currectComment) {
        box.setStyle("visibility: true");
        this.comment = currectComment;
        commentEditionTextArea.setText(this.comment.getText());
    }
}
