package com.library.dao;

import com.library.models.User;
import com.library.utils.DatabaseConnection;

import java.sql.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class UserDAO {

    public User getUserById(String id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("password"),
                        rs.getInt("fine"),
                        rs.getInt("borrowed_books"),
                        rs.getTimestamp("last_login_time")
                );
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching user: " + e.getMessage());
        }
        return null;
    }

    public boolean saveUser(User user) {
        String sql = "INSERT INTO users (id, name, phone, password, fine, borrowed_books, last_login_time) VALUES (?, ?, ?, ?, 0, 0, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getPhone());
            stmt.setString(4, user.getPassword());
            stmt.setTimestamp(5, user.getTime());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error saving user: " + e.getMessage());
            return false;
        }
    }

    public boolean updateFine(String userId, long time){
        String sql = """
                UPDATE users
                SET fine=CASE
                WHEN fine - ?<0 THEN 0
                ELSE fine - ?
                END,
                    last_login_time= ?
                WHERE id=?;
                """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            Timestamp currentTime=Timestamp.from(Instant.now());
            stmt.setInt(1, (int)time);
            stmt.setInt(2, (int)time);
            stmt.setTimestamp(3, currentTime);
            stmt.setString(4, userId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error updating fine and time: " + e.getMessage());
        }
        return false;
    }

    public boolean borrowBookUpdate(String userId, int amount){
        String sql = """
                UPDATE users
                SET borrowed_books=borrowed_books + ?
                WHERE id=?;
                """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {


            stmt.setInt(1, amount);
            stmt.setString(2, userId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error updating borrowed book: " + e.getMessage());
        }
        return false;
    }

    public int compareDate(String bookName, String id) {
        String sql = "SELECT * FROM transaction_history WHERE user_id = ? and book_name= ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.setString(2, bookName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Timestamp checkoutTime=rs.getTimestamp("transaction_date");
                Timestamp currentTime=Timestamp.from(Instant.now());
                long timeDifference= ChronoUnit.DAYS.between(checkoutTime.toLocalDateTime(), currentTime.toLocalDateTime())-15;
                return Math.max((int) timeDifference, 0);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error compare date: " + e.getMessage());
        }
        return 0;
    }

    public void fetchUserInfo(User user){
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                        int fine=rs.getInt("fine");
                        int borrowBook=rs.getInt("borrowed_books");
                        Timestamp time=rs.getTimestamp("last_login_time");
                        user.setUserValues(fine, borrowBook, time);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching user: " + e.getMessage());
        }
    }
}
