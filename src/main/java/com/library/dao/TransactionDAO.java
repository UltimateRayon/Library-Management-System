package com.library.dao;

import com.library.utils.DatabaseConnection;
import java.util.ArrayList;
import java.sql.*;
import java.time.Instant;
import java.util.List;

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
            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error checking OUT book: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean bookCheckIn(String bookName, String userID) {
        String sql = """
                    DELETE FROM transaction_history
                    WHERE ctid IN (
                    SELECT ctid FROM transaction_history
                    WHERE user_id = ? AND book_name = ?
                    ORDER BY transaction_date ASC
                    LIMIT 1
                    )
                """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userID) ;
            stmt.setString(2, bookName);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error check IN: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean hasUserBorrowedBook(String bookName, String userID) {
        String sql = """
                SELECT * FROM transaction_history WHERE user_id = ? AND book_name =?
                """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userID) ;
            stmt.setString(2, bookName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error UserBorrowedBook: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public List<String> getCheckOutHistory(String userId) {
        List<String> history = new ArrayList<>();
        String sql = """
                SELECT b.title, th.transaction_date
                FROM transaction_history th
                JOIN book_collection b ON th.book_name = b.title
                WHERE th.user_id = ?
                ORDER BY th.transaction_date DESC
                """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId) ;
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String title = rs.getString("title");
                Timestamp time= rs.getTimestamp("transaction_date");
                history.add(title+" - "+time);
             }
        return history;
        } catch (SQLException e) {
            System.err.println("❌ Error in getCheckOutHistory: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}




