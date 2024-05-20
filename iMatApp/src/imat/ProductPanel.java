package imat;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductDetail;

/**
 *
 * @author oloft
 */
public class ProductPanel extends StackPane {


    @FXML
    ImageView imageView;
    @FXML
    Label nameLabel;
    @FXML
    Label prizeLabel;

    @FXML ImageView imageView2;
    @FXML Label nameLabel2;
    @FXML Label prizeLabel2;

    @FXML
    Label ecoLabel;

    @FXML
    Label descriptionLabel;

    @FXML
    Label originLabel;

    @FXML
    Label brandLabel;

    @FXML
    Label contentsLabel;

    @FXML AnchorPane quantityPane;
    @FXML AnchorPane addButtonPane;

    @FXML Label quantityLabel;

    @FXML ImageView favoriteImageView;

    @FXML ImageView favoriteImageView2;

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

        this.mainController = mainController;
        this.checkoutPanel = checkoutPanel;


        if (iMatDataHandler.isFavorite(product)){
            favoriteImageView2.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                    "imat/resources/starfilld.png")));
            favoriteImageView.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                    "imat/resources/starfilld.png")));}
        else{

            favoriteImageView.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                    "imat/resources/star.png")));
            favoriteImageView2.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                    "imat/resources/star.png")));}


        // Setting basic product info
        this.product = product;
        nameLabel.setText(product.getName());
        prizeLabel.setText(String.format("%.2f", product.getPrice()) + " " + product.getUnit());
        imageView.setImage(model.getImage(product, kImageWidth, kImageWidth*kImageRatio));

   }

   public Product getProduct() {
        return product;
   }
    
    @FXML
    private void handleAddAction(ActionEvent event) {
        quantity = 1;


        quantityPane.toFront();

        imageView2.setImage(model.getImage(product, kImageWidth, kImageWidth * kImageRatio));
        nameLabel2.setText(product.getName());
        prizeLabel2.setText(String.format("%.2f", product.getPrice()) + " " + product.getUnit());
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
                    "imatmini/resources/star.png")));
            favoriteImageView2.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                    "imatmini/resources/star.png")));
            // mainController.searchfavoriteCategory();

        } else {
            iMatDataHandler.addFavorite(product);

            System.out.println(product);
            System.out.println(iMatDataHandler.favorites());
            favoriteImageView.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                    "imat/resources/starfilld.png")));
            favoriteImageView2.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
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
    private void updateQuantityLabel() {
        quantityLabel.setText(Integer.toString(quantity)); // Uppdatera antalet på etiketten
    }
}