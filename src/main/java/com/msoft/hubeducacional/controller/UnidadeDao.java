/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.msoft.hubeducacional.controller;


import com.msoft.hubeducacional.model.Unidade;
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
public class UnidadeDao {
    private final String jdbcURL = "jdbc:mysql://10.12.2.182:3306/Educa_Hub?useSSL=false";
    private final String jdbcUsername = "marcio";
    private final String jdbcPassword = "passpass";

    private static final String INSERT_UNIDADE_SQL = "insert into Unidade (uni_nomeUnidade,uni_telefone,uni_siglaEscola)" + "values (?,?,?)";
    private static final String SELECT_UNIDADE_BY_ID = "select uni_idUnidade,uni_nomeUnidade,uni_telefone,uni_siglaEscola from unidade where uni_idUnidade = ?";
    private static final String SELECT_ALL_UNIDADE_SQL = "select * from Unidade order by uni_idUnidade";
    private static final String DELETE_UNIDADE_SQL = "delete from Unidade where uni_idUnidade= ?";
    private static final String UPDATE_UNIDADE_SQL = "update Unidade set uni_nomeUnidade = ?, uni_telefone = ?,uni_siglaEscola = ? where uni_idUnidade = ?";

    public UnidadeDao() {
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
     
     public boolean cadastrarUnidade(Unidade unidade) {
        System.out.println(INSERT_UNIDADE_SQL);

        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(INSERT_UNIDADE_SQL);
            ps.setString(1, unidade.getUni_nomeUnidade());
            ps.setString(2, unidade.getUni_telefone());
            ps.setString(3, unidade.getUni_siglaEscola());
         
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

    public boolean alterarUnidae(Unidade unidade) throws SQLException {
        System.out.println(UPDATE_UNIDADE_SQL);
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(UPDATE_UNIDADE_SQL);
            
            ps.setString(1, unidade.getUni_nomeUnidade());
            ps.setString(2, unidade.getUni_telefone());
            ps.setString(3, unidade.getUni_siglaEscola());
            ps.setInt(4, unidade.getUni_idUnidade());
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

    public boolean deletarUnidade(Integer toDelete) throws SQLException {

        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(DELETE_UNIDADE_SQL);
            ps.setInt(1, toDelete);
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
    
    public Unidade selecionarUnidade(int uni_idUnidade) {
        Unidade unidade = null;
        // Step 1: Establishing a Connection
        try ( Connection connection = getConnection(); // Step 2:Create a statement using connection object
                  PreparedStatement ps = connection.prepareStatement(SELECT_UNIDADE_BY_ID);) {
            ps.setInt(1, uni_idUnidade);
            System.out.println(ps);
            // Step 3: Execute the query or update query
            ResultSet rs = ps.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {

                String uni_nomeUnidade = rs.getString("uni_nomeUnidade");
                String uni_telefone = rs.getString("uni_telefone");
                String uni_siglaEscola = rs.getString("uni_siglaEscola");
                unidade = new Unidade(uni_idUnidade, uni_nomeUnidade,uni_telefone,uni_siglaEscola);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return unidade;
    }


    public List<Unidade> procurarTodosUnidade() {
        System.out.println(SELECT_ALL_UNIDADE_SQL);
        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Unidade> unidade = new ArrayList<>();
        // Step 1: Establishing a Connection
        try ( Connection connection = getConnection(); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_UNIDADE_SQL);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int uni_idUnidade = rs.getInt("uni_idUnidade");
                String uni_nomeUnidade = rs.getString("uni_nomeUnidade");
                String uni_telefone = rs.getString("uni_telefone");
                String uni_siglaEscola = rs.getString("uni_siglaEscola");

                unidade.add(new Unidade (uni_idUnidade,uni_nomeUnidade,uni_telefone,uni_siglaEscola));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return unidade;
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
