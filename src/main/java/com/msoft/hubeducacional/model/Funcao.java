/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.msoft.hubeducacional.model;

/**
 *
 * @author claudevandro
 */
public class Funcao {
    private int func_idFuncao;
    private String func_nome;

    public Funcao(int func_idFuncao, String func_nome) {
        this.func_idFuncao = func_idFuncao;
        this.func_nome = func_nome;
    }

    
    public Funcao(){
    }
    
    @Override
    public String toString(){
        return "Funcao{" + "codigo=" + func_idFuncao + ", nome=" + func_nome + '}';
    }
    
    public int getFunc_idFuncao() {
        return func_idFuncao;
    }

    public void setFunc_idFuncao(int func_idFuncao) {
        this.func_idFuncao = func_idFuncao;
    }

    public String getFunc_nome() {
        return func_nome;
    }

    public void setFunc_nome(String func_nome) {
        this.func_nome = func_nome;
    }
    
    
    
    
}
