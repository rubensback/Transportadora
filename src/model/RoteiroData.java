/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Rubens Back
 */
public class RoteiroData {
    
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yy");
    
    private int idr;
    private Date datar;

    public RoteiroData() {
    }

    public RoteiroData(int idr, Date datar) {
        this.idr = idr;
        this.datar = datar;
    }

    public int getIdr() {
        return idr;
    }

    public void setIdr(int idr) {
        this.idr = idr;
    }

    public Date getDatar() {
        return datar;
    }

    public void setDatar(Date datar) {
        this.datar = datar;
    }

    @Override
    public String toString() {
        return "ID: " + idr + "  //  Data: " + sdf1.format(datar);
    }
    
    
}
