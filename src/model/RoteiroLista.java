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
public class RoteiroLista {

    private int idrl;
    private RoteiroData roteiroData;
    private Motorista motorista;
    private Veiculo veiculo;
    private Objeto objeto;

    public RoteiroLista() {
        roteiroData = new RoteiroData();
        motorista = new Motorista();
        veiculo = new Veiculo();
        objeto = new Objeto();
    }

    public RoteiroLista(int idrl, RoteiroData roteiroData, Motorista motorista, Veiculo veiculo, Objeto objeto) {
        this.idrl = idrl;
        this.motorista = motorista;
        this.veiculo = veiculo;
        this.objeto = objeto;
    }

    public int getIdrl() {
        return idrl;
    }

    public void setIdrl(int idrl) {
        this.idrl = idrl;
    }

    public RoteiroData getRoteiroData() {
        return roteiroData;
    }

    public void setRoteiroData(RoteiroData roteiroData) {
        this.roteiroData = roteiroData;
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
}
