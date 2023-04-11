/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.msoft.hubeducacional;

import com.msoft.hubeducacional.controller.PessoaDao;
import com.msoft.hubeducacional.controller.TurmaDao;
import com.msoft.hubeducacional.model.Pessoa;
import com.msoft.hubeducacional.model.Turma;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
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
@WebServlet(name = "ExcluirPessoa", urlPatterns = {"/Matricula"})
public class ExcluirPessoa extends HttpServlet {

    private final TurmaDao repository = new TurmaDao();
    private final PessoaDao p = new PessoaDao();

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

        try {
            sTurma(request, response);
            sPessoa(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ExcluirPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void sPessoa(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        String nome = request.getParameter("Nome");
        String ra = request.getParameter("RA");
        String dtnasc = request.getParameter("dtnasc");
        String cpf = request.getParameter("cpf");
        String sexo = request.getParameter("sexo");
        String telefone1 = request.getParameter("telefone1");
        String email = request.getParameter("email");
        String cep = request.getParameter("cep");
        String endereco = request.getParameter("rua");
        String numero = request.getParameter("numero");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String estado = request.getParameter("estado");

        Pessoa pessoa = new Pessoa();
        pessoa.setPes_nome(nome);
        pessoa.setPes_Ra(ra);
        pessoa.setPes_datanascimento(dtnasc);
        pessoa.setPes_cpf(cpf);
        pessoa.setPes_sexo(sexo);
        pessoa.setPes_telefone(telefone1);
        pessoa.setPes_email(email);
        pessoa.setPes_cep(cep);
        pessoa.setPes_endereco(endereco);
        pessoa.setPes_numero(numero);
        pessoa.setPes_bairro(bairro);
        pessoa.setPes_cidade(cidade);
        pessoa.setPes_estado(estado);

        ArrayList<Pessoa> listaDePessoa = (ArrayList<Pessoa>) p.procurarTodosPessoas();
        request.setAttribute("listaDePessoa", listaDePessoa);
        RequestDispatcher rd = request.getRequestDispatcher("cad_matricula.jsp");
        rd.forward(request, response);
    }

    private void sTurma(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {

        String CodTurma = request.getParameter("Codigo");
        String Serie = request.getParameter("Serie");
        String Turma = request.getParameter("Turma");

        Turma turma = new Turma();
        turma.setTurm_codturma(CodTurma);
        turma.setTurm_serie(Serie);
        turma.setTurm_turma(Turma);

        ArrayList<Turma> listaDeTurma = (ArrayList<Turma>) repository.procurarTodasTurmas();
        request.setAttribute("listaDeTurma", listaDeTurma);
        RequestDispatcher rd = request.getRequestDispatcher("cad_matricula.jsp");
        rd.forward(request, response);

    }

}
