/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.msoft.hubeducacional;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author claudevandro
 */
@WebServlet(name= "register", urlPatterns = {"/register"})
public class RegistrationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uname = request.getParameter("name");
        String uemail = request.getParameter("email");
        String upwd = request.getParameter("pass");
        String umobile = request.getParameter("contact");
        RequestDispatcher rs = null;
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://10.12.2.182:3306/Educa_Hub?useSSL=false", "marcio", "passpass");

            PreparedStatement pstmt = con.prepareStatement("insert into user_login(user_login,user_pass,user_email,user_lastname) values(?,?,?,?)"); //sql insert query
            pstmt.setString(1, uname);
            pstmt.setString(2, upwd);
            pstmt.setString(3, uemail);
            pstmt.setString(4, umobile);

            int rowCount = pstmt.executeUpdate();
            rs = request.getRequestDispatcher("register.jsp");
            if (rowCount > 0) {

                request.setAttribute("status", "Sucesso");

            } else {

                request.setAttribute("status", "Failed");

            }
            rs.forward(request, response);

        } catch (IOException | ClassNotFoundException | SQLException | ServletException ex) {

        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
}
