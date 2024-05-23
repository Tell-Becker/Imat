package imat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Order;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class OrderElement extends AnchorPane {

    private Order order;
    private List<Order> orders;

    private NamePanel namePanel;
    @FXML
    Label orderDate;

    public OrderElement(Order order, NamePanel namePanel) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderElement.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.order = order;
        this.namePanel = namePanel;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd       HH:mm");
        String formattedDate = dateFormat.format(order.getDate());
        orderDate.setText(formattedDate);

    }

    @FXML
    private void handleShowOrderDetail(ActionEvent event) {

        namePanel.openOrderDetail(order);
    }
}
