
package imat;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import se.chalmers.cse.dat216.project.*;

public class MainViewController implements Initializable, ShoppingCartListener {

    @FXML
    private AnchorPane shopPane;

    @FXML
    private TextField searchField;

    @FXML
    private FlowPane mainViewFlowPane;

    @FXML
    private FlowPane productsPanel;

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

    // Other variables:
    IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();
    private final Model model = Model.getInstance();

    // Shop pane actions:

    private void productDetail(Product product) {
        ProductDetail detail = model.getDetail(product);
    }

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

    @FXML
    public void home() {
        searchEverythingCategory();
        everythingCategory.getStyleClass().remove("selected_category");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model.getShoppingCart().addShoppingCartListener(this);
        for (Node node : categoryList.getChildren()) {
            if (node instanceof AnchorPane) {
                node.getStyleClass().remove("selected_category");
                node.getStyleClass().add("categories");
            }
        }
        updateProductList(model.getProducts());
        //updateBottomPanel();
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {

    }

    private void updateProductList(List<Product> products) {

        System.out.println("updateProducts " + products.size());
        productsPanel.getChildren().clear();

        for( Product product : products) {
            productsPanel.getChildren().add(new ProductPanel(product));
        }
    }
}