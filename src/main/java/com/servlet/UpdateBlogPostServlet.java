package com.servlet;

import com.dao.BlogDAO;
import com.entity.Blog;
import com.entity.File;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/updateBlog")
@MultipartConfig
public class UpdateBlogPostServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        System.out.println("idddd = " + id);

        String title = req.getParameter("title");
        String content = req.getParameter("content");

        Part filePart = req.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        InputStream fileContent = filePart.getInputStream();
        byte[] fileData = new byte[fileContent.available()];
        fileContent.read(fileData);

        File file = new File();
        file.setFilename(fileName);
        file.setFiledata(fileData);

        BlogDAO blogDAO = new BlogDAO();

        Blog blog = new Blog(id, title, content, file);
        boolean res = blogDAO.updateBlog(blog);

        if (res) {
            resp.sendRedirect("adminDashboard.jsp");
        } else {
            System.out.println("Error posting the blog in db");
        }

    }
}
