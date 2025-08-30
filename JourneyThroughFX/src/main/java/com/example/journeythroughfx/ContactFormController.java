package com.example.journeythroughfx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;



public class ContactFormController{
    @FXML
    private TextField nameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField mobileField;
    @FXML
    private Label messageLabel;

    @FXML
    private void contact(){
        String name = nameField.getText().trim();
        String address = addressField.getText().trim();
        String mobile = mobileField.getText().trim();

        if (name.isEmpty() || mobile.isEmpty() || address.isEmpty()){
            showError("All field are required");
            return;
        }
        if (!FileHolder.isValidMobile(mobile)) {
            showError("Invalid mobile number.");
            return;
        }

        try {
            if (FileHolder.isMobileExists(mobile)) {
                showError("Mobile number already exists.");
                return;
            }
            FileHolder.writeContact(name, address, mobile);
            showSuccess("Contact added.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void showError(String msg) {
        messageLabel.setText(msg);
        messageLabel.setStyle("-fx-text-fill: red;");
    }

    private void showSuccess(String msg) {
        messageLabel.setText(msg);
        messageLabel.setStyle("-fx-text-fill: green;");
    }

    @FXML
    private void logOutB(){
        HelloApplication.setRoot("logInForm");
    }

}



