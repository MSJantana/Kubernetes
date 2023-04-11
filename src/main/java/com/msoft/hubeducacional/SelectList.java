package com.msoft.hubeducacional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectList {

    protected Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://10.12.2.182:3306/Educa_Hub?useSSL=false", "marcio", "passpass");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return con;
    }

    public List<String> select(String table, String field) {
        List<String> results = new ArrayList<>();

        String query = "SELECT DISTINCT " + field + " FROM " + table;

        try ( Connection conn = getConnection();  PreparedStatement pstmt = conn.prepareStatement(query);  ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                results.add(rs.getString(field));
            }

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return results;
    }

}
