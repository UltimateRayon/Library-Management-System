package com.library.ui.gui;

import com.library.services.UserService;
import com.library.utils.InputValidator;
import com.library.LibraryApp;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RegisterGUI {
    private final UserService userService = new UserService();
    private final InputValidator passCheck = new InputValidator();

    public void start(Stage stage, LibraryApp app) {
        stage.setTitle("Register");

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> app.showMainMenu(stage));
        HBox topBar = new HBox(backButton);
        topBar.setAlignment(Pos.TOP_LEFT);

        Label instructions = new Label("Enter your details below:");
        TextField nameField = new TextField();
        nameField.setPromptText("Full Name");
        TextField idField = new TextField();
        idField.setPromptText("ID");
        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone Number");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        PasswordField rePasswordField = new PasswordField();
        rePasswordField.setPromptText("Re-enter Password");

        Label passwordCriteria = new Label("Password must:");
        Label criteria1 = new Label("- Be at least 6 characters long");
        Label criteria2 = new Label("- Include a special character");
        Label criteria3 = new Label("- Have uppercase and lowercase letters");
        Label criteria4 = new Label("- Contain at least one number");

        criteria1.setTextFill(Color.RED);
        criteria2.setTextFill(Color.RED);
        criteria3.setTextFill(Color.RED);
        criteria4.setTextFill(Color.RED);

        passwordField.textProperty().addListener((obs, oldVal, newVal) -> {
            criteria1.setTextFill(newVal.length() >= 6 ? Color.GREEN : Color.RED);
            criteria2.setTextFill(newVal.matches(".*[,.!@#$%^&*()].*") ? Color.GREEN : Color.RED);
            criteria3.setTextFill((newVal.matches(".*[a-z].*") && newVal.matches(".*[A-Z].*")) ? Color.GREEN : Color.RED);
            criteria4.setTextFill(newVal.matches(".*\\d.*") ? Color.GREEN : Color.RED);
        });

        VBox criteriaBox = new VBox(5, passwordCriteria, criteria1, criteria2, criteria3, criteria4);
        criteriaBox.setAlignment(Pos.TOP_LEFT);
        criteriaBox.setPadding(new Insets(5, 0, 5, 0));

        Label message = new Label();

        Button submitButton = new Button("Register");
        submitButton.setOnAction(e -> {
            String name = nameField.getText();
            String id = idField.getText();
            String phone = phoneField.getText();
            String password = passwordField.getText();
            String rePassword = rePasswordField.getText();

            if (!password.equals(rePassword)) {
                message.setText("Passwords do not match.");
            } else if (!passCheck.isValidPassword(password)) {
                message.setText("Password does not meet criteria.");
            } else {
                String success = userService.register(id, name, phone, password);
                if (success.equals("Success")) {
                    message.setText("Registration successful! You may now log in.");
                } else if(success.equals("Duplicate")){
                    message.setText("User ID already exists. Please try with another ID");
                } else {
                    message.setText("Registration failed. Please try again.");
                }
            }
        });

        HBox submitBox = new HBox(submitButton);
        submitBox.setAlignment(Pos.CENTER);

        VBox formBox = new VBox(10, topBar, instructions, nameField, idField, phoneField,
                criteriaBox, passwordField, rePasswordField, submitBox, message);
        formBox.setAlignment(Pos.TOP_LEFT);
        formBox.setPadding(new Insets(20));

        Scene scene = new Scene(formBox, 500, 570);
        stage.setScene(scene);
        stage.show();
    }
}
