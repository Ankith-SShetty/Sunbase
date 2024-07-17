package com.servlet;

import com.connection.DbConnection;
import com.dao.UserDAO;
import com.entity.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpCookie;
import java.sql.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDAO userDAO = new UserDAO();

        User authenticatedUser = userDAO.authenticateUser(email, password);

        Cookie cookie = new Cookie("email", email);
        cookie.setMaxAge(24 * 60 * 60);
        resp.addCookie(cookie);


        if (authenticatedUser == null) {
            session.setAttribute("loginErrMsg","Login failed. User not found <br/> Please register :)");
            resp.sendRedirect("login.jsp");
        } else if (authenticatedUser.getRole().equals("admin")) {
            resp.sendRedirect("adminDashboard.jsp");
        } else if (authenticatedUser.getRole().equals("user")) {
            resp.sendRedirect("viewBlogs.jsp");
        }
    }
}

