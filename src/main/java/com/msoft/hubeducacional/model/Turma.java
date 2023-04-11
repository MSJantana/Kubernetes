/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.msoft.hubeducacional.model;

/**
 *
 * @author Marcio
 */
public class Turma {
    
    private int turm_idturma;
    private String turm_codturma;
    private String turm_serie;
    private String turm_turma;

    public Turma(int turm_idturma, String turm_codturma, String turm_serie, String turm_turma) {
        this.turm_idturma = turm_idturma;
        this.turm_codturma = turm_codturma;
        this.turm_serie = turm_serie;
        this.turm_turma = turm_turma;
    }
 
    public Turma(){}

    public int getTurm_idturma() {
        return turm_idturma;
    }

    public void setTurm_idturma(int turm_idturma) {
        this.turm_idturma = turm_idturma;
    }

    public String getTurm_codturma() {
        return turm_codturma;
    }

    public void setTurm_codturma(String turm_codturma) {
        this.turm_codturma = turm_codturma;
    }

 
    public String getTurm_serie() {
        return turm_serie;
    }

    public void setTurm_serie(String turm_serie) {
        this.turm_serie = turm_serie;
    }

    public String getTurm_turma() {
        return turm_turma;
    }

    public void setTurm_turma(String turm_turma) {
        this.turm_turma = turm_turma;
    }

    @Override
    public String toString() {
        return "Turma{" + "turm_idturma=" + turm_idturma + ", turm_codturma=" + turm_codturma + ", turm_serie=" + turm_serie + ", turm_turma=" + turm_turma + '}';
    }
    
    
}
