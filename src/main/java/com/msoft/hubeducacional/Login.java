/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.msoft.hubeducacional;

import java.io.IOException;
/*import java.io.PrintWriter;*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author claudevandro
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class Login extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Connection con = null;
        HttpSession session = request.getSession();        
        RequestDispatcher dispatcher = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://10.12.2.182:3306/Educa_Hub?useSSL=false", "marcio", "passpass");
            PreparedStatement pstmt = con.prepareStatement("select * from user_login where user_login = ? and user_pass = ?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                session.setAttribute("username", rs.getString("user_login"));
                dispatcher = request.getRequestDispatcher("paginaInicial.jsp");
                
            } else {
                request.setAttribute("status", "Failed");
                dispatcher = request.getRequestDispatcher("index.jsp");
                
            }
           dispatcher.forward(request, response);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
