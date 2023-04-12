/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.msoft.hubeducacional;

import com.msoft.hubeducacional.controller.TurmaDao;
import com.msoft.hubeducacional.model.Turma;
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
 * @author Marcio
 */
@WebServlet(name = "TurmaServlet", urlPatterns = {"/Turma"})
public class TurmaServlet extends HttpServlet {

    private final TurmaDao repository = new TurmaDao();

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
        String CodTurma = request.getParameter("Codigo");
        String Serie = request.getParameter("Serie");
        String Turma = request.getParameter("Turma");

        Turma turma = new Turma();
        turma.setTurm_codturma(CodTurma);
        turma.setTurm_serie(Serie);
        turma.setTurm_turma(Turma);

        if (action == null) {
            action = "read";
        }

        switch (action) {
            case "create":
                repository.cadastrarTruma(turma);
                response.sendRedirect("Turma");
                break;
            case "read":
                ArrayList<Turma> listaDeTurma = (ArrayList<Turma>) repository.procurarTodasTurmas();
                request.setAttribute("listaDeTurma", listaDeTurma);
                RequestDispatcher rd = request.getRequestDispatcher("cad_turma.jsp");
                rd.forward(request, response);
                break;
            case "update": {
                try {
                    updateTurma(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(TurmaServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case "eliminarTurma": {
                try {
                    eliminarTurma(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(TurmaServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

            default:
                break;
        }

    }

    private void updateTurma(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String CodTurma = request.getParameter("Codigo");
        String Serie = request.getParameter("Serie");
        String Turma = request.getParameter("Turma");

        Turma turma = new Turma(id, CodTurma, Serie, Turma);
        repository.alterarTurma(turma);
        response.sendRedirect("Turma");
    }

    private void eliminarTurma(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        TurmaDao dao = new TurmaDao();
        Turma p = new Turma();
        System.out.println("POST - Excluir Turma");

        if (request.getParameter("cod") != null) {
            p.setTurm_idturma(Integer.parseInt(request.getParameter("cod")));
            try {
                dao.deletarturma(p);
                response.sendRedirect("Turma");

            } catch (IOException e) {
                throw new ServletException("Erro ao inserir a Turma: " + e.getMessage());
            }

        } else {
            throw new ServletException("Erro ao inserir matrícula: " + p);

        }
    }

}
