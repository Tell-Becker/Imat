package imat;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductDetail;

/**
 *
 * @author oloft
 */
public class ProductPanel extends AnchorPane {


    @FXML
    ImageView imageView;
    @FXML
    Label nameLabel;
    @FXML
    Label prizeLabel;

    /*@FXML
    Label ecoLabel;

    @FXML
    Label descriptionLabel;

    @FXML
    Label originLabel;

    @FXML
    Label brandLabel;

    @FXML
    Label contentsLabel;*/

    private int quantity = 0;

    private MainViewController mainController;

    private Model model = Model.getInstance();

    private Product product;
    
    private final static double kImageWidth = 100.0;
    private final static double kImageRatio = 0.75;

    public ProductPanel(Product product, MainViewController mainController) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProductPanel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.mainController = mainController;
        // Setting basic product info
        this.product = product;
        nameLabel.setText(product.getName());
        prizeLabel.setText(String.format("%.2f", product.getPrice()) + " " + product.getUnit());
        imageView.setImage(model.getImage(product, kImageWidth, kImageWidth*kImageRatio));

   }
    
    @FXML
    private void handleAddAction(ActionEvent event) {
        System.out.println("Add " + product.getName());
        model.addToShoppingCart(product);
    }

    @FXML protected void handleShowProductDetail(Event event) {
        mainController.openProductDetail(product);
    }
}