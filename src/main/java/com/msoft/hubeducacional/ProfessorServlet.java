/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.msoft.hubeducacional;

import com.msoft.hubeducacional.controller.ProfessorDao;
import com.msoft.hubeducacional.model.Professor;
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
@WebServlet(name = "ProfessorServlet", urlPatterns = {"/Professor"})
public class ProfessorServlet extends HttpServlet {

    private final ProfessorDao repository = new ProfessorDao();

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
        String Nome = request.getParameter("Nome");
        String CPF = request.getParameter("cpf");
        String Titularidade = request.getParameter("titularidade");

        Professor professor = new Professor();
        professor.setProf_nome(Nome);
        professor.setProf_cpf(CPF);
        professor.setProf_titulacao(Titularidade);
        

        if (action == null) {
            action = "read";
        }

        switch (action) {
            case "create":
                repository.cadastrarProfessor(professor);
                response.sendRedirect("Professor");
                break;
            case "read":
                ArrayList<Professor> listaDeProfessor = (ArrayList<Professor>) repository.procurarTodosProfessores();
                request.setAttribute("listaDeProfessor", listaDeProfessor);
                RequestDispatcher rd = request.getRequestDispatcher("cad_professores.jsp");
                rd.forward(request, response);
                break;
            case "update": {
                try {
                    updateProfessor(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(PessoaServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case "eliminarProfessor":
            {
                try {
                    eliminarProfessor(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(PessoaServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                break;

            default:
                break;
        }

    }

    private void updateProfessor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String Nome = request.getParameter("Nome");
        String CPF = request.getParameter("cpf");
        String Titularidade = request.getParameter("titularidade");
        

        Professor professor = new Professor(id, Nome, CPF,Titularidade);
        repository.alterarProfessor(professor);
        response.sendRedirect("Professor");
    }

    private void eliminarProfessor(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        ProfessorDao dao = new ProfessorDao();
        Professor p = new Professor();
        System.out.println("POST - Excluir Professor");

        if (request.getParameter("cod") != null) {
            p.setProf_idprofessor(Integer.parseInt(request.getParameter("cod")));
            try {
                dao.deletarProfessor(p);
                response.sendRedirect("Professor");

            } catch (IOException e) {
                request.setAttribute("msje", "Não foi possivel acessar a base de dados!!!!" + e.getMessage());
            }

        } else {
            request.setAttribute("msje", "Não foi possível excluir " + p);

        }
    }

}
