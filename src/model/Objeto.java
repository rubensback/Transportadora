/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Rubens Back
 */
public class Objeto {
    
    private int ido;
    private String nomerem,enderecorem,nomedest,enderecodest;
    private Date datadep;
    private double peso;
    private Veiculo idv;

    public Objeto() {
        idv = new Veiculo();
    }

    public Objeto(int ido, String nomerem, String enderecorem, String nomedest, String enderecodest, Date datadep, double peso, Veiculo idv) {
        this.ido = ido;
        this.nomerem = nomerem;
        this.enderecorem = enderecorem;
        this.nomedest = nomedest;
        this.enderecodest = enderecodest;
        this.datadep = datadep;
        this.peso = peso;
        this.idv = idv;
    }

    public int getIdo() {
        return ido;
    }

    public void setIdo(int ido) {
        this.ido = ido;
    }

    public String getNomerem() {
        return nomerem;
    }

    public void setNomerem(String nomerem) {
        this.nomerem = nomerem;
    }

    public String getEnderecorem() {
        return enderecorem;
    }

    public void setEnderecorem(String enderecorem) {
        this.enderecorem = enderecorem;
    }

    public String getNomedest() {
        return nomedest;
    }

    public void setNomedest(String nomedest) {
        this.nomedest = nomedest;
    }

    public String getEnderecodest() {
        return enderecodest;
    }

    public void setEnderecodest(String enderecodest) {
        this.enderecodest = enderecodest;
    }

    public Date getDatadep() {
        return datadep;
    }

    public void setDatadep(Date datadep) {
        this.datadep = datadep;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Veiculo getIdv() {
        return idv;
    }

    public void setIdv(Veiculo idv) {
        this.idv = idv;
    }
    
}
