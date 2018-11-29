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
public class Roteiro {
    
    private int idr;
    private Motorista idm;
    private Veiculo idv;
    private Objeto ido;

    public Roteiro() {
        idm = new Motorista();
        idv = new Veiculo();
        ido = new Objeto();
    }

    public Roteiro(int idr, Motorista idm, Veiculo idv, Objeto ido) {
        this.idr = idr;
        this.idm = idm;
        this.idv = idv;
        this.ido = ido;
    }

    public int getIdr() {
        return idr;
    }

    public void setIdr(int idr) {
        this.idr = idr;
    }

    public Motorista getIdm() {
        return idm;
    }

    public void setIdm(Motorista idm) {
        this.idm = idm;
    }

    public Veiculo getIdv() {
        return idv;
    }

    public void setIdv(Veiculo idv) {
        this.idv = idv;
    }

    public Objeto getIdo() {
        return ido;
    }

    public void setIdo(Objeto ido) {
        this.ido = ido;
    }

}
