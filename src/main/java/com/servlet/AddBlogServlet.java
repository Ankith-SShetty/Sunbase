package com.servlet;

import com.dao.BlogDAO;
import com.entity.Blog;
import com.entity.File;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/AddBlogServlet")
@MultipartConfig
public class AddBlogServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("blogTitle");
        String content = req.getParameter("blogContent");

        Part filePart = req.getPart("blogFile");
        String fileName = filePart.getSubmittedFileName();
        InputStream fileContent = filePart.getInputStream();
        byte[] fileData = new byte[fileContent.available()];
        fileContent.read(fileData);

        File file = new File();
        file.setFilename(fileName);
        file.setFiledata(fileData);

        BlogDAO blogDAO = new BlogDAO();

        Cookie[] cookies = req.getCookies();

        String currentUserEmail = "";

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("email")) {
                currentUserEmail = cookie.getValue();
                break;
            }
        }

        int currentUserId = blogDAO.getIdByEmail(currentUserEmail);


        Blog blog = new Blog(title, content, file, currentUserId);
        boolean res = blogDAO.savePostToDatabase(blog);

        if (res) {
            resp.sendRedirect("adminDashboard.jsp");
        } else {
            System.out.println("Error posting the blog in db");
        }
    }
}
