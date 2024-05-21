package imat;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;

/**
 *
 * @author oloft
 */
public class ProductPanel extends StackPane {

    @FXML ImageView imageView;
    @FXML Label nameLabel;
    @FXML Label prizeLabel;

    @FXML AnchorPane quantityPane;
    @FXML AnchorPane addButtonPane;
    @FXML Label quantityLabel;
    @FXML ImageView favoriteImageView;
    private int quantity = 0;
    IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();
    private MainViewController mainController;
    private CheckoutPanel checkoutPanel;
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

        this.product = product;
        this.mainController = mainController;
        mainController.registerProductPanel(this);

        initializeProductInfo();

   }

   private void initializeProductInfo() {
       nameLabel.setText(product.getName());
       prizeLabel.setText(String.format("%.2f", product.getPrice()) + " " + product.getUnit());
       imageView.setImage(model.getImage(product, kImageWidth, kImageWidth*kImageRatio));
       updateFavoriteImage();
   }

   private void updateFavoriteImage() {
       if (iMatDataHandler.isFavorite(product)){
           favoriteImageView.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                   "imat/resources/starfilld.png")));}
       else {
           favoriteImageView.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                   "imat/resources/star.png")));
       }
   }

    @FXML
    private void handleAddAction(ActionEvent event) {
        quantity = 1;

        quantityPane.toFront();
        updateQuantityLabel();

        // System.out.println("Add " + product.getName());
        model.addToShoppingCart(product);
        mainController.updateShoppingCartElement(model.getShoppingCart().getItems());
    }

    @FXML private void handleShowProductDetail(Event event) {
        mainController.openProductDetail(product);
    }

    @FXML
    private void handlePlusAction(Event event) {
        quantity++; // Öka antalet
        updateQuantityLabel(); // Uppdatera etiketten för antal
        model.addToShoppingCart(product);
        mainController.updateShoppingCartElement(model.getShoppingCart().getItems());

    }

    @FXML
    private void handleShowFavorite(Event event) {
        if (iMatDataHandler.isFavorite(product)) {
            iMatDataHandler.removeFavorite(product);
            System.out.println(iMatDataHandler.favorites());
            favoriteImageView.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                    "imat/resources/star.png")));

            // mainController.searchfavoriteCategory();

        } else {
            iMatDataHandler.addFavorite(product);

            System.out.println(product);
            System.out.println(iMatDataHandler.favorites());
            favoriteImageView.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                    "imat/resources/starfilld.png")));
            //mainController.searchfavoriteCategory();
        }
    }


    @FXML
    private void handleMinusAction(Event event) {
        if (quantity > 1) {
            quantity--; // Minska antalet om det är större än noll
            model.removeFromShoppingCart(product);

            for (int i = 0; i < quantity; i++) {
                model.addToShoppingCart(product);
            }

            updateQuantityLabel(); // Uppdatera etiketten för antal

        }else {
            model.removeFromShoppingCart(product);

            addButtonPane.toFront();


        }
        mainController.updateShoppingCartElement(model.getShoppingCart().getItems());

    }

    public void resetQuantity() {
        quantity = 0;
        updateQuantityLabel();
        addButtonPane.toFront();
    }
    private void updateQuantityLabel() {
        quantityLabel.setText(Integer.toString(quantity)); // Uppdatera antalet på etiketten
    }
}