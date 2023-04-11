/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.msoft.hubeducacional;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.msoft.hubeducacional.controller.CursoDao;
import com.msoft.hubeducacional.model.Curso;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author claudevandro
 */
@WebServlet(name = "ExcluirCurso", urlPatterns = {"/excluircurso"})
public class ExcluirCurso extends HttpServlet {
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/cad_curso.jsp");
        dispatcher.forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        System.out.println("POST - Excluir PRODUTO");

        Curso u = new Curso();
        u.setCur_idCurso(Integer.parseInt(request.getParameter("cur_idCurso")));
        
        CursoDao dao = new CursoDao();

        try {
            dao.deletarCurso(u.getCur_idCurso());
            
            response.sendRedirect("Curso");
        } catch (SQLException ex) {
            Logger.getLogger(ExcluirCurso.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }

}
