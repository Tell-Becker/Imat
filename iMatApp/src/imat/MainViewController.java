
package imat;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.CreditCard;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingCartListener;

public class MainViewController implements Initializable, ShoppingCartListener {

    @FXML
    private AnchorPane shopPane;

    @FXML
    private FlowPane mainViewFlowPane;

    IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }


    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {

    }
}