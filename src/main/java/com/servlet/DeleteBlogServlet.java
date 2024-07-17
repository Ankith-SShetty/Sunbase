package com.servlet;

import com.dao.BlogDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteBlogServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        BlogDAO dao = new BlogDAO();
        boolean f = dao.deleteBlog(id);
        HttpSession session = req.getSession();
        if(f) {
            resp.sendRedirect("adminDashboard.jsp");
        }else {
            session.setAttribute("errMsg","Deletion failed");
            resp.sendRedirect("adminDashboard.jsp");
        }
    }

}
