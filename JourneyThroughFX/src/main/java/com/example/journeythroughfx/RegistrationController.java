package com.example.journeythroughfx;

import com.sun.tools.javac.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegistrationController {
    @FXML
    private TextField NameField;
    @FXML
    private TextField EmailField;
    @FXML
    private TextField PasswordField;
    @FXML
    private TextField ConfirmPassField;
    @FXML
    private Label MessageLabel;
    @FXML
    private Button goToLogin;

    @FXML
    private void register(){
        String name = NameField.getText().trim();
        String email = EmailField.getText().trim().toLowerCase();
        String pass = PasswordField.getText().trim();
        String confirmPass = ConfirmPassField.getText().trim();

        if (name.isEmpty() || email.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()){
            showError("All fields are required.");
        }
        if (!pass.equals(confirmPass)){
            showError("Password is not matching");
        }
        try{
            if(FileHolder.isUserRegistered(email)){
                showError("Already registered with this email");
                return;
            }
            FileHolder.saveUser(name, email, pass);
            HelloApplication.setRoot("logInForm");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void showError(String message){
        MessageLabel.setText(message);
        MessageLabel.setStyle("-fx-text-fill: red");
    }

    @FXML
    private void goToLogin() {
        try {
            HelloApplication.setRoot("LogInForm");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
