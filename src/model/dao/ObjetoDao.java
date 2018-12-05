package model.dao;

import interfaces.DaoI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import model.Objeto;
import util.Conexao;
import util.Dao;

public class ObjetoDao extends Dao implements DaoI<Objeto> {

    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public ObjetoDao() {
        super();
    }

    @Override
    public boolean salvar(Objeto obj) {
        try {
            stmt = conexao.prepareStatement("INSERT INTO "
                    + "objeto(nomerem,enderecorem,nomedest,enderecodest,datadep,peso) "
                    + "VALUES(?,?,?,?,?,?)");

            stmt.setString(1, obj.getNomerem());
            stmt.setString(2, obj.getEnderecorem());
            stmt.setString(3, obj.getNomedest());
            stmt.setString(4, obj.getNomedest());
            stmt.setDate(5, new java.sql.Date(obj.getDatadep().getTime()));
            stmt.setDouble(6, obj.getPeso());

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
    public boolean atualizar(Objeto obj) {
        this.sql = "UPDATE objeto SET "
                + " nomerem = ?,"
                + " enderecorem = ?,"
                + " nomedest = ?,"
                + " enderecorem = ?,"
                + " datadep = ?,"
                + " peso = ?"
                + " WHERE "
                + " idobjeto = ?";
        try {
            stmt = conexao.prepareStatement(this.sql);
            stmt.setString(1, obj.getNomerem());
            stmt.setString(2, obj.getEnderecorem());
            stmt.setString(3, obj.getNomedest());
            stmt.setString(4, obj.getNomedest());
            stmt.setDate(5, new java.sql.Date(obj.getDatadep().getTime()));
            stmt.setDouble(6, obj.getPeso());
            stmt.setInt(7, obj.getIdo());
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
    public boolean excluir(Objeto obj) {
        try {
            stmt = conexao.prepareStatement("DELETE FROM objeto "
                    + " WHERE idobjeto = ?");
            stmt.setInt(1, obj.getIdo());

            //Verifica a quantidade de linhas afetadas
            return (stmt.executeUpdate() > 0);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Objeto> listar() {
        this.sql = "SELECT "
                + " idobjeto, nomerem, enderecorem,nomedest,enderecodest,datadep,peso"
                + " FROM "
                + " objeto"
                + " ORDER BY idobjeto DESC";
        try {
            this.stmt = conexao.prepareStatement(this.sql);
            res = this.stmt.executeQuery();
            List<Objeto> lista = new ArrayList<>();
            while (res.next()) {
                Objeto o = new Objeto();
                o.setIdo(res.getInt("idobjeto"));
                o.setNomerem(res.getString("nomerem"));
                o.setEnderecorem(res.getString("enderecorem"));
                o.setNomedest(res.getString("nomedest"));
                o.setEnderecodest(res.getString("enderecodest"));
                o.setDatadep(res.getDate("datadep"));
                o.setPeso(res.getDouble("peso"));
                lista.add(o);
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
    public Objeto lerPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Objeto> pesquisar(String termo) {
        this.sql = "SELECT "
                + " idobjeto, nomerem, enderecorem,nomedest,enderecodest,datadep,peso "
                + " FROM "
                + " objeto"
                + " WHERE "
                + " idobjeto = ? OR nomerem LIKE ? OR enderecorem LIKE ? OR nomedest LIKE ? OR enderecodest LIKE ? OR datadep LIKE ? OR peso LIKE ?"
                + " ORDER BY idobjeto";
        try {
            this.stmt = conexao.prepareStatement(this.sql);
            this.stmt.setString(1, termo);
            this.stmt.setString(2, termo + "%");
            this.stmt.setString(3, termo + "%");
            this.stmt.setString(4, termo + "%");
            this.stmt.setString(5, termo + "%");
            this.stmt.setString(6, termo + "%");
            this.stmt.setString(7, termo + "%");
            res = this.stmt.executeQuery();
            List<Objeto> lista = new ArrayList<>();
            while (res.next()) {
                Objeto o = new Objeto();
                o.setIdo(res.getInt("idobjeto"));
                o.setNomerem(res.getString("nomerem"));
                o.setEnderecorem(res.getString("enderecorem"));
                o.setNomedest(res.getString("nomedest"));
                o.setEnderecodest(res.getString("enderecodest"));
                o.setDatadep(res.getDate("datadep"));
                o.setPeso(res.getDouble("peso"));
                lista.add(o);
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
