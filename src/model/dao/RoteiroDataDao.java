/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.RoteiroData;
import util.Dao;

/**
 *
 * @author Rubens Back
 */
public class RoteiroDataDao extends Dao {

    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public RoteiroDataDao() {
        super();
    }

    public boolean salvar(RoteiroData obj) {
        try {
            stmt = conexao.prepareStatement("INSERT INTO "
                    + "roteirodata(datar) "
                    + "VALUES(?)");

            stmt.setDate(1,new java.sql.Date(obj.getDatar().getTime()));

            int res = stmt.executeUpdate();
            if (res > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean atualizar(RoteiroData obj) {
        this.sql = "UPDATE roteirodata SET "
                + " datar = ?"
                + " WHERE "
                + " idroteirodata = ?";
        try {
            stmt = conexao.prepareStatement(this.sql);
            stmt.setDate(1,new java.sql.Date(obj.getDatar().getTime()));
            stmt.setInt(2, obj.getIdr());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
            if (debug) {
                System.out.println(sql);
            }
        }
    }
    
    public boolean excluir(RoteiroData obj) {
        try {
            stmt = conexao.prepareStatement("DELETE FROM roteirodata "
                    + " WHERE idroteirodata = ?");
            stmt.setInt(1, obj.getIdr());

            //Verifica a quantidade de linhas afetadas
            return (stmt.executeUpdate() > 0);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public List<RoteiroData> listar() {
        this.sql = "SELECT "
                + " idroteirodata, datar"
                + " FROM "
                + " roteirodata"
                + " ORDER BY idroteirodata DESC";
        try {
            this.stmt = conexao.prepareStatement(this.sql);
            res = this.stmt.executeQuery();
            List<RoteiroData> lista = new ArrayList<>();
            while (res.next()) {
                RoteiroData r = new RoteiroData();
                r.setIdr(res.getInt("idroteirodata"));
                r.setDatar(res.getDate("datar"));
                lista.add(r);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        } finally {
            if (debug) {
                System.out.println(sql);
            }
        }
    }
}
