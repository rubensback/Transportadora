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
import model.RoteiroData;
import model.RoteiroLista;
import util.Conexao;
import util.Dao;

/**
 *
 * @author Rubens Back
 */
public class GerarRoteiroDao extends Dao {

    public GerarRoteiroDao() {
        super();
    }

    public boolean salvar(RoteiroLista obj) throws Exception {
        try {
            stmt = conexao.prepareStatement("INSERT INTO "
                    + "roteirolista(id_roteirodata,id_motorista,id_veiculo,id_objeto) "
                    + "VALUES(?,?,?,?)");

            stmt.setInt(1, obj.getRoteiroData().getIdr());
            stmt.setInt(2, obj.getMotorista().getIdm());
            stmt.setInt(3, obj.getVeiculo().getIdv());
            stmt.setInt(4, obj.getObjeto().getIdo());

            int res = stmt.executeUpdate();
            if (res > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
            Conexao.fechaConexao(conexao, stmt);
        }
    }

    public List<RoteiroLista> listar() throws Exception {
        this.sql = "SELECT "
                + " id_roteirodata, id_motorista,id_veiculo,id_objeto"
                + " FROM "
                + " roteirolista"
                + " ORDER BY id_roteirodata DESC";
        try {
            this.stmt = conexao.prepareStatement(this.sql);
            res = this.stmt.executeQuery();
            List<RoteiroLista> lista = new ArrayList<>();
            while (res.next()) {
                RoteiroLista r = new RoteiroLista();
                r.getRoteiroData().setIdr(res.getInt("id_roteirodata"));
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
            Conexao.fechaConexao(conexao, stmt, res);
        }
    }

    public boolean atualizar(RoteiroLista obj) throws Exception {
        this.sql = "UPDATE roteirolista SET "
                + " id_roteirodata = ?,"
                + " id_motorista = ?,"
                + " id_veiculo = ?,"
                + " id_objeto = ?"
                + " WHERE "
                + " idroteirolista = ?";
        try {
            stmt = conexao.prepareStatement(this.sql);
            stmt.setInt(1, obj.getRoteiroData().getIdr());
            stmt.setInt(2, obj.getMotorista().getIdm());
            stmt.setInt(3, obj.getVeiculo().getIdv());
            stmt.setInt(4, obj.getObjeto().getIdo());
            stmt.setInt(5, obj.getIdrl());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
            Conexao.fechaConexao(conexao, stmt);
        }
    }
}
