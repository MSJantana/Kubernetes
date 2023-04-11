/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.msoft.hubeducacional.controller;

import com.msoft.hubeducacional.model.Curso;
import com.msoft.hubeducacional.model.Funcao;
import com.msoft.hubeducacional.model.Matricula;
import com.msoft.hubeducacional.model.Pessoa;
import com.msoft.hubeducacional.model.Professor;
import com.msoft.hubeducacional.model.Turma;
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
public class MatriculaDao {

    private final String jdbcURL = "jdbc:mysql://10.12.2.182:3306/Educa_Hub?useSSL=false";
    private final String jdbcUsername = "marcio";
    private final String jdbcPassword = "passpass";

    private static final String INSERT_MATRICULA_SQL = "INSERT INTO Matricula (turm_idturma, pes_idPessoa, cur_idCurso, func_idFuncao, prof_idProfessor, uni_idUnidade) VALUES (?,?,?,?,?,?)";
    private static final String DELETE_MATRICULA_SQL = "DELETE FROM Matricula WHERE mat_idMatricula = ?";
    private static final String SELECT_ALL_MATRICULA_SQL = "SELECT t.turm_turma, p.pes_nome, pr.prof_nome, f.func_nome, c.cur_descricao, u.uni_siglaEscola, m.mat_idMatricula, m.turm_idturma\n"
            + "FROM Matricula m\n"
            + "INNER JOIN Turma t ON t.turm_idturma = m.turm_idturma\n"
            + "INNER JOIN Pessoa p ON p.pes_idPessoa = m.pes_idPessoa\n"
            + "INNER JOIN Funcao f ON f.func_idFuncao = m.func_idFuncao\n"
            + "INNER JOIN curso c ON c.cur_idCurso = m.cur_idCurso\n"
            + "INNER JOIN Unidade u ON u.uni_idUnidade = m.uni_idUnidade\n"
            + "LEFT JOIN Professores pr ON pr.prof_idProfessor = m.prof_idProfessor";

    public MatriculaDao() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
             System.out.println(e);
        }
        return connection;
    }

    public void insertMatricula(Matricula matricula) throws SQLException {
        System.out.println(INSERT_MATRICULA_SQL);
        try ( Connection connection = getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MATRICULA_SQL)) {
            preparedStatement.setInt(1, matricula.getTurma().getTurm_idturma());
            preparedStatement.setInt(2, matricula.getPessoa().getPes_IdPessoa());
            preparedStatement.setInt(3, matricula.getCurso().getCur_idCurso());
            preparedStatement.setInt(4, matricula.getFuncao().getFunc_idFuncao());
            if (matricula.getProfessor() != null) {
                preparedStatement.setInt(5, matricula.getProfessor().getProf_idprofessor());
            } else {
                preparedStatement.setNull(5, java.sql.Types.INTEGER);
            }
            preparedStatement.setInt(6, matricula.getUnidade().getUni_idUnidade());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

   public void deletarMatricula(Matricula matricula) throws SQLException {

        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(DELETE_MATRICULA_SQL);
            ps.setInt(1, matricula.getMat_idMatricula());
            System.out.println(ps);
            ps.executeUpdate();
            ps.close();
            con.close();

        } catch (SQLException e) {
            printSQLException(e);

        }

    }

    public List<Matricula> getAllMatriculas() throws SQLException {
        System.out.println(SELECT_ALL_MATRICULA_SQL);
        List<Matricula> matriculas = new ArrayList<>();
        try ( Connection connection = getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MATRICULA_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Turma turma = new Turma();
                turma.setTurm_turma(rs.getString("turm_turma"));
                Pessoa pessoa = new Pessoa();
                pessoa.setPes_nome(rs.getString("pes_nome"));
                Funcao funcao = new Funcao();
                funcao.setFunc_nome(rs.getString("func_nome"));
                Curso curso = new Curso();
                curso.setCur_descricao(rs.getString("cur_descricao"));
                Unidade unidade = new Unidade();
                unidade.setUni_siglaEscola(rs.getString("uni_siglaEscola"));
                Professor professor = null;
                if (rs.getString("prof_nome") != null) {
                    professor = new Professor();
                    professor.setProf_nome(rs.getString("prof_nome"));
                }
                Matricula matricula = new Matricula();
                matricula.setMat_idMatricula(rs.getInt("mat_idMatricula"));
                matricula.setTurma(turma);
                matricula.setPessoa(pessoa);
                matricula.setFuncao(funcao);
                matricula.setCurso(curso);
                matricula.setUnidade(unidade);
                matricula.setProfessor(professor);
                matriculas.add(matricula);
            }
        } catch (SQLException e) {
            throw e;
        }
        return matriculas;
    }

    public List<Matricula> getMatriculasByTurma(int turmaId) throws SQLException {
        List<Matricula> matriculas = new ArrayList<>();
        String SELECT_MATRICULAS_BY_TURMA_SQL = SELECT_ALL_MATRICULA_SQL + " WHERE m.turm_idturma = ?";
        try ( Connection connection = getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MATRICULAS_BY_TURMA_SQL)) {
            preparedStatement.setInt(1, turmaId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Turma turma = new Turma();
                turma.setTurm_turma(rs.getString("turm_turma"));
                Pessoa pessoa = new Pessoa();
                pessoa.setPes_nome(rs.getString("pes_nome"));
                Funcao funcao = new Funcao();
                funcao.setFunc_nome(rs.getString("func_nome"));
                Curso curso = new Curso();
                curso.setCur_descricao(rs.getString("cur_descricao"));
                Unidade unidade = new Unidade();
                unidade.setUni_siglaEscola(rs.getString("uni_siglaEscola"));
                Professor professor = null;
                if (rs.getString("prof_nome") != null) {
                    professor = new Professor();
                    professor.setProf_nome(rs.getString("prof_nome"));
                }
                Matricula matricula = new Matricula();
                matricula.setMat_idMatricula(rs.getInt("mat_idMatricula"));
                matricula.setTurma(turma);
                matricula.setPessoa(pessoa);
                matricula.setFuncao(funcao);
                matricula.setCurso(curso);
                matricula.setUnidade(unidade);
                matricula.setProfessor(professor);
                matriculas.add(matricula);
            }
        } catch (SQLException e) {
            throw e;
        }
        return matriculas;
    }

    public List<Matricula> getMatriculasByCurso(int cursoId) throws SQLException {
        List<Matricula> matriculas = new ArrayList<>();
        String SELECT_MATRICULAS_BY_CURSO_SQL = SELECT_ALL_MATRICULA_SQL + " WHERE m.cur_idCurso = ?";
        try ( Connection connection = getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MATRICULAS_BY_CURSO_SQL)) {
            preparedStatement.setInt(1, cursoId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Turma turma = new Turma();
                turma.setTurm_turma(rs.getString("turm_turma"));
                Pessoa pessoa = new Pessoa();
                pessoa.setPes_nome(rs.getString("pes_nome"));
                Funcao funcao = new Funcao();
                funcao.setFunc_nome(rs.getString("func_nome"));
                Curso curso = new Curso();
                curso.setCur_descricao(rs.getString("cur_descricao"));
                Unidade unidade = new Unidade();
                unidade.setUni_siglaEscola(rs.getString("uni_siglaEscola"));
                Professor professor = null;
                if (rs.getString("prof_nome") != null) {
                    professor = new Professor();
                    professor.setProf_nome(rs.getString("prof_nome"));
                }
                Matricula matricula = new Matricula();
                matricula.setMat_idMatricula(rs.getInt("mat_idMatricula"));
                matricula.setTurma(turma);
                matricula.setPessoa(pessoa);
                matricula.setFuncao(funcao);
                matricula.setCurso(curso);
                matricula.setUnidade(unidade);
                matricula.setProfessor(professor);
                matriculas.add(matricula);
            }
        } catch (SQLException e) {
            throw e;
        }
        return matriculas;
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
