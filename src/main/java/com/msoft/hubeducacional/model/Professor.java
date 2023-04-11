/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.msoft.hubeducacional.model;

/**
 *
 * @author Marcio 
 */
public class Professor {
    private int prof_idprofessor;
    private String prof_nome;
    private String prof_cpf; 
    private String prof_titulacao;

     
    public Professor(){
        
    }
    
    public int getProf_idprofessor() {
        return prof_idprofessor;
    }

    public void setProf_idprofessor(int prof_idprofessor) {
        this.prof_idprofessor = prof_idprofessor;
    }

    public String getProf_nome() {
        return prof_nome;
    }

    public void setProf_nome(String prof_nome) {
        this.prof_nome = prof_nome;
    }

    public String getProf_cpf() {
        return prof_cpf;
    }

    public void setProf_cpf(String prof_cpf) {
        this.prof_cpf = prof_cpf;
    }

    public String getProf_titulacao() {
        return prof_titulacao;
    }

    public void setProf_titulacao(String prof_titulacao) {
        this.prof_titulacao = prof_titulacao;
    }

    public Professor(int prof_idprofessor, String prof_nome, String prof_cpf, String prof_titulacao) {
        this.prof_idprofessor = prof_idprofessor;
        this.prof_nome = prof_nome;
        this.prof_cpf = prof_cpf;
        this.prof_titulacao = prof_titulacao;
    }

    @Override
    public String toString() {
        return "Professor{" + "prof_idprofessor=" + prof_idprofessor + ", prof_nome=" + prof_nome + ", prof_cpf=" + prof_cpf + ", prof_titulacao=" + prof_titulacao + '}';
    }


   
}
