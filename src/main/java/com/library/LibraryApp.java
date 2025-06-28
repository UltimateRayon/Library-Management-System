package com.library;

import com.library.models.User;
import com.library.ui.gui.LoginGUI;
import com.library.ui.gui.MainMenuGUI;
import com.library.ui.gui.RegisterGUI;
import com.library.utils.AppContext;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LibraryApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Library Management System");

        showMainMenu(primaryStage);
    }

    public void showMainMenu(Stage primaryStage) {
        Button registerButton = new Button("Register");
        Button loginButton = new Button("Login");

        RegisterGUI registerGUI = new RegisterGUI();
        LoginGUI loginGUI = new LoginGUI();

        registerButton.setOnAction(e -> registerGUI.start(primaryStage, this));
        loginButton.setOnAction(e -> loginGUI.start(primaryStage, this));

        VBox vbox = new VBox(15, registerButton, loginButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setMinWidth(300);

        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showMainMenuUI(Stage stage, User user) {
        MainMenuGUI menuGUI = new MainMenuGUI();
        menuGUI.start(stage, this, user);
    }

    public static void main(String[] args) {

        launch(args);
    }
}


