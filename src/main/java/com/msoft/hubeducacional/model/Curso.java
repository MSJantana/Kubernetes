/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.msoft.hubeducacional.model;

/**
 *
 * @author Marcio
 */
public class Curso {
   

    private int cur_idCurso;
    private String cur_descricao ;

    public int getCur_idCurso() {
        return cur_idCurso;
    }

    public void setCur_idCurso(int cur_idCurso) {
        this.cur_idCurso = cur_idCurso;
    }

    public String getCur_descricao() {
        return cur_descricao;
    }

    public void setCur_descricao(String cur_descricao) {
        this.cur_descricao = cur_descricao;
    }

    public Curso(int cur_idCurso, String cur_descricao) {
        this.cur_idCurso = cur_idCurso;
        this.cur_descricao = cur_descricao;
    }
    
    public Curso(){
    }
    
    @Override
    public String toString(){
        return "Curso{" + "codigo=" + cur_idCurso + ", nome=" + cur_descricao + '}';
    }

    public Curso(String cur_descricao) {
        this.cur_descricao = cur_descricao;
    }

    public Curso(int cur_idCurso) {
        this.cur_idCurso = cur_idCurso;
    }
    
    
    
}

    

