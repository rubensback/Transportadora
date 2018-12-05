/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import interfaces.DaoI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Roteiro;
import util.Dao;

/**
 *
 * @author Rubens Back
 */
public class GerarRoteiroDao extends Dao {

    public GerarRoteiroDao() {
        super();
    }

    public boolean salvar(Roteiro obj) {
        try {
            stmt = conexao.prepareStatement("INSERT INTO "
                    + "roteiro(idroteiro,datar,id_motorista,id_veiculo,id_objeto) "
                    + "VALUES(?,?,?,?,?)");
            
            stmt.setInt(1, obj.getIdr());
            stmt.setDate(2, new java.sql.Date(obj.getDatar().getTime()));
            stmt.setInt(3, obj.getMotorista().getIdm());
            stmt.setInt(4, obj.getVeiculo().getIdv());
            stmt.setInt(5, obj.getObjeto().getIdo());

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
    
    public List<Roteiro> listar() {
        this.sql = "SELECT "
                + " idroteiro, datar, id_motorista,id_veiculo,id_objeto"
                + " FROM "
                + " roteiro"
                + " ORDER BY idroteiro DESC";
        try {
            this.stmt = conexao.prepareStatement(this.sql);
            res = this.stmt.executeQuery();
            List<Roteiro> lista = new ArrayList<>();
            while (res.next()) {
                Roteiro r = new Roteiro();
                r.setIdr(res.getInt("idroteiro"));
                r.setDatar(res.getDate("datar"));
                r.getMotorista().setIdm(res.getInt("id_motorista"));
                r.getVeiculo().setIdv(res.getInt("id_veiculo"));
                r.getObjeto().setIdo(res.getInt("id_objeto"));
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

