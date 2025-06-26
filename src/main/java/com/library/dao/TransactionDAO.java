package com.library.dao;

import com.library.utils.DatabaseConnection;
import jdk.jfr.StackTrace;

import java.sql.*;
import java.time.Instant;

public class TransactionDAO {


    public boolean bookCheckOut(String bookName, String userID) {
        String sql = """
                    INSERT INTO transaction_history (transaction_date, user_id, book_name) VALUES
                    (?, ?, ?)
                    """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            Timestamp timestamp = Timestamp.from(Instant.now());
            stmt.setTimestamp(1, timestamp);
            stmt.setString(2, userID);
            stmt.setString(3, bookName);
            int rowsAffected = stmt.executeUpdate();
            if(rowsAffected>0){
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error checking out book: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
