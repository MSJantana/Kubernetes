/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.msoft.hubeducacional.controller;

import com.msoft.hubeducacional.model.Funcao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author claudevandro
 */
public class FuncaoDao {

    private final String jdbcURL = "jdbc:mysql://10.12.2.182:3306/Educa_Hub?useSSL=false";
    private final String jdbcUsername = "marcio";
    private final String jdbcPassword = "passpass";

    private static final String INSERT_FUNCAO_SQL = "insert into Funcao (func_nome)" + "values (?)";
    //private static final String SELECT_FUNCAO_BY_ID = "select func_idFuncao,func_nome from Funcao where func_idFuncao = ?";
    private static final String SELECT_ALL_FUNCAO_SQL = "select * from Funcao order by func_idFuncao";
    private static final String DELETE_FUNCAO_SQL = "delete from Funcao where func_idFuncao= ?";
    private static final String UPDATE_FUNCAO_SQL = "update Funcao set func_nome = ? where func_idFuncao = ?";

    public FuncaoDao() {
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

    public boolean cadastrarFuncao(Funcao funcao) {
        System.out.println(INSERT_FUNCAO_SQL);

        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(INSERT_FUNCAO_SQL);
            ps.setString(1, funcao.getFunc_nome());
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
    
    public boolean alterarFuncao(Funcao funcao) throws SQLException {
        System.out.println(UPDATE_FUNCAO_SQL);
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(UPDATE_FUNCAO_SQL);
            ps.setInt(2, funcao.getFunc_idFuncao());
            ps.setString(1, funcao.getFunc_nome());
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
    
    public boolean deletarFuncao(int func_idFuncao) throws SQLException {

        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(DELETE_FUNCAO_SQL);
            ps.setInt(1, func_idFuncao);
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
 
    public List<Funcao> procurarTodosCursos() {
        System.out.println(SELECT_ALL_FUNCAO_SQL);
        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Funcao> funcao = new ArrayList<>();
        // Step 1: Establishing a Connection
        try ( Connection connection = getConnection(); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FUNCAO_SQL);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int func_idFuncao = rs.getInt("func_idFuncao");
                String func_nome = rs.getString("func_nome");

                funcao.add(new Funcao(func_idFuncao, func_nome));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return funcao;
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
