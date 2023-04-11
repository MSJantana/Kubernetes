/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.msoft.hubeducacional;

import com.msoft.hubeducacional.controller.PessoaDao;
import com.msoft.hubeducacional.model.Pessoa;
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
@WebServlet(name = "PessoaServlet", urlPatterns = {"/Pessoa"})
public class PessoaServlet extends HttpServlet {

    private final PessoaDao repository = new PessoaDao();

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

        if (action == null) {
            action = "read";
        }

        switch (action) {
            case "create":
                repository.cadastrarPessoa(pessoa);
                response.sendRedirect("Pessoa");
                break;
            case "read":
                ArrayList<Pessoa> listaDePessoa = (ArrayList<Pessoa>) repository.procurarTodosPessoas();
                request.setAttribute("listaDePessoa", listaDePessoa);
                RequestDispatcher rd = request.getRequestDispatcher("cad_aluno.jsp");
                rd.forward(request, response);
                break;
            case "update": {
                try {
                    updatePessoa(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(PessoaServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case "eliminarPessoa":
            {
                try {
                    eliminarPessoa(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(PessoaServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                break;

            default:
                break;
        }

    }

    private void updatePessoa(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
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

        Pessoa pessoa = new Pessoa(id, ra, nome, cpf, dtnasc, sexo, telefone1, email, cep, endereco, numero, bairro, cidade, estado);
        repository.alterarPessoa(pessoa);
        response.sendRedirect("Pessoa");
    }

    private void eliminarPessoa(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        PessoaDao dao = new PessoaDao();
        Pessoa p = new Pessoa();
        System.out.println("POST - Excluir Pessoa");

        if (request.getParameter("cod") != null) {
            p.setPes_IdPessoa(Integer.parseInt(request.getParameter("cod")));
            try {
                dao.deletarPessoa(p);
                response.sendRedirect("Pessoa");

            } catch (IOException e) {
                request.setAttribute("msje", "Não foi possivel acessar a base de dados!!!!" + e.getMessage());
            }

        } else {
            request.setAttribute("msje", "Não foi possível excluir " + p);

        }
    }

}
