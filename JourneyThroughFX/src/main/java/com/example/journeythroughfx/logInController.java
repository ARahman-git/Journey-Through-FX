package com.example.journeythroughfx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import static com.example.journeythroughfx.FileHolder.verifyUser;

public class logInController {
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label messageLabel;

    private void logIn() {
        String email = emailField.getText().trim().toLowerCase();
        String pass = passwordField.getText().trim();

        if (email.isEmpty() || pass.isEmpty()) {
            showError("Email and password cannot be empty!");
            return;
        }

        if (verifyUser(email, pass)) {
            messageLabel.setText("Login successful!");
            messageLabel.setStyle("-fx-text-fill: green;");
            // HelloApplication.setRoot("HomePage");  // switch to another scene
        } else {
            showError("Invalid email or password!");
        }
    }


    private void showError(String msg) {
        messageLabel.setText(msg);
        messageLabel.setStyle("-fx-text-fill: red;");
    }

    @FXML
    private void goToContact(){
        HelloApplication.setRoot("ContactForm");
    }

}
