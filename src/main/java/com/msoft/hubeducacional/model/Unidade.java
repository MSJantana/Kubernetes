/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.msoft.hubeducacional.model;

/**
 *
 * @author Marcio
 */
public class Unidade {

    private int uni_idUnidade;
    private String uni_nomeUnidade;
    private String uni_telefone;
    private String uni_siglaEscola;

    public Unidade(int uni_idUnidade, String uni_nomeUnidade, String uni_telefone, String uni_siglaEscola) {
        this.uni_idUnidade = uni_idUnidade;
        this.uni_nomeUnidade = uni_nomeUnidade;
        this.uni_telefone = uni_telefone;
        this.uni_siglaEscola = uni_siglaEscola;
    }

    public Unidade() {
    }

    public int getUni_idUnidade() {
        return uni_idUnidade;
    }

    public void setUni_idUnidade(int uni_idUnidade) {
        this.uni_idUnidade = uni_idUnidade;
    }

    public String getUni_nomeUnidade() {
        return uni_nomeUnidade;
    }

    public void setUni_nomeUnidade(String uni_nomeUnidade) {
        this.uni_nomeUnidade = uni_nomeUnidade;
    }

    public String getUni_telefone() {
        return uni_telefone;
    }

    public void setUni_telefone(String uni_telefone) {
        this.uni_telefone = uni_telefone;
    }

    public String getUni_siglaEscola() {
        return uni_siglaEscola;
    }

    public void setUni_siglaEscola(String uni_siglaEscola) {
        this.uni_siglaEscola = uni_siglaEscola;
    }

    @Override
    public String toString() {
        return "Unidade {Unidade=" + uni_nomeUnidade + "IdUnidade=" + uni_idUnidade
                + "Telefone Unidade=" + uni_telefone + ",Sigla Unidade=" + uni_siglaEscola + " }";
    }

}
