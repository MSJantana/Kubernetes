/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.msoft.hubeducacional.model;


/**
 *
 * @author Marcio
 */
public class Pessoa {

    private int pes_IdPessoa;
    private String pes_Ra;
    private String pes_nome;
    private String pes_cpf;
    private String pes_datanascimento;
    private String pes_sexo;
    private String pes_telefone;    
    private String pes_email;
    private String pes_cep;
    private String pes_endereco;
    private String pes_numero;
    private String pes_bairro;
    private String pes_estado;
    private String pes_cidade;

    public Pessoa(int pes_IdPessoa,String pes_Ra, String pes_nome, String pes_cpf, String pes_datanascimento, String pes_sexo, String pes_telefone,String pes_email ,String pes_cep, String pes_endereco, String pes_numero, String pes_bairro, String pes_estado, String pes_cidade) {
        this.pes_IdPessoa = pes_IdPessoa;
        this.pes_nome = pes_nome;
        this.pes_cpf = pes_cpf;
        this.pes_datanascimento = pes_datanascimento;
        this.pes_sexo = pes_sexo;
        this.pes_telefone = pes_telefone;
        this.pes_email = pes_email;
        this.pes_cep = pes_cep;
        this.pes_endereco = pes_endereco;
        this.pes_numero = pes_numero;
        this.pes_bairro = pes_bairro;
        this.pes_estado = pes_estado;
        this.pes_cidade = pes_cidade;
        this.pes_Ra = pes_Ra;
    }
  

    public Pessoa() {
    }


    public int getPes_IdPessoa() {
        return pes_IdPessoa;
    }

    public void setPes_IdPessoa(int pes_IdPessoa) {
        this.pes_IdPessoa = pes_IdPessoa;
    }

    public String getPes_nome() {
        return pes_nome;
    }

    public void setPes_nome(String pes_nome) {
        this.pes_nome = pes_nome;
    }

    public String getPes_telefone() {
        return pes_telefone;
    }

    public void setPes_telefone(String pes_telefone) {
        this.pes_telefone = pes_telefone;
    }

    public String getPes_email() {
        return pes_email;
    }

    public void setPes_email(String pes_email) {
        this.pes_email = pes_email;
    }
    
    public String getPes_cep() {
        return pes_cep;
    }

    public void setPes_cep(String pes_cep) {
        this.pes_cep = pes_cep;
    }

    public String getPes_endereco() {
        return pes_endereco;
    }

    public void setPes_endereco(String pes_endereco) {
        this.pes_endereco = pes_endereco;
    }

    public String getPes_estado() {
        return pes_estado;
    }

    public void setPes_estado(String pes_estado) {
        this.pes_estado = pes_estado;
    }

    public String getPes_cidade() {
        return pes_cidade;
    }

    public void setPes_cidade(String pes_cidade) {
        this.pes_cidade = pes_cidade;
    }

    public String getPes_datanascimento() {
        return pes_datanascimento;
    }

    public void setPes_datanascimento(String pes_datanascimento) {
        this.pes_datanascimento = pes_datanascimento;
    }
   

    public String getPes_cpf() {
        return pes_cpf;
    }

    public void setPes_cpf(String pes_cpf) {
        this.pes_cpf = pes_cpf;
    }

    public String getPes_sexo() {
        return pes_sexo;
    }

    public void setPes_sexo(String pes_sexo) {
        this.pes_sexo = pes_sexo;
    }

 
    public String getPes_numero() {
        return pes_numero;
    }

    public void setPes_numero(String pes_numero) {
        this.pes_numero = pes_numero;
    }

    public String getPes_bairro() {
        return pes_bairro;
    }

    public void setPes_bairro(String pes_bairro) {
        this.pes_bairro = pes_bairro;
    }

    public String getPes_Ra() {
        return pes_Ra;
    }

    public void setPes_Ra(String pes_Ra) {
        this.pes_Ra = pes_Ra;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "pes_IdPessoa=" + pes_IdPessoa + ", pes_Ra=" + pes_Ra + ", pes_nome=" + pes_nome + ", pes_cpf=" + pes_cpf + ", pes_datanascimento=" + pes_datanascimento + ", pes_sexo=" + pes_sexo + ", pes_telefone=" + pes_telefone + ", pes_email=" + pes_email + ", pes_cep=" + pes_cep + ", pes_endereco=" + pes_endereco + ", pes_numero=" + pes_numero + ", pes_bairro=" + pes_bairro + ", pes_estado=" + pes_estado + ", pes_cidade=" + pes_cidade + '}';
    }
 

}
