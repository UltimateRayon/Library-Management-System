package com.library.ui.gui;

import com.library.LibraryApp;
import com.library.models.User;
import com.library.services.TransactionService;
import com.library.services.UserService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenuGUI {
    private final UserService userService = new UserService();
    private final TransactionService transactionService = new TransactionService();
    //private final BookSearchUI bookFind = new BookSearchUI();

    public void start(Stage stage, LibraryApp app, User user) {
        stage.setTitle("Main Menu");

        Label greeting = new Label("Welcome to the Library Management System");
        Label nameLabel = new Label("Name: " + user.getName());
        Label idLabel = new Label("ID: " + user.getId());
        Label phoneLabel = new Label("Phone: " + user.getPhone());

        Button searchButton = new Button("Search");
        //searchButton.setOnAction(e -> bookFind.bookSearch());

        Button checkoutButton = new Button("Check Out");
        checkoutButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Book Checkout");
            dialog.setHeaderText("Enter the name of the book to check out:");
            dialog.showAndWait().ifPresent(bookName -> transactionService.checkOut(bookName, user.getId()));
        });

        Button logoutButton = new Button("Log Out");
        logoutButton.setOnAction(e -> app.showMainMenu(stage));

        VBox layout = new VBox(10, greeting, nameLabel, idLabel, phoneLabel,
                searchButton, checkoutButton, logoutButton);
        layout.setAlignment(Pos.TOP_LEFT);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 450, 400);
        stage.setScene(scene);
        stage.show();
    }
}
