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
public class Veiculo {
    
    private int idv,ano,cargamax;
    private String marca,modelo,placa,tipo;

    public Veiculo() {
    }

    public Veiculo(int idv, int ano, int cargamax, String marca, String modelo, String placa, String tipo) {
        this.idv = idv;
        this.ano = ano;
        this.cargamax = cargamax;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.tipo = tipo;
    }

    public int getIdv() {
        return idv;
    }

    public void setIdv(int idv) {
        this.idv = idv;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getCargamax() {
        return cargamax;
    }

    public void setCargamax(int cargamax) {
        this.cargamax = cargamax;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "ID: " + idv + "  //  " + tipo + "  //  Carga Máxima - " + cargamax;
    }
    
}
