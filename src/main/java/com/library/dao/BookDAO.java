package com.library.dao;

import com.library.models.Book;
import com.library.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public boolean bookReduce(String bookName){
        String sql = """
                UPDATE book_collection
                SET
                    total_copies = total_copies - 1,
                    availability = CASE
                        WHEN total_copies - 1 <= 1 THEN false
                        ELSE true
                    END
                    WHERE title = ? AND total_copies > 0;
                """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bookName);
            int rowsAffected = stmt.executeUpdate();
            if(rowsAffected>0){
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error updating book availability: " + e.getMessage());
        }
        return false;
    }
    public boolean bookIncrease(String bookName){
        String sql = """
            UPDATE book_collection
            SET
                total_copies = total_copies + 1,
                availability = true
            WHERE title = ?;
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bookName);
            int rowsAffected = stmt.executeUpdate();
            if(rowsAffected > 0){
                return true;
            }

        } catch (SQLException e) {
            System.err.println("❌ Error increasing book count: " + e.getMessage());
        }
        return false;
    }

    public List<String> userBookList(String userId) {
        List<String> bookList = new ArrayList<>();
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
                bookList.add(rs.getString("title"));
            }
            return bookList;
        } catch (SQLException e) {
            System.err.println("❌ Error in getCheckOutHistory: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getBookList() {
        List<String> bookList = new ArrayList<>();
        String sql = """
                SELECT title, availability FROM book_collection
                ORDER BY title;
                """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String book=rs.getString("title");
                boolean status=rs.getBoolean("availability");
                bookList.add("\n-Book Title: "+book+"\n-Availability: "+((status)?"Available":"Not Available"));
            }
            return bookList;
        } catch (SQLException e) {
            System.err.println("❌ Error in getBookList: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}