
package imat;

import java.util.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import se.chalmers.cse.dat216.project.*;


public class MainViewController implements Initializable, ShoppingCartListener {

    @FXML private AnchorPane header;
    @FXML private ImageView accountLogo;
    @FXML private ImageView shoppingCartLogo;
    @FXML private AnchorPane program;
    @FXML private StackPane shopPane;
    @FXML private TextField searchField;
    @FXML private Label itemsLabel;
    @FXML private Label costLabel;
    @FXML private FlowPane mainViewFlowPane;
    @FXML private FlowPane productsPanel;
    private Map<String, ProductPanel> productMap = new HashMap();

    //Categories:
    @FXML private AnchorPane categoryList;
    @FXML private AnchorPane everythingCategory;
    @FXML private AnchorPane meatCategory;
    @FXML private AnchorPane fishCategory;
    @FXML private AnchorPane dairyCategory;
    @FXML private AnchorPane carbCategory;
    @FXML private AnchorPane bakeryCategory;
    @FXML private AnchorPane vegetableCategory;
    @FXML private AnchorPane fruitCategory;
    @FXML private AnchorPane pantryCategory;
    @FXML private AnchorPane snackCategory;
    @FXML private AnchorPane drinkCategory;

    //Detailview
    @FXML private AnchorPane productDetailView;
    @FXML private Label originLabel;
    @FXML private Label descriptionLabel;
    @FXML private Label nameLabel;
    @FXML private ImageView detailImageView;
    @FXML private ImageView closeDetailImageView;
    @FXML private Label brandLabel;
    @FXML private Label contentsLabel;
    @FXML private Label prizeLabel;
    @FXML private Label ecoLabel;

    //AccountPane:
    @FXML private AnchorPane accountPane;
    @FXML ComboBox cardTypeCombo;
    @FXML private TextField numberTextField;
    @FXML private TextField nameTextField;
    @FXML private ComboBox monthCombo;
    @FXML private ComboBox yearCombo;
    @FXML private TextField cvcField;
    @FXML private Label purchasesLabel;

    @FXML public FlowPane shoppingCartFlowPane;

    @FXML private AnchorPane dynamicPane;
    @FXML private AnchorPane dynamicPane2;

    //Shoppingcart
    @FXML private AnchorPane shoppingCart;

    @FXML private ImageView closeShoppingCartIcon;
    @FXML private ImageView backArrow;

    // Other variables:
    IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();
    private final Model model = Model.getInstance();

    CheckoutPanel checkoutPanel;

    // Shop pane actions:

    /*private void productDetail(Product product) {
        ProductDetail detail = model.getDetail(product);
    }*/

    private void selectCategory(AnchorPane categoryPane) {
        for (Node node : categoryList.getChildren()) {
            if (node instanceof AnchorPane) {
                node.getStyleClass().remove("selected_category");
                node.getStyleClass().add("categories");
            }
        }
        categoryPane.getStyleClass().remove("categories");
        categoryPane.getStyleClass().add("selected_category");
    }

    @FXML private void handleSearchAction(ActionEvent event) {
        for (Node node : categoryList.getChildren()) {
            if (node instanceof AnchorPane) {
                node.getStyleClass().remove("selected_category");
                node.getStyleClass().add("categories");
            }
        }
        List<Product> matches = model.findProducts(searchField.getText());
        updateProductList(matches);
        System.out.println("# matching products: " + matches.size());
    }

    @FXML private void handleClearCartAction(ActionEvent event) {
        model.clearShoppingCart();
    }

    @FXML private void handleBuyItemsAction(ActionEvent event) {
        model.placeOrder();
        costLabel.setText("Köpet klart!");
    }

    private void handleNameAction() {
        openNameView();
    }

    @FXML
    private void handleCheckoutAction(ActionEvent event) {
        openCheckoutView();
    }

    //Acount pane actions

    void openProductDetail() {
        productDetailView.toFront();
    }

    @FXML
    private void handleDoneAction(ActionEvent event) {
        closeAccountView();
    }

    @FXML private void handleDoneAccountAction(ActionEvent event) {
        closeAccountView();
    }


    @FXML private void searchEverythingCategory() {
        searchField.clear();
        selectCategory(everythingCategory);
        updateProductList(model.getProducts());
    }

    @FXML private void searchMeatCategory() {
        searchField.clear();
        selectCategory(meatCategory);
        ArrayList<Product> matches = new ArrayList<Product>();
        List<Product> products = model.getProducts();
        for (Product product : products) {
            ProductCategory category = product.getCategory();
            if(category == ProductCategory.MEAT) {
                matches.add(product);
            }

        }
        updateProductList(matches);
    }

    @FXML private void searchFishCategory() {
        searchField.clear();
        selectCategory(fishCategory);
        ArrayList<Product> matches = new ArrayList<Product>();
        List<Product> products = model.getProducts();
        for (Product product : products) {
            ProductCategory category = product.getCategory();
            if(category == ProductCategory.FISH) {
                matches.add(product);
            }
        }
        updateProductList(matches);
    }

    @FXML private void searchDairyCategory() {
        searchField.clear();
        selectCategory(dairyCategory);
        ArrayList<Product> matches = new ArrayList<Product>();
        List<Product> products = model.getProducts();
        for (Product product : products) {
            ProductCategory category = product.getCategory();
            if(category == ProductCategory.DAIRIES) {
                matches.add(product);
            }

        }
        updateProductList(matches);
    }

    @FXML private void searchCarbCategory() {
        searchField.clear();
        selectCategory(carbCategory);
        ArrayList<Product> matches = new ArrayList<Product>();
        List<Product> products = model.getProducts();
        for (Product product : products) {
            ProductCategory category = product.getCategory();
            if(category == ProductCategory.PASTA) {
                matches.add(product);
            }
            if(category == ProductCategory.POTATO_RICE) {
                matches.add(product);
            }
            if(category == ProductCategory.BREAD) {
                matches.add(product);
            }
        }
        updateProductList(matches);
    }

    @FXML private void searchBakeryCategory() {
        searchField.clear();
        selectCategory(bakeryCategory);
        ArrayList<Product> matches = new ArrayList<Product>();
        List<Product> products = model.getProducts();
        for (Product product : products) {
            ProductCategory category = product.getCategory();
            if(Objects.equals(product.getName(), "Kanelbullar")) {
                matches.add(product);
            }
            if(category == ProductCategory.BREAD) {
                matches.add(product);
            }
        }
        updateProductList(matches);
    }

    @FXML private void searchFruitCategory() {
        searchField.clear();
        selectCategory(fruitCategory);
        ArrayList<Product> matches = new ArrayList<Product>();
        List<Product> products = model.getProducts();
        for (Product product : products) {
            ProductCategory category = product.getCategory();
            if(category == ProductCategory.FRUIT) {
                matches.add(product);
            }
            if(category == ProductCategory.BERRY) {
                matches.add(product);
            }
            if(category == ProductCategory.CITRUS_FRUIT) {
                matches.add(product);
            }
            if(category == ProductCategory.MELONS) {
                matches.add(product);
            }
            if(category == ProductCategory.EXOTIC_FRUIT) {
                matches.add(product);
            }
            if(category == ProductCategory.VEGETABLE_FRUIT) {
                matches.add(product);
            }
        }
        updateProductList(matches);
    }

    @FXML private void searchVegetableCategory() {
        searchField.clear();
        selectCategory(vegetableCategory);
        ArrayList<Product> matches = new ArrayList<Product>();
        List<Product> products = model.getProducts();
        for (Product product : products) {
            ProductCategory category = product.getCategory();
            if(category == ProductCategory.CABBAGE) {
                matches.add(product);
            }
            if(category == ProductCategory.HERB) {
                matches.add(product);
            }
            if(category == ProductCategory.ROOT_VEGETABLE) {
                matches.add(product);
            }
            if(category == ProductCategory.POD) {
                matches.add(product);
            }
            if(category == ProductCategory.VEGETABLE_FRUIT) {
                matches.add(product);
            }
        }
        updateProductList(matches);
    }

    @FXML private void searchPantryCategory() {
        searchField.clear();
        selectCategory(pantryCategory);
        ArrayList<Product> matches = new ArrayList<Product>();
        List<Product> products = model.getProducts();
        for (Product product : products) {
            ProductCategory category = product.getCategory();
            if(category == ProductCategory.FLOUR_SUGAR_SALT) {
                matches.add(product);
            }
        }
        updateProductList(matches);
    }

    @FXML private void searchSweetCategory() {
        searchField.clear();
        selectCategory(snackCategory);
        ArrayList<Product> matches = new ArrayList<Product>();
        List<Product> products = model.getProducts();
        for (Product product : products) {
            ProductCategory category = product.getCategory();
            if(category == ProductCategory.SWEET) {
                matches.add(product);
            }
            if(category == ProductCategory.NUTS_AND_SEEDS) {
                matches.add(product);
            }
        }
        updateProductList(matches);
    }

    @FXML private void searchDrinkCategory() {
        searchField.clear();
        selectCategory(drinkCategory);
        ArrayList<Product> matches = new ArrayList<Product>();
        List<Product> products = model.getProducts();
        for (Product product : products) {
            ProductCategory category = product.getCategory();
            if(category == ProductCategory.HOT_DRINKS) {
                matches.add(product);
            }
            if(category == ProductCategory.COLD_DRINKS) {
                matches.add(product);
            }
        }
        updateProductList(matches);
    }

    @FXML public void home() {
        shopPane.toFront();
        searchEverythingCategory();
        everythingCategory.getStyleClass().remove("selected_category");
    }

    @FXML void openProductDetail(Product product) {
        populateProductDetail(product);
        productDetailView.toFront();
    }

    private void populateProductDetail(Product product) {
        nameLabel.setText(product.getName());
        detailImageView.setImage(model.getImage(product));
        ProductDetail detail = model.getDetail(product);
        if (detail != null) {
            descriptionLabel.setText(detail.getDescription());
            originLabel.setText(detail.getOrigin());
            prizeLabel.setText(String.format("%.2f", product.getPrice()) + " " + product.getUnit());
            if (product.isEcological()) {
                ecoLabel.setText("Ekologisk");
            } else {
                ecoLabel.setText("Ej ekologisk");
            }
            brandLabel.setText(detail.getBrand());
            contentsLabel.setText(detail.getContents());
        } else {
            descriptionLabel.setText("Beskrivning saknas");
            originLabel.setText("Ursprungsplats saknas");
            prizeLabel.setText("Pris saknas");
            ecoLabel.setText("Ekologi saknas");
            brandLabel.setText("Märke saknas");
            contentsLabel.setText("Innehåll saknas");


        }


    }

    @FXML public void closeButtonMouseEntered(){
        closeDetailImageView.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/icon_close_hover.png")));
    }

    @FXML public void closeButtonMouseExit(){
        closeDetailImageView.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/icon_close.png")));
    }

    @FXML public void accountIconEntered(){
        accountLogo.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/user_hover.png")));
    }

    @FXML public void accountIconPressed() throws InterruptedException {
        accountLogo.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/user_pressed.png")));
        handleNameAction();
    }

    @FXML public void accountIconExited(){
        accountLogo.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/user.png")));
    }

    @FXML public void shoppingIconEntered(){
        shoppingCartLogo.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/shopping-cart_hover.png")));
    }

    @FXML public void shoppingIconPressed() throws InterruptedException {
        shoppingCartLogo.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/shopping-cart_pressed.png")));
        openShoppingCart();
    }

    @FXML public void shoppingIconExited(){
        shoppingCartLogo.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/shopping-cart.png")));
    }

    @FXML public void shoppingCloseIconEntered(){
        closeShoppingCartIcon.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/x-mark_hover.png")));
    }
    @FXML public void shoppingCloseIconExited(){
        closeShoppingCartIcon.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/x-mark.png")));
    }


    @FXML private void handleDoneProductDetail() {
        closeDetailImageView.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/icon_close_pressed.png")));
        openShopPane();
    }

    @FXML void openShopPane() {
        shopPane.toFront();
        header.toFront();
    }

    @FXML
    void searchfavoriteCategory() {
        IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();
        searchField.clear();
        List<Product> favoriteProducts = iMatDataHandler.favorites();
        updateProductList(favoriteProducts);
    }

    @Override public void initialize(URL url, ResourceBundle rb) {

        this.checkoutPanel = new CheckoutPanel(this);
        model.getShoppingCart().addShoppingCartListener(this);
        for (Node node : categoryList.getChildren()) {
            if (node instanceof AnchorPane) {
                node.getStyleClass().remove("selected_category");
                node.getStyleClass().add("categories");
            }
        }
        for (Product product : IMatDataHandler.getInstance().getProducts()) {
            ProductPanel item = new ProductPanel(product,this);
            productMap.put(product.getName(), item);
        }

        updateProductList(model.getProducts());
        updateShoppingCartElement(model.getShoppingCart().getItems());

        StackPane namePane = new NamePanel(this);
        dynamicPane.getChildren().add(namePane);

        StackPane checkoutPane = new CheckoutPanel(this);
        dynamicPane2.getChildren().add(checkoutPane);

    }

    //Navigation:

    public void openAccountView() {
        updateAccountPanel();
        accountPane.toFront();
    }

    public void closeAccountView() {
        updateCreditCard();
        shopPane.toFront();
    }

    public void openNameView() {
        dynamicPane.toFront();
    }

    public void openCheckoutView() {dynamicPane2.toFront();}

    @FXML public void openShoppingCart() {
        shoppingCart.toFront();
    }


    public void closeNameView() {
        shopPane.toFront();
    }

    public void closeCheckoutView() {shopPane.toFront();}



    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        updateLabel();
    }

    public void updateShoppingCartElement(List<ShoppingItem> items) {


        shoppingCartFlowPane.getChildren().clear();

        for (ShoppingItem item : items) {
            shoppingCartFlowPane.getChildren().add(new imat.ShoppingCartElement(item.getProduct(), item.getAmount(),this));
        }
        checkoutPanel.updateCheckoutElement(items);

    }

    private void updateProductList(List<Product> products) {
        productsPanel.getChildren().clear();

        /*Set<String> productNames = new HashSet<>();
        for (Product product: products) {
            productNames.add(product.getName());
        }

        for (Node node : productsPanel.getChildren()) {
            if (node instanceof ProductPanel) {
                ProductPanel productPanel = (ProductPanel) node;
                productPanel.setVisible(productNames.contains(productPanel.getProduct().getName()));
            }
        }*/

        for( Product product : products) {
            productsPanel.getChildren().add(productMap.get(product.getName()));
        }


    }

    void updateAccountPanel() {

        CreditCard card = model.getCreditCard();

        numberTextField.setText(card.getCardNumber());
        nameTextField.setText(card.getHoldersName());

        cardTypeCombo.getSelectionModel().select(card.getCardType());
        monthCombo.getSelectionModel().select(""+card.getValidMonth());
        yearCombo.getSelectionModel().select(""+card.getValidYear());

        cvcField.setText(""+card.getVerificationCode());

        purchasesLabel.setText(model.getNumberOfOrders()+ " tidigare inköp hos iMat");

    }

    private void updateCreditCard() {

        CreditCard card = model.getCreditCard();

        card.setCardNumber(numberTextField.getText());
        card.setHoldersName(nameTextField.getText());

        String selectedValue = (String) cardTypeCombo.getSelectionModel().getSelectedItem();
        card.setCardType(selectedValue);

        selectedValue = (String) monthCombo.getSelectionModel().getSelectedItem();
        card.setValidMonth(Integer.parseInt(selectedValue));

        selectedValue = (String) yearCombo.getSelectionModel().getSelectedItem();
        card.setValidYear(Integer.parseInt(selectedValue));

        card.setVerificationCode(Integer.parseInt(cvcField.getText()));

    }

    private void updateLabel() {

        ShoppingCart shoppingCart = model.getShoppingCart();

        //itemsLabel.setText("Antal varor: " + String.format("%.1f",model.getCartAmount()));
        //costLabel.setText("Kostnad: " + String.format("%.2f",shoppingCart.getTotal()));

    }

    private void setupAccountPane() {

        cardTypeCombo.getItems().addAll(model.getCardTypes());

        monthCombo.getItems().addAll(model.getMonths());

        yearCombo.getItems().addAll(model.getYears());

    }

    public void updateQuantity(Product product, int quantity){
        model.updateQuantity(product, quantity);
    }
}