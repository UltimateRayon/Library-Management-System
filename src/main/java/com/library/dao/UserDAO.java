package com.library.dao;

import com.library.models.User;
import com.library.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                        rs.getString("password")
                );
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching user: " + e.getMessage());
        }
        return null;
    }

    public boolean saveUser(User user) {
        String sql = "INSERT INTO users (id, name, phone, password) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getPhone());
            stmt.setString(4, user.getPassword());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error saving user: " + e.getMessage());
            return false;
        }
    }
}
