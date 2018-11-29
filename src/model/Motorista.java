/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Rubens Back
 */
public class Motorista {
    
    private int idm;
    private String nome,nascimento,endereco,tipocnh,numcnh;

    public Motorista() {
    }

    public Motorista(int idm, String nome, String nascimento, String endereco, String tipocnh, String numcnh) {
        this.idm = idm;
        this.nome = nome;
        this.nascimento = nascimento;
        this.endereco = endereco;
        this.tipocnh = tipocnh;
        this.numcnh = numcnh;
    }

    public int getIdm() {
        return idm;
    }

    public void setIdm(int idm) {
        this.idm = idm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipocnh() {
        return tipocnh;
    }

    public void setTipocnh(String tipocnh) {
        this.tipocnh = tipocnh;
    }

    public String getNumcnh() {
        return numcnh;
    }

    public void setNumcnh(String numcnh) {
        this.numcnh = numcnh;
    }
    
    
}
