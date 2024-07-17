package com.dao;

import com.connection.DbConnection;
import com.entity.Blog;
import com.entity.File;


import javax.servlet.http.Part;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlogDAO {
    public boolean savePostToDatabase(Blog blog) {
        String sql = "INSERT INTO blog_posts(title, content, filename, filedata, createdby) VALUES (?,?,?,?,?)";
        try(Connection con = DbConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
        ){

            File file = blog.getFile();
            String filename = null;
            byte[] filedata = new byte[0];
            if (file != null) {
                filename = file.getFilename();
                filedata = file.getFiledata();
            }

            pstmt.setString(1, blog.getTitle());
            pstmt.setString(2, blog.getContent());
            pstmt.setObject(3, filename);
            pstmt.setBytes(4, filedata);
            pstmt.setInt(5, blog.getCreatorId());

            int i = pstmt.executeUpdate();

            if (i == 1) {
                return true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public int getIdByEmail(String email) {

        String query = "SELECT * FROM admin WHERE email = ?";

        try(Connection con = DbConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);
        ){
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    public List<Blog> getAllBlogs() {
        String sql = "SELECT * FROM blog_posts";
        List<Blog> allBlogs = new ArrayList<>();

        try (Connection con = DbConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
             ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Blob blob = rs.getBlob("filedata");
                int blobLength = (int) blob.length();
                byte[] filedata = blob.getBytes(1, blobLength);

                File file = new File(rs.getString("filename"), filedata);
                Blog blog = new Blog(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        file
                );
                allBlogs.add(blog);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return allBlogs;
    }

    public List<Blog> selectAllBlogsByCreatorId(int creatorId) {
        String sql = "SELECT * FROM blog_posts WHERE createdby = ?";

        List<Blog> blogs = new ArrayList<>();

        try(Connection con = DbConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
        ){
            pstmt.setInt(1, creatorId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Blob blob = rs.getBlob("filedata");
                int blobLength = (int) blob.length();
                byte[] filedata = blob.getBytes(1, blobLength);

                File file = new File(rs.getString("filename"), filedata);
                Blog blog = new Blog(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        file
                );
                blogs.add(blog);
            }
            return blogs;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return blogs;
    }

    public boolean deleteBlog(int id) {
        boolean f = false;
        String sql = "DELETE FROM blog_posts WHERE id=?";
        try(Connection con = DbConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
        ){
            pstmt.setInt(1,id);
            int i = pstmt.executeUpdate();
            if(i == 1) {
                f = true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return f;
    }


    public boolean updateBlog(Blog blog) {
        String sql = "UPDATE blog_posts SET title=?, content=?, filename=?, filedata=? WHERE id = ?";
        boolean f = false;
        try(Connection con = DbConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
        ){
            pstmt.setString(1,blog.getTitle());
            pstmt.setString(2,blog.getContent());
            pstmt.setString(3,blog.getFile().getFilename());
            pstmt.setBytes(4,blog.getFile().getFiledata());
            pstmt.setInt(5,blog.getId());

            int i = pstmt.executeUpdate();
            if(i == 1) {
                f = true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return f;
    }
}

