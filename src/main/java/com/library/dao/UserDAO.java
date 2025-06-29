package com.library.dao;

import com.library.models.User;
import com.library.utils.DatabaseConnection;

import java.sql.*;
import java.time.Instant;

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

    public void updateFine(long time, String userId){
        String sql = """
                UPDATE users
                SET fine=fine - ?,
                    last_login_time= ?
                WHERE id=?;
                """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            Timestamp currentTime=Timestamp.from(Instant.now());
            stmt.setInt(1, (int)time);
            stmt.setTimestamp(2, currentTime);
            stmt.setString(3, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error updating fine and time: " + e.getMessage());
        }
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
            int rowsAffected = stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error updating borrowed book: " + e.getMessage());
        }
        return false;
    }
}
