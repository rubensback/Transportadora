package model.dao;

import interfaces.DaoI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Objeto;
import model.Veiculo;
import util.Conexao;
import util.Dao;

public class VeiculoDao extends Dao implements DaoI<Veiculo> {

    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public VeiculoDao() {
        super();
    }

    @Override
    public boolean salvar(Veiculo obj) {
        try {
            stmt = conexao.prepareStatement("INSERT INTO "
                    + "veiculo(marca,modelo,ano,placa,tipo,cargamax) "
                    + "VALUES(?,?,?,?,?,?)");

            stmt.setString(1, obj.getMarca());
            stmt.setString(2, obj.getModelo());
            stmt.setInt(3, obj.getAno());
            stmt.setString(4, obj.getPlaca());
            stmt.setString(5, obj.getTipo());
            stmt.setInt(6, obj.getCargamax());

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

    @Override
    public boolean atualizar(Veiculo obj) {
        this.sql = "UPDATE veiculo SET "
                + " marca = ?,"
                + " modelo = ?,"
                + " ano = ?,"
                + " placa = ?,"
                + " tipo = ?,"
                + " cargamax = ?"
                + " WHERE "
                + " idveiculo = ?";
        try {
            stmt = conexao.prepareStatement(this.sql);
            stmt.setString(1, obj.getMarca());
            stmt.setString(2, obj.getModelo());
            stmt.setInt(3, obj.getAno());
            stmt.setString(4, obj.getPlaca());
            stmt.setString(5, obj.getTipo());
            stmt.setInt(6, obj.getCargamax());
            stmt.setInt(7, obj.getIdv());
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

    @Override
    public boolean excluir(Veiculo obj) {
        try {
            stmt = conexao.prepareStatement("DELETE FROM veiculo "
                    + " WHERE idveiculo = ?");
            stmt.setInt(1, obj.getIdv());

            //Verifica a quantidade de linhas afetadas
            return (stmt.executeUpdate() > 0);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Veiculo> listar() {
        this.sql = "SELECT "
                + " idveiculo, marca, modelo,ano,placa,tipo,cargamax"
                + " FROM "
                + " veiculo"
                + " ORDER BY idveiculo DESC";
        try {
            this.stmt = conexao.prepareStatement(this.sql);
            res = this.stmt.executeQuery();
            List<Veiculo> lista = new ArrayList<>();
            while (res.next()) {
                Veiculo v = new Veiculo();
                v.setIdv(res.getInt("idveiculo"));
                v.setMarca(res.getString("marca"));
                v.setModelo(res.getString("modelo"));
                v.setAno(res.getInt("ano"));
                v.setPlaca(res.getString("placa"));
                v.setTipo(res.getString("tipo"));
                v.setCargamax(res.getInt("cargamax"));
                lista.add(v);
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

    public List<Veiculo> pesquisar(String termo) {
        this.sql = "SELECT "
                + " idveiculo, marca, modelo,ano,placa,tipo"
                + " FROM "
                + " veiculo"
                + " WHERE "
                + " idveiculo = ? OR marca LIKE ? OR modelo LIKE ? OR ano LIKE ? OR placa LIKE ? OR tipo LIKE ?"
                + " ORDER BY idveiculo";
        try {
            this.stmt = conexao.prepareStatement(this.sql);
            this.stmt.setString(1, termo);
            this.stmt.setString(2, termo + "%");
            this.stmt.setString(3, termo + "%");
            this.stmt.setString(4, termo + "%");
            this.stmt.setString(5, termo + "%");
            this.stmt.setString(6, termo + "%");
            res = this.stmt.executeQuery();
            List<Veiculo> lista = new ArrayList<>();
            while (res.next()) {
                Veiculo v = new Veiculo();
                v.setIdv(res.getInt("idveiculo"));
                v.setMarca(res.getString("marca"));
                v.setModelo(res.getString("modelo"));
                v.setAno(res.getInt("ano"));
                v.setPlaca(res.getString("placa"));
                v.setTipo(res.getString("tipo"));
                lista.add(v);
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

    @Override
    public Veiculo lerPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
