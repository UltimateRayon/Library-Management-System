package com.library.ui.gui;

import com.library.LibraryApp;
import com.library.models.User;
import com.library.services.UserService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginGUI {
    private final UserService userService = new UserService();

    public void start(Stage stage, LibraryApp app) {
        stage.setTitle("Log In");

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> app.showMainMenu(stage));
        HBox topBar = new HBox(backButton);
        topBar.setAlignment(Pos.TOP_LEFT);

        Label idLabel = new Label("Enter your ID:");
        TextField idField = new TextField();

        Label passLabel = new Label("Enter your password:");
        PasswordField passwordField = new PasswordField();

        Label message = new Label();

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            String id = idField.getText();
            String password = passwordField.getText();
            User user = userService.login(id, password);
            if (user != null) {
                message.setText("✅ Welcome back, " + user.getName() + "!");
                app.showMainMenuUI(stage, user);
            } else {
                message.setText("❌ Invalid ID or password.");
            }
        });

        HBox loginButtonBox = new HBox(loginButton);
        loginButtonBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox(10, topBar, idLabel, idField, passLabel, passwordField, loginButtonBox, message);
        layout.setAlignment(Pos.TOP_LEFT);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 450, 300);
        stage.setScene(scene);
        stage.show();
    }
}
