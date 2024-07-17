package com.dao;

import com.connection.DbConnection;
import com.entity.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private boolean result = false;

    public boolean register(User user) {
        String query1 = "INSERT INTO " + user.getRole() + "(name, email, password) VALUES (?, ?, ?)";
        String query2 = "SELECT * FROM admin WHERE email = ?";
        String query3 = "SELECT * FROM user WHERE email = ?";
        try(
                Connection con = DbConnection.getConnection();
                PreparedStatement pstmt1 = con.prepareStatement(query1);
                PreparedStatement pstmt2 = con.prepareStatement(query2);
                PreparedStatement pstmt3 = con.prepareStatement(query3);
        ) {

            pstmt1.setString(1, user.getName());
            pstmt1.setString(2, user.getEmail());
            pstmt1.setString(3, user.getPassword());

            pstmt2.setString(1, user.getEmail());
            ResultSet rs1 = pstmt2.executeQuery();

            pstmt3.setString(1, user.getEmail());
            ResultSet rs2 = pstmt3.executeQuery();
            if (rs1.next() || rs2.next()) {
                return false;
            }

            int res = pstmt1.executeUpdate();
            if(res == 1) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public User authenticateUser(String email, String password) {
        String sql1 = "SELECT * FROM admin WHERE email = ?";
        String sql2 = "SELECT * FROM user WHERE email = ?";
        try (
                Connection con = DbConnection.getConnection();
                PreparedStatement stmt1 = con.prepareStatement(sql1);
                PreparedStatement stmt2 = con.prepareStatement(sql2)
        ) {
            stmt1.setString(1, email);
            stmt2.setString(1, email);
            ResultSet rs1 = stmt1.executeQuery();
            ResultSet rs2 = stmt2.executeQuery();
            if (rs1.next()) {
                String hashedPassword = rs1.getString("password");

                if (BCrypt.checkpw(password, hashedPassword)) {
                    return new User(email, rs1.getString("name"), "admin");
                }
            } else if (rs2.next()) {
                String hashedPassword = rs2.getString("password");

                if (BCrypt.checkpw(password, hashedPassword)) {
                    return new User(email, rs2.getString("name"), "user");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
