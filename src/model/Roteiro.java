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
public class Roteiro {

    private int idr;
    private Date datar;
    private Motorista motorista;
    private Veiculo veiculo;
    private Objeto objeto;

    public Roteiro() {
        motorista = new Motorista();
        veiculo = new Veiculo();
        objeto = new Objeto();
    }

    public Roteiro(int idr, Date datar, Motorista motorista, Veiculo veiculo, Objeto objeto) {
        this.idr = idr;
        this.datar = datar;
        this.motorista = motorista;
        this.veiculo = veiculo;
        this.objeto = objeto;
    }

    public int getIdr() {
        return idr;
    }

    public void setIdr(int idr) {
        this.idr = idr;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista idm) {
        this.motorista = idm;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo idv) {
        this.veiculo = idv;
    }

    public Objeto getObjeto() {
        return objeto;
    }

    public void setObjeto(Objeto ido) {
        this.objeto = ido;
    }

    public Date getDatar() {
        return datar;
    }

    public void setDatar(Date datar) {
        this.datar = datar;
    }

    @Override
    public String toString() {
        return "Roteiro: " + "Nº " + idr + "  /  Dia " + datar;
    }
    
    

}
