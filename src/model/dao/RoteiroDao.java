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
import java.util.Date;
import java.util.List;
import model.RoteiroLista;
import util.Conexao;
import util.Dao;

/**
 *
 * @author Rubens Back
 */
public class RoteiroDao extends Dao {

    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public RoteiroDao() {
        super();
    }

    public List<RoteiroLista> listar() {
        this.sql = "SELECT "
                + " datar,id_motorista,id_veiculo,id_objeto,idroteirolista"
                + " FROM "
                + " roteirolista"
                + " INNER JOIN roteirodata"
                + " ON id_roteirodata = idroteirodata"
                + " ORDER BY datar";
        try {
            this.stmt = conexao.prepareStatement(this.sql);
            res = this.stmt.executeQuery();
            List<RoteiroLista> lista = new ArrayList<>();
            while (res.next()) {
                RoteiroLista r = new RoteiroLista();
                r.getRoteiroData().setDatar(res.getDate("datar"));
                r.getMotorista().setIdm(res.getInt("id_motorista"));
                r.getVeiculo().setIdv(res.getInt("id_veiculo"));
                r.getObjeto().setIdo(res.getInt("id_objeto"));
                r.setIdrl(res.getInt("idroteirolista"));
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

    public boolean excluir(RoteiroLista obj) {
        this.sql = "DELETE FROM roteirolista WHERE idroteirolista = ?";
        try {
            stmt = conexao.prepareStatement(this.sql);
            stmt.setInt(1, obj.getIdrl());
            //stmt.setInt(1, obj.getRoteiroData().getIdr());
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

    public boolean fazerEntrega(RoteiroLista obj) {
        this.sql = "DELETE FROM roteirolista WHERE idroteirolista = ?";
        try {
            stmt = conexao.prepareStatement(this.sql);
            stmt.setInt(1, obj.getIdrl());
            //stmt.setInt(1, obj.getRoteiroData().getIdr());

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

    public boolean fazerEntrega2(RoteiroLista obj) {
        this.sql = "DELETE FROM objeto WHERE idobjeto = ?";

        try {
            stmt = conexao.prepareStatement(this.sql);
            stmt.setInt(1, obj.getObjeto().getIdo());
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

    public List<RoteiroLista> pesquisarM(String termo) {
        this.sql = "SELECT "
                + " datar,id_motorista,id_veiculo,id_objeto,idroteirolista"
                + " FROM "
                + " roteirolista"
                + " INNER JOIN roteirodata"
                + " ON id_roteirodata = idroteirodata"
                + " WHERE id_motorista = ?"
                + " ORDER BY datar";
        try {
            this.stmt = conexao.prepareStatement(this.sql);
            this.stmt.setString(1, termo);
            res = this.stmt.executeQuery();
            List<RoteiroLista> lista = new ArrayList<>();
            while (res.next()) {
                RoteiroLista r = new RoteiroLista();
                r.getRoteiroData().setDatar(res.getDate("datar"));
                r.getMotorista().setIdm(res.getInt("id_motorista"));
                r.getVeiculo().setIdv(res.getInt("id_veiculo"));
                r.getObjeto().setIdo(res.getInt("id_objeto"));
                r.setIdrl(res.getInt("idroteirolista"));
                lista.add(r);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public List<RoteiroLista> pesquisarV(String termo) {
        this.sql = "SELECT "
                + " datar,id_motorista,id_veiculo,id_objeto,idroteirolista"
                + " FROM "
                + " roteirolista"
                + " INNER JOIN roteirodata"
                + " ON id_roteirodata = idroteirodata"
                + " WHERE id_veiculo = ?"
                + " ORDER BY datar";
        try {
            this.stmt = conexao.prepareStatement(this.sql);
            this.stmt.setString(1, termo);
            res = this.stmt.executeQuery();
            List<RoteiroLista> lista = new ArrayList<>();
            while (res.next()) {
                RoteiroLista r = new RoteiroLista();
                r.getRoteiroData().setDatar(res.getDate("datar"));
                r.getMotorista().setIdm(res.getInt("id_motorista"));
                r.getVeiculo().setIdv(res.getInt("id_veiculo"));
                r.getObjeto().setIdo(res.getInt("id_objeto"));
                r.setIdrl(res.getInt("idroteirolista"));
                lista.add(r);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public List<RoteiroLista> pesquisarO(String termo) {
        this.sql = "SELECT "
                + " datar,id_motorista,id_veiculo,id_objeto,idroteirolista"
                + " FROM "
                + " roteirolista"
                + " INNER JOIN roteirodata"
                + " ON id_roteirodata = idroteirodata"
                + " WHERE id_objeto = ?"
                + " ORDER BY datar";
        try {
            this.stmt = conexao.prepareStatement(this.sql);
            this.stmt.setString(1, termo);
            res = this.stmt.executeQuery();
            List<RoteiroLista> lista = new ArrayList<>();
            while (res.next()) {
                RoteiroLista r = new RoteiroLista();
                r.getRoteiroData().setDatar(res.getDate("datar"));
                r.getMotorista().setIdm(res.getInt("id_motorista"));
                r.getVeiculo().setIdv(res.getInt("id_veiculo"));
                r.getObjeto().setIdo(res.getInt("id_objeto"));
                r.setIdrl(res.getInt("idroteirolista"));
                lista.add(r);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
