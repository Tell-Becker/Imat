/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat;

import imat.CheckoutElement;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import se.chalmers.cse.dat216.project.*;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.function.UnaryOperator;


public class CheckoutPanel extends StackPane implements ShoppingCartListener{


    @FXML
    AnchorPane checkoutPane;

    @FXML
    AnchorPane checkoutTimePane;
    @FXML
    AnchorPane checkoutDetailPane;
    @FXML
    AnchorPane checkoutCardPane;

    @FXML
    AnchorPane checkoutFinishedPane;

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

    @FXML
    private FlowPane checkoutFlowPane;
    private Model model = Model.getInstance();

    private Customer customer;
    private MainViewController mainController;

    //timepanel


        @FXML
        private Button button0810;
        @FXML
        private Button button1012;
        @FXML
        private Button button1214;
        @FXML
        private Button button1416;
        @FXML
        private Button button1618;
        @FXML
        private Button button1820;
        @FXML
        private Button button2022;
        @FXML
        private Label datLeft;
        @FXML
        private Label datCenter;
        @FXML
        private Label datRight;

        @FXML
        private ImageView leftArrow;
        @FXML
        private ImageView rightArrow;

        @FXML
        private Label deliverTime;

        @FXML private Label productSumLabel;

        @FXML private Label quantityLabel;

        @FXML private Label totalLabel;

        @FXML private Label totalLabel2;

        @FXML private Label totalLabel3;

        @FXML private Label timeErrorLabel;
        @FXML private Label detailErrorLabel;
        @FXML private Label cardErrorLabel;



        @FXML private ImageView backArrow;
        @FXML private ImageView backArrow2;
        @FXML private ImageView backArrow3;

        @FXML private ImageView backArrow4;

        private Labeled selectedTime;



    private List<Button> lista;

        String month1;
        String month2;
        String month3;

        String idagStrang;
        Date idag;
        Date igar;
        Date imorgon;

        int Igar;
        int Imorgon;
        int Idag;
        int Idag1;
        String Igarstrang;
        String Imorgonstrang;


    public CheckoutPanel(MainViewController mainController) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CheckoutPanel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.mainController = mainController;
        this.customer = model.getCustomer();
        setupCardPane();
        updateCheckoutElement(model.getShoppingCart().getItems());

        model.getShoppingCart().addShoppingCartListener(this);

        ShoppingCart shoppingCart = model.getShoppingCart();

        //numberTextField.setTextFormatter(createNumericTextFormatter(16));

        numberTextField.setTextFormatter(createNumericTextFormatter(16));
        cvcField.setTextFormatter(createNumericTextFormatter(3));

        updateTotalLabels(shoppingCart.getTotal());

        //timepane
        lista = new ArrayList<Button>();
        lista.add(button0810);
        lista.add(button1012);
        lista.add(button1214);
        lista.add(button1416);
        lista.add(button1618);
        lista.add(button1820);
        lista.add(button2022);

        month1 = "Maj";
        month2 = "Maj";
        month3 = "Maj";

        datLeft.setVisible(false);


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd");

        // Hämta dagens datum
        idag = new Date();



        // Konvertera datumet till en sträng med önskat format
        idagStrang = dateFormat.format(idag);
        Igarstrang = String.valueOf(Igar);
        Imorgonstrang = String.valueOf(Imorgon);

        Idag = (Integer.parseInt(idagStrang));
        Igar = (Integer.parseInt(idagStrang)-1);
        Imorgon = (Integer.parseInt(idagStrang)+1);
        Idag1 = (Integer.parseInt(idagStrang));


        Igarstrang = String.valueOf(Igar);
        Imorgonstrang = String.valueOf(Imorgon);
        idagStrang  = String.valueOf(Idag);



        datLeft.setText(Igarstrang + " Maj");
        datCenter.setText(idagStrang + " Maj");
        datRight.setText(Imorgonstrang + " Maj");

    }

    @FXML public void backArrowEntered() {
        backArrow.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/back-arrow_hover.png")));
    }
    @FXML public void backArrowExit() {
        backArrow.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/back-arrow.png")));
    }

    @FXML public void backArrow2Entered() {
        backArrow2.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/back-arrow_hover.png")));
    }
    @FXML public void backArrow2Exit() {
        backArrow2.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/back-arrow.png")));
    }

    @FXML public void backArrow3Entered() {
        backArrow3.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/back-arrow_hover.png")));
    }
    @FXML public void backArrow3Exit() {
        backArrow3.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/back-arrow.png")));
    }

    @FXML public void backArrow4Entered() {
        backArrow4.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/back-arrow_hover.png")));
    }
    @FXML public void backArrow4Exit() {
        backArrow4.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/back-arrow.png")));
    }

    @FXML public void leftArrowEntered() {
        leftArrow.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/left-arrow_hover.png")));
    }

    @FXML public void leftArrowExit() {
        leftArrow.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/left-arrow.png")));
    }

    @FXML public void rightArrowEntered() {
        rightArrow.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/right-arrow_hover.png")));
    }

    @FXML public void rightArrowExit() {
        rightArrow.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "imat/resources/right-arrow.png")));
    }

    public List<Button> getLista() {
        return lista;
    }


    public void highlightButton(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        for (Button buttonRemoveStyle : getLista()) {
            buttonRemoveStyle.getStyleClass().remove("highlighted-button");
            buttonRemoveStyle.getStyleClass().add("normal-button");
        }
        for (Button button : getLista()) {
            if (button == clickedButton) {
                selectedTime = button;
                button.getStyleClass().remove("normal-button");
                button.getStyleClass().add("highlighted-button");
                if (selectedTime == button0810) {
                    deliverTime.setText("Din tid för leverans är: 08-10");
                }
                if (selectedTime == button1012) {
                    deliverTime.setText("Din tid för leverans är: 10-12");
                }
                if (selectedTime == button1214) {
                    deliverTime.setText("Din tid för leverans är: 12-14");
                }
                if (selectedTime == button1416) {
                    deliverTime.setText("Din tid för leverans är: 14-16");
                }
                if (selectedTime == button1618) {
                    deliverTime.setText("Din tid för leverans är: 16-18");
                }
                if (selectedTime == button1820) {
                    deliverTime.setText("Din tid för leverans är: 18-20");
                }
                if (selectedTime == button2022) {
                    deliverTime.setText("Din tid för leverans är: 20-22");
                }
            } else {
                button.getStyleClass().remove("highlighted-button");
                button.getStyleClass().add("normal-button");
            }
        }
    }

    public static void preventDoubleClick(Button button, long delayMillis, Runnable action) {
        if (!button.isDisabled()) {
            button.setDisable(true);
            action.run();
            Platform.runLater(() -> {
                new Thread(() -> {
                    try {
                        Thread.sleep(delayMillis);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Platform.runLater(() -> button.setDisable(false));
                }).start();
            });
        }
    }



    @FXML private void leftRotation(Event event){
        if(month3 == "Maj" && Igar == (Idag1-1)){

        }
        else {
            if (Imorgon == 1) {
                Imorgon = 32;
                month1 = "Maj";
            }
            if (Idag == 1) {
                Idag = 32;
                month2 = "Maj";
            }
            if (Igar == 1) {
                Igar = 32;
                month3 = "Maj";
            }
            Igarstrang = String.valueOf(--Igar);
            Imorgonstrang = String.valueOf(--Imorgon);
            idagStrang = String.valueOf(--Idag);

            if(Idag == Idag1 && month3 == "Maj"){
                datLeft.setVisible(false);
            }else{
                datLeft.setVisible(true);

            }

            datLeft.setText(Igarstrang + " " + month3);
            datCenter.setText(idagStrang + " " + month2);
            datRight.setText(Imorgonstrang + " " + month1);

            for (Button button : getLista()) {
                button.getStyleClass().remove("highlighted-button");
                button.getStyleClass().add("normal-button");
            }
        }


    }
    @FXML private void rightRotation(Event event){

        if(month1 == "Juni" && Imorgon == 31){

        }else {

            if (Imorgon == 31) {
                Imorgon = 0;
                month1 = "Juni";
            }
            if (Idag == 31) {
                Idag = 0;
                month2 = "Juni";

            }
            if (Igar == 31) {
                Igar = 0;
                month3 = "Juni";

            }
            Igarstrang = String.valueOf(++Igar);
            Imorgonstrang = String.valueOf(++Imorgon);
            idagStrang = String.valueOf(++Idag);
            if(Idag == Idag1 && month3 == "Maj"){
                datLeft.setVisible(false);
            }else{
                datLeft.setVisible(true);

            }

            datLeft.setText(Igarstrang + " " + month3);
            datCenter.setText(idagStrang + " " + month2);
            datRight.setText(Imorgonstrang + " " + month1);

            for (Button button : getLista()) {
                button.getStyleClass().remove("highlighted-button");
                button.getStyleClass().add("normal-button");
            }
        }

    }
    @FXML public void closeCheckoutPanel() {

        // Save values
        //  customer.setFirstName(fnameTextField.getText());
        ///customer.setLastName(lnameTextField.getText());
        checkoutPane.toFront();
        mainController.closeCheckoutView();
        //mainController.resetAllProductPanels();
    }

    @FXML public void closeCheckoutPanelFinished() {

        // Save values
        //  customer.setFirstName(fnameTextField.getText());
        ///customer.setLastName(lnameTextField.getText());
        checkoutPane.toFront();
        mainController.closeCheckoutView();
        mainController.resetAllProductPanels();
    }

    @FXML public void keepBuying() {
        mainController.closeCheckoutView();
    }

    @FXML private void openCheckout(Event event) {
        checkoutPane.toFront();
    }

    @FXML private void openCheckoutTime(Event event) {
        checkoutTimePane.toFront();
    }
    @FXML private void openCheckoutCard(ActionEvent event) {
        if (fNameTextField.getLength() !=0 && lNameTextField.getLength() !=0 && emailTextField.getLength() !=0 && phonenrTextField.getLength() !=0 && adressTextField.getLength() !=0 && postalnrTextField.getLength() !=0 && cityTextField.getLength() !=0)
        {
            updateCardPanel();
            checkoutCardPane.toFront();
            detailErrorLabel.setText("");
        }
        else {
            detailErrorLabel.setText("Vänligen fyll i alla fält");
        }
    }

    @FXML private void openCheckoutDetail(Event event) {
        if (selectedTime != null) {
            updateAccountDetail();
            checkoutDetailPane.toFront();
            timeErrorLabel.setText("");
        }
        else{
            timeErrorLabel.setText("Vänligen välj en tid");
        }
    }

    @FXML private void openCheckoutDetail2(Event event) {
        checkoutDetailPane.toFront();
    }

    @FXML private void openCheckoutFinished(ActionEvent event) {
        if (selectedTime != null && fNameTextField.getLength() !=0 && lNameTextField.getLength() !=0 && emailTextField.getLength() !=0 && phonenrTextField.getLength() !=0 && adressTextField.getLength() !=0 && postalnrTextField.getLength() !=0 && cityTextField.getLength() !=0 && numberTextField.getLength() != 0 && nameTextField.getLength() != 0 && cvcField.getLength() != 0) {
            BuyItems();
            checkoutFlowPane.getChildren().clear();
            mainController.shoppingCartFlowPane.getChildren().clear();
            mainController.updateLabel();
            checkoutFinishedPane.toFront();
            cardErrorLabel.setText("");
        }
        else if(numberTextField.getLength() != 0 && nameTextField.getLength() != 0 && cvcField.getLength() != 0)
        {
            cardErrorLabel.setText("Vänligen fyll i alla fält i de tidigare stegen");
        }
        else {
            cardErrorLabel.setText("Vänligen fyll i alla fält");
        }
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

    void setupCardPane() {

        cardTypeCombo.getItems().addAll(model.getCardTypes());

        monthCombo.getItems().addAll(model.getMonths());

        yearCombo.getItems().addAll(model.getYears());

    }

    @FXML private void updateCreditCard(ActionEvent event) {

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

    @FXML private void updateAccountDetail() {


        fNameTextField.setText(customer.getFirstName());
        lNameTextField.setText(customer.getLastName());
        phonenrTextField.setText(customer.getPhoneNumber());
        adressTextField.setText(customer.getAddress());
        postalnrTextField.setText(customer.getPostCode());
        emailTextField.setText(customer.getEmail());
        cityTextField.setText(customer.getPostAddress());

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

    public void updateCheckoutElement(List<ShoppingItem> items) {

        checkoutFlowPane.getChildren().clear();

        for (ShoppingItem item : items) {
            checkoutFlowPane.getChildren().add(new CheckoutElement(item.getProduct(), item.getAmount(), mainController, this));
        }
    }

    @FXML private void BuyItems() {
        model.placeOrder();
    }


    private void updateTotalLabels(double total) {
        totalLabel.setText("Kostnad: " + String.format("%.2f", total) + " Kr");
        totalLabel2.setText("Kostnad: " + String.format("%.2f", total) + " Kr");
        //totalLabel3.setText("Kostnad: " + String.format("%.2f", total) + " Kr");
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        double total = model.getShoppingCart().getTotal();
        updateTotalLabels(total);
    }


    @FXML public void wizard1(ActionEvent event) {
        checkoutPane.toFront();
        updateAccountDetail();
        updateCardPanel();


    }

    @FXML public void wizard2(ActionEvent event) {
        checkoutTimePane.toFront();
        updateAccountDetail();
        updateCardPanel();

    }

    @FXML public void wizard3(ActionEvent event) {
        checkoutDetailPane.toFront();
        updateAccountDetail();
        updateCardPanel();

    }

    @FXML public void wizard4(ActionEvent event) {
        checkoutCardPane.toFront();
        updateAccountDetail();
        updateCardPanel();

    }
}
