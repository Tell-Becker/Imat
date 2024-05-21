/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

/**
 *
 * @author oloft
 */
public class ShoppingCartElement extends AnchorPane {


    @FXML ImageView imageView;
    @FXML Label nameLabel;
    @FXML Label prizeLabel;
    @FXML
    private Label quantityLabel;

    private MainViewController mainController;

    private Model model = Model.getInstance();

    //CheckoutPanel checkoutPanel;

    private Product product;
    private int count;
    //private List<ShoppingItem> shoppingCart;

    private List<Label> amountList;

    ShoppingCart shoppingCart = Model.getInstance().getShoppingCart();

    private final static double kImageWidth = 100.0;
    private final static double kImageRatio = 0.75;

    public ShoppingCartElement(Product product, double amount, MainViewController mainController) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShoppingCartElement.fxml"));
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
        imageView.setImage(model.getImage(product, kImageWidth, kImageWidth * kImageRatio));
        quantityLabel.setText(String.valueOf((int)amount));

    }

    public Product getProduct() {
        return product;
    }

    public void setAmount(double amount) {
        quantityLabel.setText(String.valueOf(amount));
    }

    @FXML
    private void handleAddAction(ActionEvent event) {
        System.out.println("Add " + product.getName());
        model.addToShoppingCart(product);
    }

    @FXML
    private void handleShowProductDetail(ActionEvent event) {

        mainController.openProductDetail(product);
    }
}
