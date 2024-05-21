/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;

/**
 *
 * @author oloft
 */
public class CheckoutElement extends AnchorPane {


    @FXML ImageView imageView;
    @FXML Label nameLabel;
    @FXML Label prizeLabel;

    @FXML Label quantityLabel;

    @FXML Label productSumLabel;

    private MainViewController mainController;

    private Model model = Model.getInstance();

    private Product product;

    private final static double kImageWidth = 100.0;
    private final static double kImageRatio = 0.75;

    public CheckoutElement(Product product, double amount) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CheckoutElement.fxml"));
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
        quantityLabel.setText(String.valueOf((int)amount));
        productSumLabel.setText(String.format("%.2f", product.getPrice() * amount) + " Kr");

        //if (!product.isEcological()) {
        //   ecoLabel.setText("");
    }
    // Setting additional product info
    // ProductDetail detail = model.getDetail(product);
    // if (detail != null) {
    // descriptionLabel.setText(detail.getDescription());
    // originLabel.setText(detail.getOrigin());
    // brandLabel.setText(detail.getBrand());
    // contentsLabel.setText(detail.getContents());
    // }
    // }

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
