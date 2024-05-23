
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import se.chalmers.cse.dat216.project.*;
import se.chalmers.cse.dat216.project.ProductCategory;

import javax.swing.*;


public class MainViewController implements Initializable, ShoppingCartListener {

    private List<ProductPanel> productPanels = new ArrayList<>();

    @FXML private AnchorPane header;
    @FXML private ImageView accountLogo;
    @FXML private ImageView shoppingCartLogo;
    @FXML private ImageView imatHome;
    @FXML private AnchorPane program;
    @FXML private StackPane shopPane;
    @FXML private TextField searchField;
    @FXML private Label itemsLabel;
    @FXML private Label costLabel;
    @FXML private FlowPane mainViewFlowPane;
    @FXML private FlowPane productsPanel;
    @FXML private FlowPane advertisementFlowPane;
    @FXML private AnchorPane accountContactPane;

    @FXML private ScrollPane home;
    @FXML private ScrollPane shop;

    private Model model = Model.getInstance();
    private Map<String, ProductPanel> productMap = new HashMap();

    //Categories:
    @FXML private AnchorPane categoryList;
    @FXML private AnchorPane favoriteCategory;
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

    private NamePanel namepanel;

    @FXML public FlowPane shoppingCartFlowPane;

    @FXML private AnchorPane dynamicPane;
    @FXML private AnchorPane dynamicPane2;

    //Shoppingcart
    @FXML private AnchorPane shoppingCart;

    @FXML private ImageView closeShoppingCartIcon;
    @FXML private ImageView backArrow;

    @FXML private ImageView ContactUsClose;

    @FXML private ImageView aboutImatClose;

    @FXML private AnchorPane aboutImat;

    @FXML private ImageView buyInfoClose;
    @FXML private AnchorPane buyInfo;

    @FXML private Label errorLabel;

    private ShoppingCart shoppingCartInstance;

    // Other variables:
    IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();

    CheckoutPanel checkoutPanel;

    // Shop pane actions:

    //private int pageSize = 20;
    //private int currentPage = 0;

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

    private void removeAllSelectedCategories() {
        favoriteCategory.getStyleClass().remove("selected_category");;
        meatCategory.getStyleClass().remove("selected_category");;
        fishCategory.getStyleClass().remove("selected_category");;
        dairyCategory.getStyleClass().remove("selected_category");;
        carbCategory.getStyleClass().remove("selected_category");;
        bakeryCategory.getStyleClass().remove("selected_category");;
        vegetableCategory.getStyleClass().remove("selected_category");;
        fruitCategory.getStyleClass().remove("selected_category");;
        pantryCategory.getStyleClass().remove("selected_category");;
        snackCategory.getStyleClass().remove("selected_category");;
        drinkCategory.getStyleClass().remove("selected_category");;
    }


    @FXML private void handleSearchAction(ActionEvent event) {
        shop.toFront();
        header.toFront();
        for (Node node : categoryList.getChildren()) {
            if (node instanceof AnchorPane) {
                node.getStyleClass().remove("selected_category");
                node.getStyleClass().add("categories");
            }
        }
        List<Product> matches = model.findProducts(searchField.getText());
        updateProductList(matches);
        System.out.println("# matching products: " + matches.size());

        shop.setVvalue(0.0);
    }

    private void handleNameAction() {
        namepanel.openAccountPanel();
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

    private List<Product> filterProductsByCategory(ProductCategory category) {
        List<Product> filteredProducts = new ArrayList<>();
        List<Product> allProducts = model.getProducts();
        for (Product product: allProducts) {
            if(product.getCategory() == category) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    @FXML private void searchfavoriteCategory() {
        shop.setVvalue(0.0);

        IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();
        searchField.clear();
        selectCategory(favoriteCategory);
        List<Product> favoriteProducts = iMatDataHandler.favorites();
        updateProductList(favoriteProducts);
    }

    @FXML private void searchMeatCategory() {
        shop.setVvalue(0.0);

        searchField.clear();
        selectCategory(meatCategory);
        List<Product> meatProducts = filterProductsByCategory(ProductCategory.MEAT);
        updateProductList(meatProducts);
    }
    @FXML private void searchFishCategory() {
        shop.setVvalue(0.0);

        searchField.clear();
        selectCategory(fishCategory);
        List<Product> fishProducts = filterProductsByCategory(ProductCategory.FISH);
        updateProductList(fishProducts);
    }

    @FXML private void searchDairyCategory() {
        shop.setVvalue(0.0);

        searchField.clear();
        selectCategory(dairyCategory);
        List<Product> dairyProducts = filterProductsByCategory(ProductCategory.DAIRIES);
        updateProductList(dairyProducts);
    }

    @FXML private void searchCarbCategory() {
        shop.setVvalue(0.0);

        searchField.clear();
        selectCategory(carbCategory);
        List<Product> breadProducts = filterProductsByCategory(ProductCategory.BREAD);
        List<Product> pastaProducts = filterProductsByCategory(ProductCategory.PASTA);
        List<Product> potato_riceProducts = filterProductsByCategory(ProductCategory.POTATO_RICE);
        List<Product> carbProducts = new ArrayList<>();
        carbProducts.addAll(breadProducts);
        carbProducts.addAll( pastaProducts);
        carbProducts.addAll( potato_riceProducts);
        updateProductList(carbProducts);
    }

    @FXML private void searchBakeryCategory() {
        shop.setVvalue(0.0);

        searchField.clear();
        selectCategory(bakeryCategory);
        List<Product> bakeryProducts = filterProductsByCategory(ProductCategory.BREAD);

        List<Product> products = model.getProducts();
        for (Product product : products) {
            if(Objects.equals(product.getName(), "Kanelbullar")) {
                bakeryProducts.add(product);
            }

        }
        updateProductList(bakeryProducts);
    }

    @FXML private void searchFruitCategory() {
        shop.setVvalue(0.0);

        searchField.clear();
        selectCategory(fruitCategory);
        List<Product> fruitProducts = filterProductsByCategory(ProductCategory.FRUIT);
        List<Product> berryProducts = filterProductsByCategory(ProductCategory.BERRY);
        List<Product> citrusProducts = filterProductsByCategory(ProductCategory.CITRUS_FRUIT);
        List<Product> melonProducts = filterProductsByCategory(ProductCategory.MELONS);
        List<Product> exoticProducts = filterProductsByCategory(ProductCategory.EXOTIC_FRUIT);
        List<Product> vegetableFruitProducts = filterProductsByCategory(ProductCategory.VEGETABLE_FRUIT);
        List<Product> allFruit = new ArrayList<>();
        allFruit.addAll(fruitProducts);
        allFruit.addAll(berryProducts);
        allFruit.addAll(citrusProducts);
        allFruit.addAll(melonProducts);
        allFruit.addAll(exoticProducts);
        allFruit.addAll(vegetableFruitProducts);
        updateProductList(allFruit);

    }

    @FXML private void searchVegetableCategory() {
        shop.setVvalue(0.0);

        searchField.clear();
        selectCategory(vegetableCategory);
        List<Product> cabbage = filterProductsByCategory(ProductCategory.CABBAGE);
        List<Product> herb = filterProductsByCategory(ProductCategory.HERB);
        List<Product> root = filterProductsByCategory(ProductCategory.ROOT_VEGETABLE);
        List<Product> pod = filterProductsByCategory(ProductCategory.POD);
        List<Product> vegetableFruit = filterProductsByCategory(ProductCategory.VEGETABLE_FRUIT);
        List<Product> allVegetable = new ArrayList<>();
        allVegetable.addAll(cabbage);
        allVegetable.addAll(herb);
        allVegetable.addAll(root);
        allVegetable.addAll(pod);
        allVegetable.addAll(vegetableFruit);
        updateProductList(allVegetable);
    }

    @FXML private void searchPantryCategory() {
        shop.setVvalue(0.0);

        searchField.clear();
        selectCategory(pantryCategory);
        List<Product> pantryProducts = filterProductsByCategory(ProductCategory.FLOUR_SUGAR_SALT);
        updateProductList(pantryProducts);
    }

    @FXML private void searchSweetCategory() {
        shop.setVvalue(0.0);

        searchField.clear();
        selectCategory(snackCategory);
        List<Product> sweetProducts = filterProductsByCategory(ProductCategory.SWEET);
        List<Product> nutsProducts = filterProductsByCategory(ProductCategory.NUTS_AND_SEEDS);
        List<Product> allSnacks = new ArrayList<>();
        allSnacks.addAll(sweetProducts);
        allSnacks.addAll(nutsProducts);

        updateProductList(allSnacks);


    }

    @FXML private void searchDrinkCategory() {
        shop.setVvalue(0.0);

        shop.setVvalue(0.0);
        searchField.clear();
        selectCategory(drinkCategory);
        List<Product> coldDrinks = filterProductsByCategory(ProductCategory.COLD_DRINKS);
        List<Product> hotDrinks = filterProductsByCategory(ProductCategory.HOT_DRINKS);
        List<Product> allDrinks = new ArrayList<>();
        allDrinks.addAll(coldDrinks);
        allDrinks.addAll(hotDrinks);
        updateProductList(allDrinks);
    }

    @FXML public void closeHome(ActionEvent event) {
        shop.toFront();
        header.toFront();
        searchfavoriteCategory();
    }

    @FXML public void home() {
        shopPane.toFront();
        shop.toFront();
        //home.toFront();
        //searchField.clear();
        //removeAllSelectedCategories();
        //List<Product> allProducts = model.getProducts();
        //updateProductList(allProducts);
        //everythingCategory.getStyleClass().remove("selected_category");
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

    @FXML public void imatIconEntered(){
        imatHome.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/imatLogo.png")));
    }

    @FXML public void imatIconPressed() throws InterruptedException {
        imatHome.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/imatLogo_pressed.png")));
        home();
    }

    @FXML public void imatIconExited(){
        imatHome.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/IMAT_logo_transparent.png")));
    }
    @FXML public void closeContactIconEntered(){
        ContactUsClose.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/icon_close_hover.png")));
    }
    @FXML public void closeContactIconExited(){
        ContactUsClose.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/icon_close.png")));
    }

    @FXML public void closeAboutImatIconEntered(){
        aboutImatClose.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/icon_close_hover.png")));
    }
    @FXML public void closeAboutImatIconExited(){
        aboutImatClose.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/icon_close.png")));
    }

    @FXML public void closeBuyInfoIconEntered(){
        buyInfoClose.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/icon_close_hover.png")));
    }
    @FXML public void closeBuyInfoIconExited(){
        buyInfoClose.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/icon_close.png")));
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

    @Override public void initialize(URL url, ResourceBundle rb) {
        //loadProducts(currentPage);
        this.checkoutPanel = new CheckoutPanel(this);
        model.getShoppingCart().addShoppingCartListener(this);

        for (Node node : categoryList.getChildren()) {
            if (node instanceof AnchorPane) {
                node.getStyleClass().remove("selected_category");
                node.getStyleClass().add("categories");
            }
        }

        List<Product> products = model.getProducts();
        int amount = 0;
        for (Product product : products) {
            ProductPanel item = new ProductPanel(product,this);

            item.getProduct();
            productMap.put(product.getName(), item);
            //productsPanel.getChildren().add(item);
        }
        searchfavoriteCategory();
        updateLabel();
        model.getShoppingCart().addShoppingCartListener(this::shoppingCartChanged);
        shoppingCartInstance = model.getShoppingCart();

        //updateProductList(model.getProducts());
        updateShoppingCartElement(model.getShoppingCart().getItems());

        namepanel = new NamePanel(this);


        StackPane namePane = namepanel;
        dynamicPane.getChildren().add(namePane);

        StackPane checkoutPane = checkoutPanel;
        dynamicPane2.getChildren().add(checkoutPane);

        List<Product> homeproducts = model.getProducts();
        for (Product product : products) {
            ProductPanel item = productMap.get(product.getName());
            if(Objects.equals(product.getName(), "Kanelbullar")) {
                advertisementFlowPane.getChildren().add(item);
            }
            if(Objects.equals(product.getName(), "Mjölk")) {
                advertisementFlowPane.getChildren().add(item);
            }
            if(Objects.equals(product.getName(), "Ägg")) {
                advertisementFlowPane.getChildren().add(item);
            }

        }

    }

    /*private void loadProducts(int page) {
        int startIndex = page * pageSize;
        int endIndex = Math.min(startIndex + pageSize, model.getProducts().size());

        List<Product> products = model.getProducts().subList(startIndex, endIndex);
        updateProductList(products);
    }

    @FXML private void nextPage() {
        currentPage++;
        loadProducts(currentPage);
    }

    @FXML private void previousPage() {
        if (currentPage > 0) {
            currentPage--;
            loadProducts(currentPage);
        }
    }*/

    //Navigation:


    public void openNameView() {
        dynamicPane.toFront();
        header.toFront();
    }

    public void registerProductPanel(ProductPanel productPanel) {
        productPanels.add(productPanel);
    }

    public void resetAllProductPanels() {
        for (ProductPanel panel : productPanels) {
            panel.resetQuantity();
        }
    }

    @FXML public void openShoppingCart() {
        shoppingCart.toFront();
        header.toFront();
    }

    @FXML void mouseTrap(Event event){
        event.consume();
    }


    public void closeNameView() {
        shopPane.toFront();
        header.toFront();
    }

    public void closeCheckoutView() {
        shopPane.toFront();
        header.toFront();
    }



    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        //updateShoppingCartElement(iMatDataHandler.getShoppingCart().getItems());
        //updateLabel();
        //updateShoppingCartElement(model.getShoppingCart().getItems());
        //updateLabel();
        //updateProductQuantities();

        //updateLabel();
        //updateProductQuantities();

    }

    public void updateProductQuantities() {
        Map<Product, Integer> productQuantities = new HashMap<>();
        for (ShoppingItem item : model.getShoppingCart().getItems()) {
            productQuantities.put(item.getProduct(), (int) item.getAmount());
        }

        for (ProductPanel panel : productPanels) {
            Integer quantity = productQuantities.get(panel.getProduct());
            if (quantity != null) {
                panel.updateQuantity(quantity);
            } else {
                panel.resetQuantity();
            }
        }
    }

    public void updateShoppingCartElement(List<ShoppingItem> items) {

        Map<String, ShoppingItem> itemMap = new HashMap<>();
        for (ShoppingItem item : items) {
            itemMap.put(item.getProduct().getName(), item);
        }

        shoppingCartFlowPane.getChildren().clear();

        for (ShoppingItem item : items) {
            shoppingCartFlowPane.getChildren().add(new ShoppingCartElement(item.getProduct(), item.getAmount(),this, checkoutPanel));
        }
        checkoutPanel.updateCheckoutElement(items);
        updatePriceLabel();

    }
    public void updatePriceLabel(){
        costLabel.setText(String.valueOf("Kostnad: " + String.format("%.2f", shoppingCartInstance.getTotal()) + " kr"));
    }

    public void checkoutElementUpdateShoppingCartElement(List<ShoppingItem> items) {

        Map<String, ShoppingItem> itemMap = new HashMap<>();
        for (ShoppingItem item : items) {
            itemMap.put(item.getProduct().getName(), item);
        }

        shoppingCartFlowPane.getChildren().clear();

        for (ShoppingItem item : items) {
            shoppingCartFlowPane.getChildren().add(new ShoppingCartElement(item.getProduct(), item.getAmount(),this, checkoutPanel));
        }

    }

    private void updateProductList(List<Product> products) {
        productsPanel.getChildren().clear();

        for (Product product : products) {
            ProductPanel item = productMap.get(product.getName());
            if (item != null) {
                productsPanel.getChildren().add(item);
            } else {
                System.out.println("Fel");
            }
        }

    }

    @FXML
    private void handleDoneShoppingCart() {
        shoppingcartback();
        errorLabel.setText("");
    }

    public void shoppingcartback() {
        shoppingCart.toBack();

        //updateProductList();
    }

    public void openCheckoutView() {

        if (model.getCartAmount() != 0){
            dynamicPane2.toFront();
            header.toFront();
        }
        else {
            errorLabel.setText("Vänligen lägg till en produkt i kundvagnen");
        }

    }


    public void updateLabel() {
        itemsLabel.setText( String.format("%d",(int) model.getCartAmount()));

    }

    @FXML public void contactUs() {
        accountContactPane.toFront();
        header.toFront();
    }

    @FXML public void aboutUs() {
        aboutImat.toFront();
        header.toFront();
    }

    @FXML public void buyInfo() {
        buyInfo.toFront();
        header.toFront();
    }

    @FXML public void advertisment() {
        advertisementFlowPane.getChildren().clear();
        List<Product> homeproducts = model.getProducts();
        for (Product product : homeproducts) {
            ProductPanel item = productMap.get(product.getName());
            if(Objects.equals(product.getName(), "Kanelbullar")) {
                advertisementFlowPane.getChildren().add(item);
            }
            if(Objects.equals(product.getName(), "Mjölk")) {
                advertisementFlowPane.getChildren().add(item);
            }
            if(Objects.equals(product.getName(), "Ägg")) {
                advertisementFlowPane.getChildren().add(item);
            }

        }
        home.toFront();
        header.toFront();
        shop.setVvalue(0.0);
    }

}