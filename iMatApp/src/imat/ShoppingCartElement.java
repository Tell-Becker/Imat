/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.Event;
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
    private double amount;
    private CheckoutPanel checkoutPanel;

    public ShoppingCartElement(Product product, double amount, MainViewController mainController, CheckoutPanel checkoutPanel) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShoppingCartElement.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.mainController = mainController;
        this.amount = amount;
        this.checkoutPanel = checkoutPanel;


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
        int roundedAmount = (int) Math.round(amount);
        quantityLabel.setText(String.valueOf(roundedAmount));
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

    @FXML
    private void handlePlusAction(Event event) {
        amount++; // Öka antalet
        model.addToShoppingCart(product);
        //mainController.updateShoppingCartElement(model.getShoppingCart().getItems());
        checkoutPanel.updateCheckoutElement(model.getShoppingCart().getItems());
        mainController.updateProductQuantities();
        updateQuantityLabel(); // Uppdatera etiketten för antal
        mainController.updateLabel();
        mainController.updatePriceLabel();
        //mainController.updateShoppingCartElement(model.getShoppingCart().getItems());


    }

    @FXML
    private void handleMinusAction(Event event) {
        if (amount > 1) {
            amount--; // Minska antalet om det är större än noll
            model.removeFromShoppingCart(product);
            for (int i = 0; i < amount; i++) {
                model.addToShoppingCart(product);
            }

            updateQuantityLabel(); // Uppdatera etiketten för antal
            //mainController.updateShoppingCartElement(model.getShoppingCart().getItems());

        }else if (amount == 1) {
            amount--;
            model.removeFromShoppingCart(product);
            //updateQuantityLabel();
            updateQuantityLabel();
            mainController.updateShoppingCartElement(model.getShoppingCart().getItems());
            //mainController.shoppingcartback();
            //mainController.fixAmount(product);

        }
        checkoutPanel.updateCheckoutElement(model.getShoppingCart().getItems());
        mainController.updateLabel();
        mainController.updatePriceLabel();
        mainController.updateProductQuantities();
        //mainController.updateShoppingCartElement(model.getShoppingCart().getItems());
        //mainController.updateProductQuantities();

        //mainController.checkoutElementUpdateShoppingCartElement(model.getShoppingCart().getItems());

    }

    private void updateQuantityLabel() {
        quantityLabel.setText(Integer.toString((int) amount)); // Uppdatera antalet på etiketten
    }

}
