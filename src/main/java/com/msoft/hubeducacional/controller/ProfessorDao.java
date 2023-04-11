/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.msoft.hubeducacional.controller;

import com.msoft.hubeducacional.model.Professor;
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
public class ProfessorDao {

    private final String jdbcURL = "jdbc:mysql://10.12.2.182:3306/Educa_Hub?useSSL=false";
    private final String jdbcUsername = "marcio";
    private final String jdbcPassword = "passpass";

    private static final String INSERT_PESSOA_SQL = "insert into Professores (prof_nome,prof_cpf,prof_titulacao)" + "values (?,?,?)";
    private static final String UPDATE_PESSOA_SQL = "update Professores set prof_nome = ?,prof_cpf = ?,prof_titulacao = ? where prof_idprofessor = ?";
    private static final String DELETE_PESSOA_SQL = "delete from Professores where prof_idprofessor= ?";
    private static final String SELECT_ALL_PESSOA_SQL = "select * from Professores order by prof_idprofessor";

    public ProfessorDao() {
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

    public boolean cadastrarProfessor(Professor professor) {
        System.out.println(INSERT_PESSOA_SQL);

        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(INSERT_PESSOA_SQL);
            ps.setString(1, professor.getProf_nome());
            ps.setString(2, professor.getProf_cpf());
            ps.setString(3, professor.getProf_titulacao());

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

    public boolean alterarProfessor(Professor professor) throws SQLException {
        System.out.println(UPDATE_PESSOA_SQL);
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(UPDATE_PESSOA_SQL);
            ps.setInt(4, professor.getProf_idprofessor());
            ps.setString(1, professor.getProf_nome());
            ps.setString(2, professor.getProf_cpf());
            ps.setString(3, professor.getProf_titulacao());

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

    public void deletarProfessor(Professor professor) throws SQLException {

        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(DELETE_PESSOA_SQL);
            ps.setInt(1, professor.getProf_idprofessor());
            System.out.println(ps);
            ps.executeUpdate();
            ps.close();
            con.close();

        } catch (SQLException e) {
            printSQLException(e);

        }

    }

    public List<Professor> procurarTodosProfessores() {
        System.out.println(SELECT_ALL_PESSOA_SQL);
        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Professor> listaprofessor = new ArrayList<>();
        // Step 1: Establishing a Connection
        try ( Connection connection = getConnection(); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PESSOA_SQL);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                Professor professor = new Professor();

                professor.setProf_idprofessor(rs.getInt("prof_idprofessor"));
                professor.setProf_nome(rs.getString("prof_nome"));
                professor.setProf_cpf(rs.getString("prof_cpf"));
                professor.setProf_titulacao(rs.getString("prof_titulacao"));

                listaprofessor.add(professor);

            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return listaprofessor;
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
