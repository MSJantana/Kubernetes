/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.msoft.hubeducacional.controller;

import com.msoft.hubeducacional.model.Curso;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcio
 */
public class CursoDao {

    private final String jdbcURL = "jdbc:mysql://10.12.2.182:3306/Educa_Hub?useSSL=false";
    private final String jdbcUsername = "marcio";
    private final String jdbcPassword = "passpass";

    private static final String INSERT_CURSOS_SQL = "insert into curso (cur_descricao)" + "values (?)";
    private static final String SELECT_CURSOS_BY_ID = "select cur_idCurso,cur_descricao from curso where cur_idCurso = ?";
    private static final String SELECT_ALL_CURSOS_SQL = "select * from curso order by cur_idCurso";
    private static final String DELETE_CURSO_SQL = "delete from curso where cur_idCurso= ?";
    private static final String UPDATE_CURSO_SQL = "update curso set cur_descricao = ? where cur_idCurso = ?";

    public CursoDao() {
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

    public boolean cadastrarCurso(Curso curso) {
        System.out.println(INSERT_CURSOS_SQL);

        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(INSERT_CURSOS_SQL);
            ps.setString(1, curso.getCur_descricao());
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

    public boolean alterarCurso(Curso curso) throws SQLException {
        System.out.println(UPDATE_CURSO_SQL);
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(UPDATE_CURSO_SQL);
            ps.setInt(2, curso.getCur_idCurso());
            ps.setString(1, curso.getCur_descricao());
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

    public boolean deletarCurso(int cur_idCurso) throws SQLException {

        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(DELETE_CURSO_SQL);
            ps.setInt(1, cur_idCurso);
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

    public Curso selecionarCurso(int cur_idCurso) {
        Curso curso = null;
        // Step 1: Establishing a Connection
        try ( Connection connection = getConnection(); // Step 2:Create a statement using connection object
                  PreparedStatement ps = connection.prepareStatement(SELECT_CURSOS_BY_ID);) {
            ps.setInt(1, cur_idCurso);
            System.out.println(ps);
            // Step 3: Execute the query or update query
            ResultSet rs = ps.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {

                String cur_descricao = rs.getString("cur_descricao");
                curso = new Curso(cur_idCurso, cur_descricao);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return curso;
    }

    public List<Curso> procurarTodosCursos() {
        System.out.println(SELECT_ALL_CURSOS_SQL);
        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Curso> curso = new ArrayList<>();
        // Step 1: Establishing a Connection
        try ( Connection connection = getConnection(); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CURSOS_SQL);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int cur_idCurso = rs.getInt("cur_idCurso");
                String cur_descricao = rs.getString("cur_descricao");

                curso.add(new Curso(cur_idCurso, cur_descricao));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return curso;
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
