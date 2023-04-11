/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.msoft.hubeducacional.controller;

import com.msoft.hubeducacional.model.Pessoa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcio
 */
public class PessoaDao {

    private final String jdbcURL = "jdbc:mysql://10.12.2.182:3306/Educa_Hub?useSSL=false";
    private final String jdbcUsername = "marcio";
    private final String jdbcPassword = "passpass";

    private static final String INSERT_PESSOA_SQL = "insert into Pessoa (pes_Ra,pes_nome,pes_cpf,pes_datanascimento,pes_sexo,pes_telefone,pes_email,pes_cep,pes_endereco,pes_numero,pes_bairro,pes_estado,pes_cidade)"
            + "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_PESSOA_SQL = "update Pessoa set pes_Ra = ?,pes_nome = ?,pes_cpf = ?, pes_datanascimento = ?,pes_sexo = ?,pes_telefone = ? ,pes_email = ? ,pes_cep = ? ,pes_endereco = ? ,pes_numero = ? , pes_bairro = ? ,pes_estado = ? ,pes_cidade = ? where pes_IdPessoa = ?";
    private static final String DELETE_PESSOA_SQL = "delete from Pessoa where pes_IdPessoa= ?";
    private static final String SELECT_ALL_PESSOA_SQL = "select * from Pessoa order by pes_IdPessoa";

    public PessoaDao() {
    }

    protected Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return con;
    }

    public boolean cadastrarPessoa(Pessoa pessoa) {
        System.out.println(INSERT_PESSOA_SQL);

        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(INSERT_PESSOA_SQL);
            ps.setString(1, pessoa.getPes_Ra());
            ps.setString(2, pessoa.getPes_nome());
            ps.setString(3, pessoa.getPes_cpf());
            ps.setString(4, pessoa.getPes_datanascimento());
            ps.setString(5, pessoa.getPes_sexo());
            ps.setString(6, pessoa.getPes_telefone());
            ps.setString(7, pessoa.getPes_email());
            ps.setString(8, pessoa.getPes_cep());
            ps.setString(9, pessoa.getPes_endereco());
            ps.setString(10, pessoa.getPes_numero());
            ps.setString(11, pessoa.getPes_bairro());
            ps.setString(12, pessoa.getPes_estado());
            ps.setString(13, pessoa.getPes_cidade());
            System.out.println(ps);
            ps.executeUpdate();
            ps.close();
            con.close();
            return true;
        } catch (SQLException e) {
            printSQLException(e);
            return false;
        }
    }

    public boolean alterarPessoa(Pessoa pessoa) throws SQLException {
        System.out.println(UPDATE_PESSOA_SQL);
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(UPDATE_PESSOA_SQL);
           
            ps.setString(1, pessoa.getPes_Ra());
            ps.setString(2, pessoa.getPes_nome());
            ps.setString(3, pessoa.getPes_cpf());
            ps.setString(4, pessoa.getPes_datanascimento());
            ps.setString(5, pessoa.getPes_sexo());
            ps.setString(6, pessoa.getPes_telefone());
            ps.setString(7, pessoa.getPes_email());
            ps.setString(8, pessoa.getPes_cep());
            ps.setString(9, pessoa.getPes_endereco());
            ps.setString(10, pessoa.getPes_numero());
            ps.setString(11, pessoa.getPes_bairro());
            ps.setString(12, pessoa.getPes_estado());
            ps.setString(13, pessoa.getPes_cidade());
            ps.setInt(14, pessoa.getPes_IdPessoa());
            System.out.println(ps);
            ps.executeUpdate();
            ps.close();
            con.close();
            return true;
        } catch (SQLException e) {
            printSQLException(e);
            return false;
        }

    }

    public void deletarPessoa(Pessoa pessoa) throws SQLException {

        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(DELETE_PESSOA_SQL);
            ps.setInt(1, pessoa.getPes_IdPessoa());
            System.out.println(ps);
            ps.executeUpdate();
            ps.close();
            con.close();
            
        } catch (SQLException e) {
            printSQLException(e);
           
        }

    }

    public List<Pessoa> procurarTodosPessoas() {
        System.out.println(SELECT_ALL_PESSOA_SQL);
        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Pessoa> listapessoas = new ArrayList<>();
        // Step 1: Establishing a Connection
        try ( Connection connection = getConnection(); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PESSOA_SQL);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();

                pessoa.setPes_IdPessoa(rs.getInt("pes_IdPessoa"));
                pessoa.setPes_Ra(rs.getString("pes_Ra"));
                pessoa.setPes_nome(rs.getString("pes_nome"));
                pessoa.setPes_cpf(rs.getString("pes_cpf"));
                pessoa.setPes_datanascimento(rs.getString("pes_datanascimento"));
                pessoa.setPes_sexo(rs.getString("pes_sexo"));
                pessoa.setPes_telefone(rs.getString("pes_telefone"));
                pessoa.setPes_email(rs.getString("pes_email"));
                pessoa.setPes_cep(rs.getString("pes_cep"));
                pessoa.setPes_endereco(rs.getString("pes_endereco"));
                pessoa.setPes_numero(rs.getString("pes_numero"));
                pessoa.setPes_bairro(rs.getString("pes_bairro"));
                pessoa.setPes_estado(rs.getString("pes_estado"));
                pessoa.setPes_cidade(rs.getString("pes_cidade"));

                listapessoas.add(pessoa);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return listapessoas; 
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }

        }
    }

}
