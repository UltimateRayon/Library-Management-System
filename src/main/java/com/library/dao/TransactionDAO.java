package com.library.dao;

import com.library.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionDAO {


    public boolean bookCheckOut(String bookName, String userID) {
        String sql = """
                    SELECT * INSERT INTO transaction_history (transaction_id, user_id, book_id, transaction_date) VALUES
                    (1, ?, ?, 10)
                    """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userID);
            stmt.setString(2, bookName);
            stmt.executeQuery();

        } catch (SQLException e) {
            System.err.println("❌ Error checking out book: " + e.getMessage());
        }
        return false;
    }
}
