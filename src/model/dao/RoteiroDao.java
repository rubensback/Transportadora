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
import model.Roteiro;
import util.Conexao;
import util.Dao;

/**
 *
 * @author Rubens Back
 */
public class RoteiroDao extends Dao{

    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public RoteiroDao() {
        super();
    }
    
    public List<Roteiro> listar() {
        this.sql = "SELECT "
                + " idroteiro,id_motorista,id_veiculo,id_objeto"
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
    
    