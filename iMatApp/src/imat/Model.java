/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat;

/**
 *
 * @author oloft
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.image.Image;
import se.chalmers.cse.dat216.project.*;

public class Model {

    private static Model instance = null;
    private IMatDataHandler iMatDataHandler;

    private final ArrayList<String> availableCardTypes = new ArrayList<String>(Arrays.asList("MasterCard", "Visa"));
    private final ArrayList<String> months = new ArrayList<String>(Arrays.asList("1", "2","3", "4", "5", "6","7", "8","9", "10", "11", "12"));
    private final ArrayList<String> years = new ArrayList<String>(Arrays.asList("24", "25", "26", "27", "28", "29", "30"));

    protected Model() {
        // Exists only to defeat instantiation.
    }

    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
            instance.init();
        }
        return instance;
    }

    private void init() {
        iMatDataHandler = IMatDataHandler.getInstance();

    }

    public List<Product> getProducts() {
        return iMatDataHandler.getProducts();
    }

    public Product getProduct(int idNbr) {
        return iMatDataHandler.getProduct(idNbr);
    }

    public List<Product> findProducts(java.lang.String s) {
        return iMatDataHandler.findProducts(s);
    }

    public Image getImage(Product p) {
        return iMatDataHandler.getFXImage(p);
    }

    public Image getImage(Product p, double width, double height) {
        return iMatDataHandler.getFXImage(p, width, height);
    }

    public ProductDetail getDetail(Product p) {
        return iMatDataHandler.getDetail(p);
    }
    public void addToShoppingCart(Product p) {
        ShoppingCart shoppingCart = iMatDataHandler.getShoppingCart();

        ShoppingItem item = new ShoppingItem(p);
        Model.getInstance().getShoppingCart().addItem(item);

        //shoppingCart.addProduct(p);
    }

    public void removeFromShoppingCart(Product p) {
        ShoppingCart shoppingCart = iMatDataHandler.getShoppingCart();

        ShoppingItem item = new ShoppingItem(p);
        Model.getInstance().getShoppingCart().removeItem(item);

    }

    public List<String> getCardTypes() {
        return availableCardTypes;
    }

    public List<String> getMonths() {
        return months;
    }

    public List<String> getYears() {
        return years;
    }

    public CreditCard getCreditCard() {
        return iMatDataHandler.getCreditCard();
    }

    public Customer getCustomer() {
        return iMatDataHandler.getCustomer();
    }

    public ShoppingCart getShoppingCart() {
        return iMatDataHandler.getShoppingCart();
    }

    public void clearShoppingCart() {

        iMatDataHandler.getShoppingCart().clear();

    }

    public void placeOrder() {

        iMatDataHandler.placeOrder();

    }


    public int getNumberOfOrders() {

        return iMatDataHandler.getOrders().size();

    }

    public List<Order> getOrders() {
        return iMatDataHandler.getOrders();
    }

    public double getCartAmount() {

        double itemCount = 0;
        ShoppingCart cart = iMatDataHandler.getShoppingCart();

        for (ShoppingItem item:cart.getItems()) {
            itemCount = itemCount + item.getAmount();
        }
        return itemCount;
    }

    public double getProductAmount() {

        double itemCount = 0;
        ShoppingCart cart = iMatDataHandler.getShoppingCart();

        for (ShoppingItem item:cart.getItems()) {
            itemCount = itemCount + item.getAmount();
        }
        return itemCount;
    }
    public void shutDown() {
        iMatDataHandler.shutDown();
    }

    public void updateQuantity(Product product, int quantity) {
        ShoppingCart shoppingCart = iMatDataHandler.getShoppingCart();
        ShoppingItem item = shoppingCart.getItems().stream()
                .filter(i -> i.getProduct().equals(product))
                .findFirst()
                .orElse(null);
        if (item != null) {
            item.setAmount(quantity);
        }
    }
}
