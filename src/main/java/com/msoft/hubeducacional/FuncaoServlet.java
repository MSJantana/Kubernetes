/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.msoft.hubeducacional;

import com.msoft.hubeducacional.controller.FuncaoDao;
import com.msoft.hubeducacional.model.Funcao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "FuncaoServlet", urlPatterns = {"/Funcao"})
public class FuncaoServlet extends HttpServlet {

   private final FuncaoDao repository = new FuncaoDao();
   
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
        doGet(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String title = request.getParameter("title");

        Funcao funcao = new Funcao();
        funcao.setFunc_nome(title);

        if (action == null) {
            action = "read";
        }
        

        switch (action) {
            case "create":
                repository.cadastrarFuncao(funcao);
                response.sendRedirect("Funcao");
                break;
            case "read":
                ArrayList<Funcao> listaDeFuncao = (ArrayList<Funcao>) repository.procurarTodosCursos();
                request.setAttribute("listaDeFuncao", listaDeFuncao);
                RequestDispatcher rd = request.getRequestDispatcher("cad_funcao.jsp");
                rd.forward(request, response);
                break;
            case "update": {
            try {
                updateFuncao(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(FuncaoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            break;

            default:
                break;
        }

    }
    
    private void updateFuncao(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");

        Funcao funcao = new Funcao(id, title);
        repository.alterarFuncao(funcao);
        response.sendRedirect("Funcao");
    }
    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
