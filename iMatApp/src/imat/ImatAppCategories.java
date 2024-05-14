package imat;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ImatAppCategories extends AnchorPane{
    private AnchorPane root;
    private MainViewController mainviewcontroller;

    public ImatAppCategories(MainViewController mainviewcontroller) {
        this.mainviewcontroller = mainviewcontroller;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("imat_app_categories.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            System.err.println("Error loading FXML file: " + exception.getMessage());
            throw new RuntimeException(exception);
        }
        this.mainviewcontroller = mainviewcontroller;


    }
}
