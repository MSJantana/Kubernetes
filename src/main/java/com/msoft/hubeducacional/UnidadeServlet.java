/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.msoft.hubeducacional;

import com.msoft.hubeducacional.controller.UnidadeDao;
import com.msoft.hubeducacional.model.Unidade;
import java.io.IOException;
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
@WebServlet(name = "UnidadeServlet", urlPatterns = {"/Unidade"})
public class UnidadeServlet extends HttpServlet {

    private final UnidadeDao repository = new UnidadeDao();

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
        String telefone = request.getParameter("telefone");
        String sigla = request.getParameter("sigla");

        Unidade unidade = new Unidade();
        unidade.setUni_nomeUnidade(title);
        unidade.setUni_telefone(telefone);
        unidade.setUni_siglaEscola(sigla);

        if (action == null) {
            action = "read";
        }

        switch (action) {
            case "create":
                repository.cadastrarUnidade(unidade);
                response.sendRedirect("Unidade");
                break;
            case "read":
                ArrayList<Unidade> listaDeUnidade = (ArrayList<Unidade>) repository.procurarTodosUnidade();
                request.setAttribute("listaDeUnidade", listaDeUnidade);
                RequestDispatcher rd = request.getRequestDispatcher("cad_unidade.jsp");
                rd.forward(request, response);
                break;
            case "update": {
                try {
                    updateUnidade(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(UnidadeServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

            default:
                break;
        }

    }

    private void updateUnidade(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String telefone = request.getParameter("telefone");
        String siglaEscola = request.getParameter("sigla");

        Unidade unidade = new Unidade(id, title,telefone,siglaEscola);
        repository.alterarUnidae(unidade);
        response.sendRedirect("Unidade");
    }

}
