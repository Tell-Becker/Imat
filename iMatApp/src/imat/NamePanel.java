/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import se.chalmers.cse.dat216.project.*;


import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.function.UnaryOperator;

/**
 *
 * @author oloft
 */
public class NamePanel extends StackPane {


    @FXML
    TextArea cartTextArea;

    @FXML
    AnchorPane accountHistoryPane;

    @FXML
    AnchorPane accountDetailPane;
    @FXML
    AnchorPane accountCardPane;
    @FXML
    AnchorPane accountContactPane;

    @FXML
    AnchorPane accountPane;

    @FXML AnchorPane accountOrderDetail;

    @FXML
    ComboBox cardTypeCombo;
    @FXML
    private TextField numberTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private ComboBox monthCombo;
    @FXML
    private ComboBox yearCombo;

    @FXML
    private TextField cvcField;

    @FXML TextField fNameTextField;

    @FXML TextField lNameTextField;
    @FXML TextField cityTextField;
    @FXML TextField phonenrTextField;
    @FXML TextField postalnrTextField;
    @FXML TextField emailTextField;
    @FXML TextField adressTextField;

    @FXML private FlowPane historyFlowPane;

    @FXML
    AnchorPane orderDetail;

    @FXML Label orderDate2;

    @FXML TextArea orderDetailTextArea;

    private Order order;

    private Model model = Model.getInstance();

    @FXML ImageView accountCloseButton;
    @FXML ImageView accountInfoCloseButton;

    @FXML ImageView accountCloseHistoryButton;

    @FXML ImageView accountCardCloseButton;

    @FXML ImageView accountContactCloseButton;

    private Customer customer;
    private MainViewController mainController;

    private CheckoutPanel checkoutPanel;

    private Order currentOrder;

    public NamePanel(MainViewController mainController) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NamePanel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.mainController = mainController;
        //this.checkoutPanel = checkoutPanel;
        this.customer = model.getCustomer();
        updateAccountDetail();
        updateOrderElement(model.getOrders());

        setupAccountPane();

        numberTextField.setTextFormatter(createNumericTextFormatter(16));
        cvcField.setTextFormatter(createNumericTextFormatter(3));

    }

    @FXML
    public void closeNameView(Event event) {

        // Save values
        //  customer.setFirstName(fnameTextField.getText());
        ///customer.setLastName(lnameTextField.getText());
        mainController.closeNameView();

    }

    @FXML public void closeAccountEntered(Event event) {
        closeEntered(accountCloseButton);
    }

    @FXML public void closeAccountExit(Event event) {
        closeExit(accountCloseButton);
    }

    @FXML public void closeAccountinfoEntered(Event event) {
        closeEntered(accountInfoCloseButton);
    }

    @FXML public void closeAccountinfoExit(Event event) {
        closeExit(accountInfoCloseButton);
    }

    @FXML public void closeAccountHistoryEntered(Event event) {
        closeEntered(accountCloseHistoryButton);
    }

    @FXML public void closeAccountHistoryExit(Event event) {
        closeExit(accountCloseHistoryButton);
    }

    @FXML public void closeAccountCardEntered(Event event) {
        closeEntered(accountCardCloseButton);
    }

    @FXML public void closeAccountCardExit(Event event) {
        closeExit(accountCardCloseButton);
    }

    @FXML public void closeAccountContactEntered(Event event) {
        closeEntered(accountContactCloseButton);
    }

    @FXML public void closeAccountContactExit(Event event) {
        closeExit(accountContactCloseButton);
    }

    public void closeEntered(ImageView closeButton){
        closeButton.setImage(new javafx.scene.image.Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/icon_close_hover.png")));
    }

    public void closeExit(ImageView closeButton){
        closeButton.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/icon_close.png")));
    }


    @FXML
    private void openAccountHistory(ActionEvent event) {
        accountHistoryPane.toFront();
    }

    @FXML
    private void openAccountDetail(ActionEvent event) {
        updateAccountDetail();
        accountDetailPane.toFront();
    }
    @FXML
    private void openAccountCard(ActionEvent event) {
        updateCardPanel();
        accountCardPane.toFront();
    }
    @FXML
    private void openAccountContact(ActionEvent event) {
        accountContactPane.toFront();
    }
    @FXML
    private void openAccountPane(ActionEvent event) {
        accountPane.toFront();
    }
    @FXML
    private void closeCardPane() {
        accountPane.toFront();
    }


    @FXML
    private void handleAddToCart(ActionEvent event){
        addToCart();
    }

    void updateCardPanel() {

        CreditCard card = model.getCreditCard();

        numberTextField.setText(card.getCardNumber());
        nameTextField.setText(card.getHoldersName());

        cardTypeCombo.getSelectionModel().select(card.getCardType());
        monthCombo.getSelectionModel().select("" + card.getValidMonth());
        yearCombo.getSelectionModel().select("" + card.getValidYear());
        cvcField.setText(""+card.getVerificationCode());
    }

    void setupAccountPane() {

        cardTypeCombo.getItems().addAll(model.getCardTypes());

        monthCombo.getItems().addAll(model.getMonths());

        yearCombo.getItems().addAll(model.getYears());

    }

    @FXML private void updateCreditCard() {

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

    private void updateAccountDetail() {


        fNameTextField.setText(customer.getFirstName());
        lNameTextField.setText(customer.getLastName());
        phonenrTextField.setText(customer.getPhoneNumber());
        adressTextField.setText(customer.getAddress());
        postalnrTextField.setText(customer.getPostCode());
        emailTextField.setText(customer.getEmail());
        cityTextField.setText(customer.getPostAddress());

    }

    @FXML
    public void closeAccountDetail(){
        accountPane.toFront();

    }

    @FXML private void saveAccountDetail(ActionEvent event){

        // Save values
        customer.setFirstName(fNameTextField.getText());
        customer.setLastName(lNameTextField.getText());
        customer.setPhoneNumber(phonenrTextField.getText());
        customer.setAddress(adressTextField.getText());
        customer.setPostCode(postalnrTextField.getText());
        customer.setEmail(emailTextField.getText());
        customer.setPostAddress(cityTextField.getText());
        updateAccountDetail();

    }


    public void updateOrderElement(List<Order> orders) {
        historyFlowPane.getChildren().clear();

        for (Order order : orders.reversed()) {

            historyFlowPane.getChildren().add(new OrderElement(order, this));
        }
    }

    void openOrderDetail(Order order) {
        populateOrderDetail(order);
        accountOrderDetail.toFront();
    }

    private void populateOrderDetail(Order order) {
        currentOrder = order;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", new Locale("sv", "SE"));
        String formattedDate = sdf.format(order.getDate());

        orderDate2.setText(formattedDate);

        StringBuilder stringBuilder = new StringBuilder();
        String headerFormat = "%-23s %-15s %-10s %-10s\n\n";
        String rowFormat = "%-23s %-15s %-10s %-10.2f\n";

        stringBuilder.append(String.format(headerFormat, "Produkt", "Pris", "Antal", "Totalt"));

        double grandTotal = 0;

        for (ShoppingItem item : order.getItems()) {
            String productName = item.getProduct().getName();
            double price = item.getProduct().getPrice();
            String productPrice = String.format("%.2f %s", price, item.getProduct().getUnit());
            Integer quantity = (int)item.getAmount();
            double total = price * quantity;
            grandTotal += total;

            stringBuilder.append(String.format(rowFormat, productName, productPrice, quantity, total));
        }

        stringBuilder.append("\n");
        stringBuilder.append(String.format("%-23s %-15s %-10s %-10.2f\n\n", "Total kostnad:", "", "", grandTotal));

        orderDetailTextArea.setText(stringBuilder.toString());

    }

    private void addToCart() {
        for (ShoppingItem item : currentOrder.getItems()) {
            model.addToShoppingCart(item.getProduct());
            mainController.updateShoppingCartElement(model.getShoppingCart().getItems());
        }
        accountHistoryPane.toFront();
    }

    private TextFormatter<String> createNumericTextFormatter(int maxLength) {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*") && newText.length() <= maxLength) {
                return change;
            }
            return null;
        };
        return new TextFormatter<>(filter);
    }
}
