package com.servlet;

import java.io.IOException;
import java.net.HttpCookie;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.connection.DbConnection;
import com.dao.UserDAO;
import com.entity.User;
import org.mindrot.jbcrypt.BCrypt;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = hashPassword(req.getParameter("password"));
        String role = req.getParameter("role");

        User user = new User(name, email, password, role);
        UserDAO userDAO = new UserDAO();
        boolean res = userDAO.register(user);

        Cookie cookie = new Cookie("email", email);
        cookie.setMaxAge(24 * 60 * 60);
        resp.addCookie(cookie);

        RequestDispatcher rd1 = req.getRequestDispatcher("adminDashboard.jsp");
        RequestDispatcher rd2 = req.getRequestDispatcher("viewBlogs.jsp");

        if (res) {
            if (role.equals("admin")) {
                rd1.forward(req, resp);
            } else {
                rd2.forward(req, resp);
            }
        } else {
            session.setAttribute("RegisterErrMsg","Registration failed. User may already exist or try again :|");
            resp.sendRedirect("register.jsp");
        }
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
