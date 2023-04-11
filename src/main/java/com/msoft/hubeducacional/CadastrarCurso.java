/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.msoft.hubeducacional;

import com.msoft.hubeducacional.controller.CursoDao;
import com.msoft.hubeducacional.model.Curso;
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
@WebServlet(name = "hubeducacional", urlPatterns = {"/Curso"})
public class CadastrarCurso extends HttpServlet {

    private final CursoDao repository = new CursoDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

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
        String action = request.getParameter("action");
        String title = request.getParameter("title");

        Curso curso = new Curso();
        curso.setCur_descricao(title);

        if (action == null) {
            action = "read";
        }
        

        switch (action) {
            case "create":
                repository.cadastrarCurso(curso);
                response.sendRedirect("Curso");
                break;
            case "read":
                ArrayList<Curso> listaDeCursos = (ArrayList<Curso>) repository.procurarTodosCursos();
                request.setAttribute("listaDeCursos", listaDeCursos);
                RequestDispatcher rd = request.getRequestDispatcher("cad_curso.jsp");
                rd.forward(request, response);
                break;
            case "update": {
            try {
                updateCurso(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(CadastrarCurso.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            break;

            default:
                break;
        }

    }
    
    private void updateCurso(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");

        Curso curso = new Curso(id, title);
        repository.alterarCurso(curso);
        response.sendRedirect("Curso");
    }
    

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *
     * @Override protected void doPost(HttpServletRequest request,
     * HttpServletResponse response) throws ServletException, IOException {
     * System.out.println("POST - CADASTRAR CURSO");
     * request.setCharacterEncoding("UTF-8");
     *
     * Curso u = new Curso();
     * u.setCur_descricao(request.getParameter("cur_descricao")); String page =
     * "cadastrarCurso.jsp";
     *
     * CursoDao dao = new CursoDao(); if(dao.cadastrarCurso(u)){ page = "Curso";
     * response.sendRedirect(page); }else { request.setAttribute("erro", "Curso
     * Não Cadastrado"); }
     *
     * }
     */
}
