package com.library.dao;

import com.library.models.Book;
import com.library.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDAO {

    public boolean bookAvailability(String name) {
        String sql = "SELECT * FROM book_collection WHERE title = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return rs.getBoolean("availability");
            }

        } catch (SQLException e) {
            System.err.println("❌ Error fetching book availability: " + e.getMessage());
        }
        return false;
    }

    public Book findBook(String name) {
        String sql = "SELECT * FROM book_collection WHERE title = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new Book(
                        rs.getString("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("genre"),
                        rs.getBoolean("availability")
                );
            }

        } catch (SQLException e) {
            System.err.println("❌ Error fetching books: " + e.getMessage());
        }
        return null;
    }
}