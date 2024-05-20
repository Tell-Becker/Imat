package imat;


import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class iMatApp extends Application {
    @Override
    public void start(Stage stage) throws Exception  {
        
        ResourceBundle bundle = java.util.ResourceBundle.getBundle("imat/resources/iMat");
        
        Parent root = FXMLLoader.load(getClass().getResource("imat_app.fxml"), bundle);

        VBox rootContainer = new VBox();
        rootContainer.getChildren().addAll(root);

        Scene scene = new Scene(rootContainer, 1000, 700);
        
        stage.setTitle(bundle.getString("application.name"));
        stage.setScene(scene);
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                Model.getInstance().shutDown();
            }
        }));
    }
}
