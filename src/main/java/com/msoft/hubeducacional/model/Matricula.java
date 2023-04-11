/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.msoft.hubeducacional.model;

/**
 *
 * @author Marcio
 */
public class Matricula {
    
    private int mat_idMatricula;
    private Turma turma;
    private Pessoa pessoa;
    private Curso curso;
    private Funcao funcao;
    private Professor professor;
    private Unidade unidade;

    public Matricula() {
    }

    public Matricula(int mat_idMatricula, Turma turma, Pessoa pessoa, Curso curso, Funcao funcao, Professor professor, Unidade unidade) {
        this.mat_idMatricula = mat_idMatricula;
        this.turma = turma;
        this.pessoa = pessoa;
        this.curso = curso;
        this.funcao = funcao;
        this.professor = professor;
        this.unidade = unidade;
    }

    public int getMat_idMatricula() {
        return mat_idMatricula;
    }

    public void setMat_idMatricula(int mat_idMatricula) {
        this.mat_idMatricula = mat_idMatricula;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    @Override
    public String toString() {
        return "Matricula{" + "mat_idMatricula=" + mat_idMatricula + ", turma=" + turma + ", pessoa=" + pessoa + ", curso=" + curso + ", funcao=" + funcao + ", professor=" + professor + ", unidade=" + unidade + '}';
    }
 
    
}
