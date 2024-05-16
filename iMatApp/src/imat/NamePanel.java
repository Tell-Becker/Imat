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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import se.chalmers.cse.dat216.project.*;

import java.awt.*;
import java.io.IOException;

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
    @FXML ImageView accountCloseButton;

    private Model model = Model.getInstance();

    private Customer customer;
    private MainViewController mainController;

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
        this.customer = model.getCustomer();
        updateAccountDetail();

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

        accountDetailPane.toFront();
    }
    @FXML
    private void openAccountCard(ActionEvent event) {
        setupAccountPane();
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
    private void closeCardPane(ActionEvent event) {
        updateCreditCard();
        accountPane.toFront();
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

        // Save values
        customer.setFirstName(fNameTextField.getText());
        customer.setLastName(lNameTextField.getText());
        customer.setPhoneNumber(phonenrTextField.getText());
        customer.setAddress(adressTextField.getText());
        customer.setPostCode(postalnrTextField.getText());
        customer.setEmail(emailTextField.getText());
        customer.setPostAddress(cityTextField.getText());
        accountPane.toFront();

    }

}
