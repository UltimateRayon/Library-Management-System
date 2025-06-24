package com.library.services;

public class TransactionService {


    //TRANSACTION
//    public Transaction getTransaction(String userID, String bookID) {
//        String query = "SELECT transaction_id, issue_date, return_date, fine FROM transactions WHERE user_id = ? AND book_id = ? ORDER BY issue_date DESC LIMIT 1";
//
//       // DatabaseConnection DBConnection;
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//
//            stmt.setString(1, userID);
//            stmt.setString(2, bookID);
//
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                int transactionId = rs.getInt("transaction_id");
//                Date issueDate = rs.getDate("issue_date");
//                Date returnDate = rs.getDate("return_date");
//                int fine = rs.getInt("fine");
//
//                return new Transaction(transactionId, userID, bookID, issueDate, returnDate, fine);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//
//
//
//
//    //CHECKOUT
//    public boolean checkoutBook(String userID, String bookID){
//
//        String query = "INSERT INTO transactions (user_id, book_id, issue_date) VALUES (?, ?, CURRENT_DATE)";
//
//     //   DatabaseConnection DBConnection;
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//
//            stmt.setString(1, userID);
//            stmt.setString(2, bookID);
//
//            int result = stmt.executeUpdate();
//            return result > 0;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//
//    //CHECKIN
//    public boolean checkinBook(String userID, String bookID){
//
//        String selectQuery = "SELECT transaction_id, issue_date FROM transactions WHERE user_id = ? AND book_id = ? AND return_date IS NULL";
//
//       // DatabaseConnection DBConnection;
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement selectStmt = conn.prepareStatement(selectQuery)) {
//
//            //setString not Int
//            selectStmt.setString(1, userID);
//            selectStmt.setString(2, bookID);
//            ResultSet rs = selectStmt.executeQuery();
//
//            if (rs.next()) {
//                int transactionId = rs.getInt("transaction_id");
//                Date issueDate = rs.getDate("issue_date");
//                Date returnDate = new Date(System.currentTimeMillis());
//
//                int fine = calculateFine(issueDate, returnDate);
//
//                String updateQuery = "UPDATE transactions SET return_date = ?, fine = ? WHERE transaction_id = ?";
//                try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
//                    updateStmt.setDate(1, returnDate);
//                    updateStmt.setInt(2, fine);
//                    updateStmt.setInt(3, transactionId);
//
//                    int updated = updateStmt.executeUpdate();
//                    return updated > 0;
//                }
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//
//    //FINE
//    private int calculateFine(Date issueDate, Date returnDate) {
//        long diffMillis = returnDate.getTime() - issueDate.getTime();
//        long diffDays = TimeUnit.MILLISECONDS.toDays(diffMillis);
//
//        int allowedPeriod = 14;
//        int overdueDays = (int) (diffDays - allowedPeriod);
//
//        return Math.max(overdueDays, 0); // $1 per overdue day
//    }

}
