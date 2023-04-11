/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.msoft.hubeducacional.controller;

import com.msoft.hubeducacional.model.Turma;
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
public class TurmaDao {
    
    private final String jdbcURL = "jdbc:mysql://10.12.2.182:3306/Educa_Hub?useSSL=false";
    private final String jdbcUsername = "marcio";
    private final String jdbcPassword = "passpass";

    private static final String INSERT_TURMA_SQL = "insert into Turma (turm_codturma,turm_serie,turm_turma)" + "values (?,?,?)";
    private static final String UPDATE_TURMA_SQL = "update Turma set turm_codturma = ?,turm_serie = ?,turm_turma = ? where turm_idturma = ?";
    private static final String DELETE_TURMA_SQL = "delete from Turma where turm_idturma= ?";
    private static final String SELECT_ALL_TURMA_SQL = "select * from Turma order by turm_idturma";
    
    public TurmaDao(){ 
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
    
    
    public boolean cadastrarTruma(Turma turma) {
        System.out.println(INSERT_TURMA_SQL);

        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(INSERT_TURMA_SQL);
            
            ps.setString(1, turma.getTurm_codturma());
            ps.setString(2, turma.getTurm_serie());
            ps.setString(3, turma.getTurm_turma());
            
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
    
    public boolean alterarTurma(Turma turma) throws SQLException {
        System.out.println(UPDATE_TURMA_SQL);
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(UPDATE_TURMA_SQL);
            ps.setInt(4, turma.getTurm_idturma());
            ps.setString(1, turma.getTurm_codturma());
            ps.setString(2, turma.getTurm_serie());
            ps.setString(3, turma.getTurm_turma());

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
    
    public void deletarturma(Turma turma) throws SQLException {

        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(DELETE_TURMA_SQL);
            ps.setInt(1, turma.getTurm_idturma());
            System.out.println(ps);
            ps.executeUpdate();
            ps.close();
            con.close();

        } catch (SQLException e) {
            printSQLException(e);

        }

    }
    
    public List<Turma> procurarTodasTurmas() {
        System.out.println(SELECT_ALL_TURMA_SQL);
        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Turma> listaturma = new ArrayList<>();
        // Step 1: Establishing a Connection
        try ( Connection connection = getConnection(); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TURMA_SQL);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                
                Turma turma = new Turma();

                turma.setTurm_idturma(rs.getInt("turm_idturma"));
                turma.setTurm_codturma(rs.getString("turm_codturma"));
                turma.setTurm_serie(rs.getString("turm_serie"));
                turma.setTurm_turma(rs.getString("turm_turma"));
                
                listaturma.add(turma);

            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return listaturma;
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
