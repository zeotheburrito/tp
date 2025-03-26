package hirehive.address.ui;

import java.util.logging.Logger;

import hirehive.address.commons.core.LogsCenter;
import hirehive.address.logic.Logic;
import hirehive.address.model.person.Note;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class NoteWindow extends UiPart<Stage> {

    public static final String EMPTY_NOTE = "No note currently.";

    private static final Logger logger = LogsCenter.getLogger(NoteWindow.class);
    private static final String FXML = "NoteWindow.fxml";

    @FXML
    private Label note;

    public NoteWindow(Stage root) {
        super(FXML, root);
        note.setText(EMPTY_NOTE);
    }

    public NoteWindow() {
        this(new Stage());
    }

    // How to update this window continuously
    // singletonObservableList? use model to update
    public void setNote(Logic logic) {
        Note newNote = logic.getPersonNote();
        note.setText(newNote.value);
    }

    public void show() {
        logger.fine("Showing note page.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        note.setText(EMPTY_NOTE);
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

}
