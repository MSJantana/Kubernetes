/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.msoft.hubeducacional;

import com.msoft.hubeducacional.controller.CursoDao;
import com.msoft.hubeducacional.controller.FuncaoDao;
import com.msoft.hubeducacional.controller.MatriculaDao;
import com.msoft.hubeducacional.controller.PessoaDao;
import com.msoft.hubeducacional.controller.ProfessorDao;
import com.msoft.hubeducacional.controller.TurmaDao;
import com.msoft.hubeducacional.controller.UnidadeDao;
import com.msoft.hubeducacional.model.Curso;
import com.msoft.hubeducacional.model.Funcao;
import com.msoft.hubeducacional.model.Matricula;
import com.msoft.hubeducacional.model.Pessoa;
import com.msoft.hubeducacional.model.Professor;
import com.msoft.hubeducacional.model.Turma;
import com.msoft.hubeducacional.model.Unidade;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MatriculaServlet
 */
@WebServlet(name = "MatriculaServlet", urlPatterns = {"/Matriculas"})
public class MatriculaServlet extends HttpServlet {

    private final MatriculaDao dao;
    private final TurmaDao turmaDao = new TurmaDao();
    private final PessoaDao pessoaDao = new PessoaDao();
    private final CursoDao cursoDao = new CursoDao();
    private final FuncaoDao funcaoDao = new FuncaoDao();
    private final ProfessorDao professorDao = new ProfessorDao();
    private final UnidadeDao unidadeDao = new UnidadeDao();

    public MatriculaServlet() {
        super();
        dao = new MatriculaDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "processMatricula";
        }
       

        switch (action) {
            case "processMatricula":
                processaComboBox(request);
                request.getRequestDispatcher("cad_matricula.jsp").forward(request, response);
                break;
            case "read":
              try {
                List<Matricula> matriculas = dao.getAllMatriculas();
                request.setAttribute("matriculas", matriculas);
                request.getRequestDispatcher("cad_matricula.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new ServletException("Erro ao buscar matrículas: " + e.getMessage());
            }
            break;
            case "eliminarMatricula": {
                try {
                    eliminarMatricula(request, response);                   
                } catch (SQLException ex) {
                    Logger.getLogger(MatriculaServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            default:
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
        /*try {
            
            Matricula matricula = new Matricula();
            matricula.getTurma().setTurm_idturma(Integer.parseInt(request.getParameter("turma")));
            matricula.getPessoa().setPes_IdPessoa(Integer.parseInt(request.getParameter("pessoa")));
            matricula.getCurso().setCur_idCurso(Integer.parseInt(request.getParameter("curso")));
            matricula.getFuncao().setFunc_idFuncao(Integer.parseInt(request.getParameter("funcao")));
            if (request.getParameter("professor") != null) {
                matricula.getProfessor().setProf_idprofessor(Integer.parseInt(request.getParameter("professor")));
            }
            matricula.getUnidade().setUni_idUnidade(Integer.parseInt(request.getParameter("unidade")));
            dao.insertMatricula(matricula);
            response.sendRedirect("Matricula");
        } catch (SQLException e) {
            throw new ServletException("Erro ao inserir matrícula: " + e.getMessage());
        }*/
    }

    private void processaComboBox(HttpServletRequest request) throws ServletException {
        List<Turma> turmas = turmaDao.procurarTodasTurmas();
        request.setAttribute("listaDeTurma", turmas);
        List<Pessoa> pessoas = pessoaDao.procurarTodosPessoas();
        request.setAttribute("listaDePessoa", pessoas);
        List<Curso> cursos = cursoDao.procurarTodosCursos();
        request.setAttribute("listaDeCursos", cursos);
        List<Funcao> funcoes = funcaoDao.procurarTodosCursos();
        request.setAttribute("listaDeFuncao", funcoes);
        List<Professor> professores = professorDao.procurarTodosProfessores();
        request.setAttribute("listaDeProfessor", professores);
        List<Unidade> unidades = unidadeDao.procurarTodosUnidade();
        request.setAttribute("listaDeUnidade", unidades);
    }

    private void eliminarMatricula(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        Matricula p = new Matricula();
        System.out.println("POST - Excluir Matricula");

        if (request.getParameter("cod") != null) {
            p.setMat_idMatricula(Integer.parseInt(request.getParameter("cod")));
            dao.deletarMatricula(p);
            response.sendRedirect("Matricula");

        } else {
            throw new ServletException("Erro ao Excluir matrícula: " + p);

        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para gerenciamento de matrículas.";
    }

}
